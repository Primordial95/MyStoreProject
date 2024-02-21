package com.mystore.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	static ExtentReports extentReports;
	static ExtentSparkReporter extentSparkReporter;
	static ExtentTest extentTest;

	public static void setExtent() {
		extentReports = new ExtentReports(); // engine
		extentSparkReporter = new ExtentSparkReporter("ExtentReport.html"); // reporter object
		extentReports.attachReporter(extentSparkReporter);

		extentReports.setSystemInfo("ProjectName", "MyStoreProject");
		extentReports.setSystemInfo("Tester", "Vivek");
		extentReports.setSystemInfo("OS", "Windows 10");
		extentReports.setSystemInfo("Browser", "Chrome");

	}

	public static void endReport() {
		extentReports.flush();
//			Desktop.getDesktop().browse(new File("ExtentReport.html").toURI()); for opening of reports automatically
	}
}
