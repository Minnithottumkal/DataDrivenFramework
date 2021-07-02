package com.simplogics.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentManager {
	private static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;

	public static ExtentReports createInstance(String fileName) {
		htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.setAppendExisting(true);// works only for aventstack 3.1.5 dependency and relevantcodes 2.41.2
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Minni S Thottumkal");
		extent.setSystemInfo("Orgainzation", "Simplogics");
		return extent;

	} 

}
