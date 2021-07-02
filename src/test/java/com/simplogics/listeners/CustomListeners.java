package com.simplogics.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.simplogics.base.TestBase;
import com.simplogics.utilities.Email;
import com.simplogics.utilities.ExtentManager;
import com.simplogics.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {
	static String fileName = "Extent.html";
	public static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"/target/surefire-reports/html/"+fileName);
	static String messageBody;
	public static  ExtentTest test;
	static Date d = new Date();
	public void onTestStart(ITestResult result) {
		//test = rep.startTest(result.getName().toUpperCase());
		 test = extent.createTest(result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Testcase success : " + result.getName().toUpperCase());
		
	}

	public void onTestFailure(ITestResult result) {

		test.log(Status.FAIL, "Testcase failed : " + result.getName().toUpperCase());

		test.log(Status.FAIL, "Failure Response: " + result.getThrowable());
		try {
			test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getbase64()).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Testcase skipped :" + result.getName().toUpperCase());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {
		
		Email emil=new Email();
		emil.email();
		 File f= new
				 File("/home/appus/Desktop/SMTP/UdemyFramework/target/surefire-reports/html/Extent.html");
				 f.delete();
	}

}
