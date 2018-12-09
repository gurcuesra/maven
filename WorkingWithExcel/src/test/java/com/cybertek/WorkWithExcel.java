package com.cybertek;

public class WorkWithExcel {

	public static void main(String[] args) {
		
		String path = "/Users/esrakartal/desktop/testData.xlsx";
		Xls_Reader data = new Xls_Reader(path);
		
		int rCount = data.getRowCount("data");
		System.out.println(rCount);
		
		String cdata = data.getCellData("data", "Name", 2);
		System.out.println(cdata);
		
		int ccount = data.getColumnCount("data");
		System.out.println(ccount);
		
		

	}

}
