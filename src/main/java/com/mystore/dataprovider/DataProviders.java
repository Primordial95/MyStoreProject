package com.mystore.dataprovider;

import org.testng.annotations.DataProvider;

import com.mystore.utility.ReadExcelFile;

public class DataProviders {
	ReadExcelFile rExcelFile = new ReadExcelFile();
	
	@DataProvider(name = "ProductSpec")
	public Object[][] getProductSpec() {
		return rExcelFile.readSheetData("ProductDetails");
	}
}
