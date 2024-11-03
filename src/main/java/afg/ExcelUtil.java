package afg;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	public static String excelFilePath;
	static Workbook workbook;
	static Sheet sheet;

	public static void init(String filePath) {
		excelFilePath = filePath;
	}
	
	public static Object[][] getStringDataMatrix(String sheetName) {
		Sheet sheet = getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	public static Object[][] get2DimArrayWith1stColNullAnd2ndColStringDataMap(String sheetName) {
		Sheet sheet = getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][1];
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<String, String> datamap = new HashMap<String, String>();
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				Object headerCell = null;
				Object cell = null;
				String key = null;
				String value = null;
				if ((headerCell = sheet.getRow(0).getCell(k)) != null) {
					key = headerCell.toString();
					if ((cell = sheet.getRow(i).getCell(k)) != null) {
						value = cell.toString();
					} else {
						value = "";
					}
					datamap.put(key, value); // put only when key is not null
				}

			}
			data[i - 1][0] = datamap;
		}
		return data;
	}

	public static List<Map<String, String>> getListOfDataMaps(String sheetName) {
		Sheet sheet = getSheet(sheetName);
		ArrayList<Map<String, String>> listOfDataMaps = new ArrayList<>();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<String, String> datamap = new HashMap<String, String>();
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				Cell headerCell = null;
				Cell cell = null;
				String key = null;
				String value = null;
				if ((headerCell = sheet.getRow(0).getCell(k)) != null) {
					key = headerCell.toString();
					if ((cell = sheet.getRow(i).getCell(k)) != null) {
						//value = cell.toString();
						value = cell.getStringCellValue();
					} else {
						value = "";
					}
					datamap.put(key, value); // put only when key is not null
				}
			}
			listOfDataMaps.add(datamap);
		}
		return listOfDataMaps;
	}

	static Sheet getSheet(String sheetName) {
		Sheet sheet;
		InputStream is = null;
		try {
			try {
				is = new FileInputStream(excelFilePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (new File(excelFilePath).getName().toLowerCase().endsWith("xlsx")) {
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
	
	public static List<String> getSheetNames() {
		List<String> sheetNames = new ArrayList<String>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			sheetNames.add(workbook.getSheetName(i));
		}
		return sheetNames;
	}

	public static void main(String[] args) {
		Object[][] data = get2DimArrayWith1stColNullAnd2ndColStringDataMap("Companies");
		data[0][0].toString();
	}
}
