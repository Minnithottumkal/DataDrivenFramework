package com.simplogics.base;
import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.simplogics.listeners.CustomListeners;
import com.simplogics.utilities.ExcelReader;
import com.simplogics.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;

	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/Files/excel/testdata.xlsx");
	public static String browser;

	@BeforeSuite
	public void setup() {
		if (driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {

				config.load(fis);
				log.debug("Config file Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			config.getProperty("browser");
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file Loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");

			} else {

				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {
				//require older version of firefox 59 for geckodriver and headless
				//System.setProperty("webdriver.gecko.driver",
					//	System.getProperty("user.dir") + "/src/test/Files/executables/geckodriver");
				//driver = new FirefoxDriver();
				//Supports firefox latest version
				WebDriverManager.firefoxdriver().setup();
				 driver=new FirefoxDriver();
				//headless browser setup
				//FirefoxOptions firefoxOptions = new FirefoxOptions();
				//firefoxOptions.addArguments("--headless");
				//firefoxOptions.addArguments("--window-size=1600,700");
			//	driver = new FirefoxDriver(firefoxOptions);

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/Files/executables/chromedriver");
				System.setProperty("webdriver.chrome.logfile", "./TestResult.log");
				System.setProperty("webdriver.chrome.verboseLogging", "true");
				//driver = new ChromeDriver();
				//driver.manage().window().setSize(new Dimension(1600, 700));
				 ChromeOptions options = new ChromeOptions();
				 options.addArguments("--headless");
				 options.addArguments("--window-size=1600,700");
				 driver = new ChromeDriver(options);
				log.debug("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/Files/executables/IEDriverServer");
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			// wait = new WebDriverWait(driver, 5);
		}

	}

	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_linkText")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
		CustomListeners.test.log(Status.INFO, "Clicking on : " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		CustomListeners.test.log(Status.INFO, "Typing in : " + locator + " entered value as " + value);
	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			CustomListeners.test.log(Status.FAIL, "Verifiation Failed:" + t.getMessage());
			CustomListeners.test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getbase64()).build());

		}

	}

	public static void Asserttoast(String expectedtoast, String locator) throws IOException {
		if (locator.endsWith("_CSS")) {
			String appearedtoast = driver.findElement(By.cssSelector(OR.getProperty(locator))).getText();
			try {
				assertEquals(appearedtoast, expectedtoast);
			} catch (Throwable t) {
				CustomListeners.test.log(Status.FAIL, "Verifiation Failed::" + t.getMessage());
				CustomListeners.test.fail("Please check the below Screenshot :",
						MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getbase64()).build());

			}
		} else if (locator.endsWith("_XPATH")) {
			String appearedtoast = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
			try {
				assertEquals(appearedtoast, expectedtoast);
			} catch (Throwable t) {
				CustomListeners.test.log(Status.FAIL, "Verifiation Failed::" + t.getMessage());
				CustomListeners.test.fail("Please check the below Screenshot :",
						MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getbase64()).build());

			}

		} else if (locator.endsWith("_ID")) {
			String appearedtoast = driver.findElement(By.id(OR.getProperty(locator))).getText();
			try {
				assertEquals(appearedtoast, expectedtoast);
			} catch (Throwable t) {
				CustomListeners.test.log(Status.FAIL, "Verifiation Failed:" + t.getMessage());
				CustomListeners.test.fail("Please check the below Screenshot :",
						MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getbase64()).build());

			}

		} else if (locator.endsWith("_linkText")) {
			String appearedtoast = driver.findElement(By.linkText(OR.getProperty(locator))).getText();
			try {
				assertEquals(appearedtoast, expectedtoast);
			} catch (Throwable t) {
				CustomListeners.test.log(Status.FAIL, "Verifiation Failed:" + t.getMessage());
				CustomListeners.test.fail("Please check the below Screenshot :",
						MediaEntityBuilder.createScreenCaptureFromBase64String(TestUtil.getbase64()).build());

			}

		}

	}

	@AfterSuite
	public void teardown() {
		if (driver != null) {
			driver.quit();

		}
		log.debug("Execution Completed");

	}

}
