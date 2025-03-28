package excelUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public static HashMap<String, String> map1;

	public HashMap<String, String> getExcelData(String sheet, String Scenario, String path) throws IOException {

		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = workbook.getSheet(sheet);

		map1 = new HashMap<>();

		int rows = sheet1.getLastRowNum() + 1;

		for (int r = 0; r < rows; r++) {

			XSSFRow row = sheet1.getRow(r);
			if (Scenario.equals(row.getCell(0).getStringCellValue())) {
				int columcnt = sheet1.getRow(r).getLastCellNum();
				for (int c = 0; c < columcnt; c++) {

					XSSFRow row0 = sheet1.getRow(0);
					String heading = row0.getCell(c).getStringCellValue();

					String value;
					if (sheet1.getRow(r).getCell(c) == null) {
						value = "";
					} else {
						value = sheet1.getRow(r).getCell(c).getStringCellValue();
					}

					map1.put(heading, value);
				}
				break;
			}
		}
		return map1;
	}
	
	public Map<Integer, Map<String, String>> finalMap(Map<Integer, Map<String, String>> outer, String placeholder, String sheetname){
		Map<Integer, Map<String, String>>  newMap = new HashMap<Integer, Map<String, String>>();
		int outersize = outer.size();
		if(sheetname.equals("Sheet2")) {
			String[] RefArr = null;
			if(placeholder.contains("|")) {
				RefArr = placeholder.split("[|]");
			}
			else {
				RefArr = new String[] {placeholder};
			}
			boolean flag = false;
			for(int i=0,j=0;i<outersize;i++) {
				
				
				flag = Arrays.asList(RefArr).contains(outer.get(i).get("Mark Ref"));
				if(flag) {
					newMap.put(j, outer.get(i));
					
					j++;
				}
			}
		}
		
		return newMap;
		
	}

	public Map<Integer, Map<String, String>> readData(String path, String sheetname) throws EncryptedDocumentException, IOException {
		
		Map<Integer, Map<String, String>> outer = new HashMap<Integer, Map<String, String>>();
		Map<String, String> inner = new HashMap<String, String>();
		File file = new File(path);
		
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = book.getSheet(sheetname);
		
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		for(int i=1;i<=rows;i++) {
			if(!(sheet.getRow(i)==null)){
				inner = new HashMap<String, String>();
				String key;
				String value;
				for(int j=0;j<columns;j++) {
					key = sheet.getRow(0).getCell(j).getStringCellValue();
					if (sheet.getRow(i).getCell(j) == null) {
					    value = "";
					} else {
					    value = sheet.getRow(i).getCell(j).getStringCellValue();
					    inner.put(key, value);
					}
				}
				outer.put(i-1, inner);
			}
		}
		return outer;
		
	}
}
