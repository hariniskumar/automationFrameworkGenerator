package afg;

import org.apache.commons.lang.StringUtils;

public class Element {
	private String elementName;
	private String elementType;
	private String elementDependsOn;
	private String desiredActionForElementDependsOn;
	private String elementLocatorType;
	private String elementLocatorValue;
	private String elementComment;

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementDependsOn() {
		return elementDependsOn;
	}

	public void setElementDependsOn(String elementDependsOn) {
		this.elementDependsOn = elementDependsOn;
	}

	public String getDesiredActionForElementDependsOn() {
		return desiredActionForElementDependsOn;
	}

	public void setDesiredActionForElementDependsOn(String desiredActionForElementDependsOn) {
		this.desiredActionForElementDependsOn = desiredActionForElementDependsOn;
	}

	public String getElementLocatorType() {
		return elementLocatorType;
	}

	public void setElementLocatorType(String elementLocatorType) {
		this.elementLocatorType = elementLocatorType;
	}

	public String getElementLocatorValue() {
		return elementLocatorValue;
	}

	public void setElementLocatorValue(String elementLocatorValue) {
		this.elementLocatorValue = elementLocatorValue;
	}

	public String getElementComment() {
		return elementComment;
	}

	public void setElementComment(String elementComment) {
		this.elementComment = elementComment;
	}

	public String getElementNameCapitalized() {
		return StringUtils.capitalize(elementName);
	}

	public String getElementDependsOnCapitalized() {
		return StringUtils.capitalize(elementDependsOn);
	}

	public boolean equals(Element e) {
		if (this.getElementName().equals(e.getElementName())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isDefElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.def);
	}

	public boolean isDefInStackElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.defInStack);
	}

	public boolean isLblElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.lbl);
	}

	public boolean isSingleSelListElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.singleSelList);
	}

	public boolean isSingleSelListInStackElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.singleSelListInStack);
	}

	public boolean isStackElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.stack);
	}

	public boolean isTxtElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.txt);
	}

	public boolean isTxtFileUploadElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.txtFileUpload);
	}

	public boolean isTagsTxtElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.tagsTxt);
	}

	public boolean isTagsTxtInStackElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.tagsTxtInStack);
	}

	public boolean isTagsSingleSelListElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.tagsSingleSelList);
	}

	public boolean isTagsSingleSelListInStackElementType() {
		return getElementType().equalsIgnoreCase(ElementTypes.tagsSingleSelListInStack);
	}

	public String toString() {
		String s = "[" + elementName + "," + elementType + "," + elementDependsOn + "," + elementLocatorType + "," + elementLocatorValue + "]";
		return s;
	}
}
