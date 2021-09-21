package afg;

import java.io.StringWriter;
import java.util.Scanner;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class PomCodeSnippetGenerator {

	public static void main(String[] args) {

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
		Template template = null;
		Scanner scanner = new Scanner(System.in);

		boolean continueCodeGeneration = true;
		while (continueCodeGeneration) {
			Element element = new Element();
			VelocityContext velocityContext = new VelocityContext();
			StringWriter writer = new StringWriter();

			System.out.println("Enter Element Name:");
			element.setElementName(scanner.nextLine());

			System.out.println(
					"Enter Element Type:   1:def 2:lbl 3:txt 4:singleSelList 5:stack 6:txtInStack 7:singleSelListInStack 8:defInStack 9:txtFileUpload");
			switch (scanner.nextLine()) {
			case "1":
				element.setElementType("def");
				break;

			case "2":
				element.setElementType("lbl");
				break;

			case "3":
				element.setElementType("txt");
				break;

			case "4":
				element.setElementType("singleSelList");
				break;

			case "5":
				element.setElementType("stack");
				break;

			case "6":
				element.setElementType("txtInStack");
				break;

			case "7":
				element.setElementType("singleSelListInStack");
				break;

			case "8":
				element.setElementType("defInStack");
				break;

			case "9":
				element.setElementType("txtFileUpload");
				break;
			}

			System.out.println("Enter locator type:  1:xpath  2:css  3:id   4:name");
			switch (scanner.nextLine()) {
			case "1":
				element.setElementLocatorType("xpath");
				break;

			case "2":
				element.setElementLocatorType("css");
				break;

			case "3":
				element.setElementLocatorType("id");
				break;

			case "4":
				element.setElementLocatorType("name");
				break;
			}

			System.out.println("Enter locator value:");
			element.setElementLocatorValue(scanner.nextLine());

			System.out.println("Does the element depend on aother element for visibility?  No:1  Yes:2");

			switch (scanner.nextLine()) {
			case "1":
				break;

			case "2":
				System.out.println("Enter the name of such element:");
				element.setElementDependsOn(scanner.nextLine());
				System.out.println("How is it dependent?  1:onMouseOver   2:onClick");
				switch (scanner.nextLine()) {
				case "1":
					element.setDesiredActionForElementDependsOn(DesiredActionTypesForElementDependsOn.goTo);
					break;
				case "2":
					element.setDesiredActionForElementDependsOn(DesiredActionTypesForElementDependsOn.click);
					break;
				}
				break;
			}

			velocityContext.put("elementName", element.getElementName());
			velocityContext.put("elementType", element.getElementType());
			velocityContext.put("elementTypeDescription", "");
			velocityContext.put("elementLocatorType", element.getElementLocatorType());
			velocityContext.put("elementLocatorValue", element.getElementLocatorValue());
			velocityContext.put("elementDependsOn", element.getElementDependsOn());
			velocityContext.put("desiredActionForElementDependsOn", element.getDesiredActionForElementDependsOn());
			velocityContext.put("elementNameCapitalized", element.getElementNameCapitalized());
			velocityContext.put("elementDependsOnCapitalized", element.getElementDependsOnCapitalized());

			template = velocityEngine.getTemplate("velocityTemplate/page/fieldLocator.vm");
			template.merge(velocityContext, writer);

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

			System.out.println(writer.toString());
			System.out.println("****************************************");

			System.out.println("Like to generate code for another element? 1:Yes  2:any other key");
			if (!scanner.nextLine().equals("1")) {
				continueCodeGeneration = false;
			}
		}
		scanner.close();

	}

}
