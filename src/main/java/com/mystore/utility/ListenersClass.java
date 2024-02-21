package com.mystore.utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mystore.actiondriver.ActionDriver;
import com.mystore.base.BaseClass;

public class ListenersClass extends ExtentManager implements ITestListener { // TestListernerAdapter

	ActionDriver actionDriver = new ActionDriver();

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Pass Test Case is :: " + result.getName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " Test Case Failed ", ExtentColor.RED));
			extentTest.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " Test Case Failed ", ExtentColor.RED));
			String img = actionDriver.screenShot(BaseClass.getDriver(), result.getName());
			extentTest.fail("Screenshot is Attached :: ", MediaEntityBuilder.createScreenCaptureFromPath(img).build());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test is Skipped :: " + result.getName());
		}
	}
}
