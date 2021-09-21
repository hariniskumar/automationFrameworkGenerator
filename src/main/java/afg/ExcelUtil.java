package afg;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static String podExcelFilePath;
	static Workbook workbook;
	static Sheet sheet;

	public static void init(String filePath) {
		podExcelFilePath = filePath;
	}

	public static List<String> getSheetNames() {
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}
		return sheetNames;
	}

	public static List<String> getEntitySheetNames() {
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (!workbook.getSheetName(i).startsWith("POD")) {
				sheetNames.add(workbook.getSheetName(i));
			}

		}
		return sheetNames;
	}

	public static Properties getElementTypeDefinition() {
		Properties glossary = new Properties();
		sheet = getSheet("POD-Element Types");
		int rowNum = 0;
		String name = null;
		String value = null;
		Row row;
		while (rowNum <= sheet.getLastRowNum()) {
			row = sheet.getRow(rowNum);
			if (row.getCell(0) != null) {
				name = row.getCell(0).getStringCellValue();
				value = row.getCell(1).getStringCellValue();
				glossary.put(name, value);
			}
			rowNum++;
		}
		return glossary;
	}

	public static List<String> getPageDetails(int columnNumber) {
		sheet = getSheet("POD");
		List<String> pageDetails = new ArrayList<String>();
		int rowNum = 0;
		Row row;
		Cell cell;
		while (rowNum <= sheet.getLastRowNum()) {
			row = sheet.getRow(rowNum);
			cell = row.getCell(columnNumber);
			if (cell == null) {
				if (rowNum == 0) {
					break;// no more pages, return emply list
				}
				pageDetails.add(null);
			} else {
				pageDetails.add(cell.getStringCellValue());
			}
			rowNum++;
		}
		int removeFromIndex = 0;
		// cleanup list, there are hundreds of null elements after the last
		// element
		for (int index = 3; index < pageDetails.size(); index = index + 5) {
			String elementName = pageDetails.get(index);
			String elementType = pageDetails.get(index + 1);
			String elementDependsOn = pageDetails.get(index + 2);
			String elementLocatoryType = pageDetails.get(index + 3);
			String elementLocatorValue = pageDetails.get(index + 4);
			if (elementName == null && elementType == null && elementDependsOn == null && elementLocatoryType == null
					&& elementLocatorValue == null) {
				removeFromIndex = index;
				while (removeFromIndex < pageDetails.size()) {
					pageDetails.remove(removeFromIndex);
				}
				break;
			}
		}
		return pageDetails;
	}

	private static Sheet getSheet(String sheetName) {
		Sheet sheet;
		InputStream is = null;
		try {
			try {
				is = new FileInputStream(podExcelFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (new File(podExcelFilePath).getName().toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(is);
			} else {
				workbook = new HSSFWorkbook(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	public static void main(String[] args) {
		System.out.println("Enter the name of the podfile relative to the current directory.");
		Scanner scanner = new Scanner(System.in);
		String podFileRelativePath = scanner.nextLine();
		scanner.close();
		podExcelFilePath = System.getProperty("user.dir") + "/" + podFileRelativePath;
		sheet = getSheet("pod");
		int colNum = 1;
		List<String> pageDetails = null;
		while (true) {
			pageDetails = getPageDetails(colNum);
			if (pageDetails.isEmpty())
				break;
			System.out.println(pageDetails.toString());
			colNum++;
		}
	}
}
