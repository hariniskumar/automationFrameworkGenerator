package afg;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class AutomationFrameworkGenerator {
	static String baseUrl;
	static String podFileRelativePath;
	static String projectFullPath;
	static String basePackageName;
	static String projectName;
	static VelocityEngine velocityEngine;
	static String podExcelFilePath;
	static Properties elementTypeDefitions;
	static List<Page> pages = new ArrayList<Page>();
	static Map<String, String> pageName2PackageNameMap = new HashMap<String, String>();

	static {
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
	}

	public static void main(String[] args) {

		System.out.println("Usage java -jar AutomationFrameworkGenerator <website url> <podFileRelativePath> <projectFullPath>");
		System.out.println(
				"Example: java -jar AutomationFrameworkGenerator https://cogmento.com com.cogmento.automation.web.xlsx /user/git/com.cogmento.automation.web");
		System.out.println("<projectFullPath> refers to the empty maven project's full path");
		if (args.length == 3) {
			baseUrl = args[0];
			podFileRelativePath = args[1];
			projectFullPath = args[2];
			// for basePackage convention is used: project folder, maven groupID and artifact ID all have the same value.
			basePackageName = new File(projectFullPath).getName();
		} else {
			System.out.println("Command line arguments missing or incomplete.");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter <website url>");
			baseUrl = scanner.nextLine();
			System.out.println("Enter <podFileRelativePath>");
			podFileRelativePath = scanner.nextLine();
			System.out.println("Enter <projectFullPath>");
			projectFullPath = scanner.nextLine();
			System.out.println("Enter <basePackage>");
			basePackageName = scanner.nextLine();
			scanner.close();
		}
		ExcelUtil.init(System.getProperty("user.dir") + "/" + podFileRelativePath);
		elementTypeDefitions = ExcelUtil.getElementTypeDefinition();
		// Runtime.getRuntime().exec("cmd /c start somefile.bat");
		// Runtime.getRuntime().exec("cmd /c \"start somefile.bat && start other.bat && cd C:\\test && test.exe\"");
		generateMavenPomFile();
		generateTestDataFolder();
		generateScriptsFolder();
		generateConfigFolder();
		generateTools();
		generateWebDrivers();
		generateScreenshotFolder();
		processPod();
		fixMissingLinks();
		for (Page page : pages) {
			generatePageClass(page);
			generatePageValidatorClass(page);			
		}
		generateFunctionalTestcases();
		// generate testng
		generateTestNgFile();
	}

	private static void generateMavenPomFile() {
		FileWriter mavenPomXmlFileWriter = null;
		try {
			String projectDirectoryName = new File(projectFullPath).getName();
			projectName = projectDirectoryName;
			String groupId = projectName;
			String artifactId = projectName;
			System.out.println("Rendering pom.xml");
			VelocityContext velocityContext = new VelocityContext();
			StringWriter writer = new StringWriter();
			velocityContext.put("groupId", groupId);
			velocityContext.put("artifactId", artifactId);
			velocityContext.put("projectName", projectName);
			velocityContext.put("baseUrl", baseUrl);
			File mavenPomXmlFile = new File(projectFullPath, "pom.xml");
			mavenPomXmlFileWriter = new FileWriter(mavenPomXmlFile, false);
			Template template = velocityEngine.getTemplate("velocityTemplate/maven/pom.xml.vm");
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			System.out.println("****************************************");
			mavenPomXmlFileWriter.write(writer.toString());
			mavenPomXmlFileWriter.flush();
		} catch (Exception e) {
			try {
				mavenPomXmlFileWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void generateTestDataFolder() {
		File testdataDir = new File(projectFullPath + "/testdata");
		testdataDir.mkdir();
		File testDataFileCopyFrom = new File(System.getProperty("user.dir") + "/" + podFileRelativePath);
		try {
			FileUtils.copyFileToDirectory(testDataFileCopyFrom, testdataDir, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File imgDir = new File(projectFullPath + "/testdata/img");
		imgDir.mkdirs();
	}

	private static void generateScriptsFolder() {
		new File(projectFullPath + "/script/js").mkdirs();
		new File(projectFullPath + "/script/autoit/fileUpload/chrome").mkdirs();
		new File(projectFullPath + "/script/autoit/fileUpload/firefox").mkdirs();
		new File(projectFullPath + "/script/autoit/fileUpload/ie").mkdirs();
		new File(projectFullPath + "/script/autoit/fileUpload/edge").mkdirs();
	}

	private static void generateConfigFolder() {
		new File(projectFullPath + "/config").mkdirs();
		FileWriter configFileWriter = null;
		try {
			String testDataFileName = new File(System.getProperty("user.dir") + "/" + podFileRelativePath).getName();
			System.out.println("Rendering config.properties");
			VelocityContext velocityContext = new VelocityContext();
			StringWriter writer = new StringWriter();
			velocityContext.put("testDataFileName", testDataFileName);
			velocityContext.put("baseUrl", baseUrl);
			File configFile = new File(projectFullPath, "/config/config.properties");
			configFileWriter = new FileWriter(configFile, false);
			Template template = velocityEngine.getTemplate("velocityTemplate/config/config.properties.vm");
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			configFileWriter.write(writer.toString());
			configFileWriter.flush();
		} catch (Exception e) {
			try {
				configFileWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	private static void generateTools() {
		String toolsFolder = projectFullPath + "/src/test/java/" + basePackageName.replace(".", "/") + "/tool";
		new File(toolsFolder).mkdirs();

		FileWriter browserUtilFileWriter = null;
		FileWriter configUtilFileWriter = null;
		FileWriter excelUtilFileWriter = null;
		FileWriter extentReporterFileWriter = null;
		FileWriter webDriverEventListenerFileWriter = null;
		try {
			System.out.println("Rendering tool java files");
			VelocityContext velocityContext = new VelocityContext();
			StringWriter writer = new StringWriter();
			velocityContext.put("basePackage", basePackageName);
			Template template;

			File browserUtilJavaFile = new File(toolsFolder, "BrowserUtil.java");
			browserUtilFileWriter = new FileWriter(browserUtilJavaFile, false);
			template = velocityEngine.getTemplate("velocityTemplate/tool/BrowserUtil.java.vm");
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			browserUtilFileWriter.write(writer.toString());
			browserUtilFileWriter.flush();

			File configUtilJavaFile = new File(toolsFolder, "ConfigUtil.java");
			configUtilFileWriter = new FileWriter(configUtilJavaFile, false);
			template = velocityEngine.getTemplate("velocityTemplate/tool/ConfigUtil.java.vm");
			writer = new StringWriter();
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			configUtilFileWriter.write(writer.toString());
			configUtilFileWriter.flush();

			File excelUtilJavaFile = new File(toolsFolder, "ExcelUtil.java");
			excelUtilFileWriter = new FileWriter(excelUtilJavaFile, false);
			template = velocityEngine.getTemplate("velocityTemplate/tool/ExcelUtil.java.vm");
			writer = new StringWriter();
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			excelUtilFileWriter.write(writer.toString());
			excelUtilFileWriter.flush();

			File extentReporterJavaFile = new File(toolsFolder, "ExtentReporter.java");
			extentReporterFileWriter = new FileWriter(extentReporterJavaFile, false);
			template = velocityEngine.getTemplate("velocityTemplate/tool/ExtentReporter.java.vm");
			writer = new StringWriter();
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			extentReporterFileWriter.write(writer.toString());
			extentReporterFileWriter.flush();

			File webDriverEventListenerJavaFile = new File(toolsFolder, "WebDriverEventListenerImpl.java");
			webDriverEventListenerFileWriter = new FileWriter(webDriverEventListenerJavaFile, false);
			template = velocityEngine.getTemplate("velocityTemplate/tool/WebDriverEventListenerImpl.java.vm");
			writer = new StringWriter();
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			webDriverEventListenerFileWriter.write(writer.toString());
			webDriverEventListenerFileWriter.flush();

		} catch (Exception e) {
			try {
				browserUtilFileWriter.close();
				configUtilFileWriter.close();
				excelUtilFileWriter.close();
				extentReporterFileWriter.close();
				webDriverEventListenerFileWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

	private static void generateWebDrivers() {
		File srcDir = new File(System.getProperty("user.dir") + "/webdriver");
		File dstDir = new File(projectFullPath + "/webdriver");
		dstDir.mkdirs();
		try {
			FileUtils.copyDirectory(srcDir, dstDir, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void generateScreenshotFolder() {
		new File(projectFullPath + "/screenshot").mkdirs();
	}

	private static void processPod() {
		int colNum = 1;
		Page page = null;
		List<String> pageDetails = null;
		while (true) {
			pageDetails = ExcelUtil.getPageDetails(colNum);
			if (pageDetails.isEmpty()) {
				break;
			} else {
				page = pageDetails2Page(pageDetails);
				pageName2PackageNameMap.put(page.getPageName(), page.getPackageName());
				pages.add(page);
				colNum++;
			}
		}
	}

	private static Page pageDetails2Page(List<String> pageDetails) {
		Page page = new Page();
		String className = null;
		String subPackageName = null;
		String parentClassName = null;
		Element element = null;
		String elementName = null;
		String elementType = null;
		String elementDependsOn = null;
		String desiredActionForElementDependsOn = null;
		String elementLocatorType = null;
		String elementLocatorValue = null;
		int index = 0;
		int remainder = 0;
		int size = pageDetails.size();
		page.setProjectPath(projectFullPath);
		page.setBasePackageName(basePackageName);
		while (index < size) {
			if (index <= 2) {
				switch (index) {
				case 0:
					className = pageDetails.get(index);
					page.setPageName(className);
					break;
				case 1:
					subPackageName = pageDetails.get(index);
					page.setSubPackageName(subPackageName);
					break;
				case 2:
					parentClassName = pageDetails.get(index);
					page.setParentPageName(parentClassName);
					break;
				}
			} else {
				remainder = (index - 3) % 5;
				switch (remainder) {
				case 0:
					element = new Element();
					elementName = pageDetails.get(index);
					element.setElementName(elementName);
					break;
				case 1:
					elementType = pageDetails.get(index);
					element.setElementType(elementType);
					break;
				case 2:
					String s = pageDetails.get(index);
					if (s != null) {
						String[] stringArray = s.split(",");
						if (stringArray.length == 1) {
							elementDependsOn = stringArray[0];
							desiredActionForElementDependsOn = DesiredActionTypesForElementDependsOn.goTo;
						} else if (stringArray.length == 2) {
							elementDependsOn = stringArray[0];
							desiredActionForElementDependsOn = stringArray[1];
						}
						element.setElementDependsOn(elementDependsOn);
						element.setDesiredActionForElementDependsOn(desiredActionForElementDependsOn);
					}
					break;
				case 3:
					elementLocatorType = pageDetails.get(index);
					element.setElementLocatorType(elementLocatorType);
					break;
				case 4:
					elementLocatorValue = pageDetails.get(index);
					element.setElementLocatorValue(elementLocatorValue);
					page.getElements().add(element);
					break;
				}
			}
			index++;
		}
		return page;
	}

	private static void fixMissingLinks() {
		for (Page somePage : pages) {
			somePage.setParentPackageName(pageName2PackageNameMap.get(somePage.getParentPageName()));
		}
	}

	private static void generatePageClass(Page page) {
		FileWriter javaFileWriter = null;
		try {
			System.out.println("Rendering page: " + page.getPageName());
			System.out.println(page);
			VelocityContext velocityContext = new VelocityContext();
			StringWriter writer = new StringWriter();
			String className = page.getPageName();
			String packageName = page.getPackageName();
			String parentClassName = page.getParentPageName();
			String parentPackageName = page.getParentPackageName();
			velocityContext.put("className", className);
			velocityContext.put("packageName", packageName);
			velocityContext.put("parentClassName", parentClassName);
			velocityContext.put("parentPackageName", parentPackageName);
			String packagePath = packageName.replace(".", "/");
			String pomBaseDirectory = projectFullPath + "/src/test/java/";
			String javaFileName = page.getPageName() + ".java";
			String javaFilePath = pomBaseDirectory + packagePath + "/" + javaFileName;
			File javaFile = new File(javaFilePath);
			javaFile.getParentFile().mkdirs();
			javaFileWriter = new FileWriter(javaFile);
			Template template = null;
			if (page.getPageName().equalsIgnoreCase("GenericPage")) {
				template = velocityEngine.getTemplate("velocityTemplate/page/rootPage.vm");
				template.merge(velocityContext, writer);
			}
			if (!page.getPageName().equalsIgnoreCase("GenericPage")) {
				template = velocityEngine.getTemplate("velocityTemplate/page/classPackage.vm");
				template.merge(velocityContext, writer);
				template = velocityEngine.getTemplate("velocityTemplate/page/classImport.vm");
				template.merge(velocityContext, writer);
				template = velocityEngine.getTemplate("velocityTemplate/page/classBegin.vm");
				template.merge(velocityContext, writer);
				for (Element element : page.getElements()) {
					String elementName = element.getElementName();
					String elementType = element.getElementType();
					String elementTypeDescription = "";
					if (elementType != null) {
						elementTypeDescription = elementTypeDefitions.get(elementType).toString();
					}
					String elementLocatorType = element.getElementLocatorType();
					String elementLocatorValue = element.getElementLocatorValue();
					velocityContext.put("elementName", element.getElementName());
					velocityContext.put("elementType", elementType);
					velocityContext.put("elementTypeDescription", elementTypeDescription);
					velocityContext.put("elementLocatorType", elementLocatorType);
					velocityContext.put("elementLocatorValue", elementLocatorValue);
					if (elementName == null) {
						System.out.println("WARNING: Element Name Absent. Skipping code generation for element " + elementName + "in page "
								+ page.getPageName());
						continue;
					}
					if (elementType == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/fieldLocatorWithBlankElementType.vm");
						template.merge(velocityContext, writer);
					} else {
						template = velocityEngine.getTemplate("velocityTemplate/page/fieldLocatorDescriptionForElementType.vm");
						template.merge(velocityContext, writer);
					}
					if (elementLocatorType != null && elementLocatorValue != null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/fieldLocator.vm");
						template.merge(velocityContext, writer);
						continue;
					}
					if (elementLocatorType != null && elementLocatorValue == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/fieldLocatorWithBlankLocatorValue.vm");
						velocityContext.put("locatorType", element.getElementLocatorType());
						template.merge(velocityContext, writer);
						continue;
					}
					if (elementLocatorType == null && elementLocatorValue == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/fieldLocatorWithBlankLocatorTypeAndValue.vm");
						velocityContext.put("elementName", element.getElementName());
						template.merge(velocityContext, writer);
					}
				}
				// Constructor
				template = velocityEngine.getTemplate("velocityTemplate/page/classConstructor.vm");
				template.merge(velocityContext, writer);
				// Methods
				for (Element element : page.getElements()) {
					velocityContext.put("elementName", element.getElementName());
					velocityContext.put("elementType", element.getElementType());
					velocityContext.put("elementDependsOn", element.getElementDependsOn());
					velocityContext.put("desiredActionForElementDependsOn", element.getDesiredActionForElementDependsOn());
					velocityContext.put("elementLocatorType", element.getElementLocatorType());
					velocityContext.put("elementLocatorValue", element.getElementLocatorValue());
					velocityContext.put("elementNameCapitalized", element.getElementNameCapitalized());
					velocityContext.put("elementDependsOnCapitalized", element.getElementDependsOnCapitalized());

					// def elementType methods
					if (element.getElementType().equals(ElementTypes.def) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodDefElement.vm");
						template.merge(velocityContext, writer);
					}
					if (element.getElementType().equals(ElementTypes.def) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodDefElementDependentGoTo.vm");
						template.merge(velocityContext, writer);

					}
					if (element.getElementType().equals(ElementTypes.def) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodDefElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// lbl elementType methods
					if (element.getElementType().equals(ElementTypes.lbl) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodLblElement.vm");
						template.merge(velocityContext, writer);
					}
					if (element.getElementType().equals(ElementTypes.lbl) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodLblElementDependentGoTo.vm");
						template.merge(velocityContext, writer);

					}
					if (element.getElementType().equals(ElementTypes.lbl) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodLblElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// txt elementType methods
					if (element.getElementType().equals(ElementTypes.txt) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtElement.vm");
						template.merge(velocityContext, writer);
					}
					if (element.getElementType().equals(ElementTypes.txt) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtElementDependentGoTo.vm");
						template.merge(velocityContext, writer);

					}
					if (element.getElementType().equals(ElementTypes.txt) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// singleSelList elementType methods
					if (element.getElementType().equals(ElementTypes.singleSelList) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodSingleSelListElement.vm");
						template.merge(velocityContext, writer);
					}
					if (element.getElementType().equals(ElementTypes.singleSelList) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodSingleSelListElementDependentGoTo.vm");
						template.merge(velocityContext, writer);

					}
					if (element.getElementType().equals(ElementTypes.singleSelList) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodSingleSelListElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// stack elementType methods
					if (element.getElementType().equals(ElementTypes.stack) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodStackElement.vm");
						template.merge(velocityContext, writer);
					}

					if (element.getElementType().equals(ElementTypes.stack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodStackElementDependentGoTo.vm");
						template.merge(velocityContext, writer);
					}

					if (element.getElementType().equals(ElementTypes.stack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodStackElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// txtInStack elementType methods
					if (element.getElementType().equals(ElementTypes.txtInStack) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtInStackElement.vm");
						template.merge(velocityContext, writer);
					}
					// TODO create .vm template
					if (element.getElementType().equals(ElementTypes.txtInStack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtInStackElementDependentGoTo.vm");
						template.merge(velocityContext, writer);

					}
					// TODO create .vm template
					if (element.getElementType().equals(ElementTypes.txtInStack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtInStackElementDependentClick.vm");
						template.merge(velocityContext, writer);
					}

					// singleSelListInStack elementType methods
					if (element.getElementType().equals(ElementTypes.singleSelListInStack) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodSingleSelListInStackElement.vm");
						template.merge(velocityContext, writer);
					}
					if (element.getElementType().equals(ElementTypes.singleSelListInStack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodSingleSelListInStackElementDependentGoTo.vm");
						template.merge(velocityContext, writer);

					}
					if (element.getElementType().equals(ElementTypes.singleSelListInStack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodSingleSelListInStackElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// defInStack elementType methods
					if (element.getElementType().equals(ElementTypes.defInStack) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodDefInStackElement.vm");
						template.merge(velocityContext, writer);
					}
					// TODO create .vm template
					if (element.getElementType().equals(ElementTypes.defInStack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodDefInStackElementDependentGoTo.vm");
						template.merge(velocityContext, writer);
					}
					// TODO create .vm template
					if (element.getElementType().equals(ElementTypes.defInStack) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodDefInStackElementDependentClick.vm");
						template.merge(velocityContext, writer);

					}

					// txtFileUploadTodo elementType methods
					if (element.getElementType().equals(ElementTypes.txtFileUpload) && element.getElementDependsOn() == null) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtFileUploadTodoElement.vm");
						template.merge(velocityContext, writer);
					}
					// TODO create .vm template
					if (element.getElementType().equals(ElementTypes.txtFileUpload) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.goTo)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtFileUploadTodoElementDependentGoTo.vm");
						template.merge(velocityContext, writer);
					}
					// TODO create .vm template
					if (element.getElementType().equals(ElementTypes.txtFileUpload) && element.getElementDependsOn() != null
							&& element.getDesiredActionForElementDependsOn().equals(DesiredActionTypesForElementDependsOn.click)) {
						template = velocityEngine.getTemplate("velocityTemplate/page/methodTxtFileUploadTodoElementDependentClick.vm");
						template.merge(velocityContext, writer);
					}

				}
				template = velocityEngine.getTemplate("velocityTemplate/page/classClose.vm");
				template.merge(velocityContext, writer);
			}

			System.out.println(writer.toString());
			System.out.println("****************************************");
			javaFileWriter.write(writer.toString());
			javaFileWriter.flush();

		} catch (

		IOException e) {
			e.printStackTrace();
		} finally {
			try {
				javaFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void generatePageValidatorClass(Page page) {
		if (!page.getPageName().equals("GenericPage")) {
			FileWriter pageValidatorFileWriter = null;
			System.out.println("Rendering pageValidator for: " + page.getPageName());
			try {
				File pageValidatorFile = new File(page.getPageValidatorPath());
				pageValidatorFile.getParentFile().mkdirs();
				pageValidatorFileWriter = new FileWriter(pageValidatorFile);

				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("page", page);
				velocityContext.put("elements", page.getElements());
				StringWriter writer = new StringWriter();
				Template template = velocityEngine.getTemplate("velocityTemplate/test/pageValidation/pageValidation.vm");
				template.merge(velocityContext, writer);
				System.out.println(writer.toString());
				System.out.println("****************************************");
				pageValidatorFileWriter.write(writer.toString());
				pageValidatorFileWriter.flush();

			} catch (

			IOException e) {
				e.printStackTrace();
			} finally {
				try {
					pageValidatorFileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void generateFunctionalTestcases() {
		// TODO Auto-generated method stub
	}

	private static void generateTestNgFile() {
		FileWriter testngFileWriter = null;
		try {
			System.out.println("Rendering testng.xml file");
			VelocityContext velocityContext = new VelocityContext();
			StringWriter writer = new StringWriter();
			velocityContext.put("baseUrl", baseUrl);
			velocityContext.put("basePackage", basePackageName);
			velocityContext.put("projectName", projectName);
			velocityContext.put("pages", pages);
			Template template = velocityEngine.getTemplate("velocityTemplate/testng/testng.xml.vm");
			File testngxmlFile = new File(projectFullPath, "testng.xml");
			testngFileWriter = new FileWriter(testngxmlFile, false);
			template.merge(velocityContext, writer);
			System.out.println(writer.toString());
			testngFileWriter.write(writer.toString());
			testngFileWriter.flush();
		} catch (Exception e) {
			try {
				testngFileWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void poc() {
		velocityEngine = new VelocityEngine();
		velocityEngine.init();
		Template classTemplate = velocityEngine.getTemplate("velocityTemplate/page/classBegin.vm");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("class", "HomePage");
		velocityContext.put("parentClass", "GenericPage");
		velocityContext.put("login", "VALUE");
		StringWriter writer = new StringWriter();
		classTemplate.merge(velocityContext, writer);
		System.out.println(writer.toString());
	}
}
