package excelUtility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static String ExcelPath = System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";
	public static HashMap<String,String> map;
	public static Map<Integer, Map<String, String>> map2;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ExcelData data = new ExcelData();
		map = data.getExcelData("Sheet1", "R001", ExcelPath);
		
		System.out.println("Student Data :"+map);
		
		map2 = data.finalMap(data.readData(ExcelPath, "Sheet2"), map.get("Mark Reference"), "Sheet2");
		System.out.println(map2);
	}

}
