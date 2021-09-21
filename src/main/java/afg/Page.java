package afg;


import java.util.ArrayList;
import java.util.List;

public class Page {
	private String pageName;
	private String basePackageName;
	private String subPackageName;

	private String parentPageName;
	private String parentPackageName;
	private List<Element> elements = new ArrayList<Element>();

	private String projectPath;
	private String mavenPath = "/src/test/java/";

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}

	public String getSubPackageName() {
		return subPackageName;
	}

	public void setSubPackageName(String subPackageName) {
		this.subPackageName = subPackageName;
	}

	public String getParentPageName() {
		return parentPageName;
	}

	public void setParentPageName(String parentPageName) {
		this.parentPageName = parentPageName;
	}

	public String getParentPackageName() {
		return parentPackageName;
	}

	public void setParentPackageName(String parentPackageName) {
		this.parentPackageName = parentPackageName;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getMavenPath() {
		return mavenPath;
	}

	public void setMavenPath(String mavenPath) {
		this.mavenPath = mavenPath;
	}

	public String getPackageName() {
		return basePackageName + "." + subPackageName;
	}

	// auxillary methods for page

	public String getPageNameFullyQualified() {
		return getPackageName() + "." + getPageName();
	}

	public String getPageNameBeginLowerCase() {
		return Character.toLowerCase(getPageName().charAt(0)) + getPageName().substring(1);
	}

	public String getPagePath() {
		return projectPath + mavenPath + getPackageName().replace(".", "/") + "/" + pageName + ".java";
	}

	// auxillary methods for pageValidator

	public String getPageValidatorName() {
		return getPageName() + "Validator";
	}

	public String getPageValidatorNameFullyQualified() {
		return getPageValidatorPackageName() + "." + getPageName() + "Validator";
	}

	public String getPageValidatorPackageName() {
		return getPackageName().replace("page.", "test.pageValidation.");
	}

	public String getPageValidatorPath() {
		return projectPath + mavenPath + getPageValidatorPackageName().replace(".", "/") + "/" + getPageValidatorName() + ".java";
	}

	//other methods
	public boolean isGenericPage() {
		return getPageName().equalsIgnoreCase("GenericPage");
	}
	public String toString() {
		String s = pageName + "\n" + getPackageName() + "\n" + parentPageName + "\n" + parentPackageName + "\n";
		for (Element e : getElements()) {
			s = s + e.toString() + "\n";
		}
		return s;
	}
}
