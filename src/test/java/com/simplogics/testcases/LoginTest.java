package com.simplogics.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;
import com.simplogics.base.TestBase;
import com.simplogics.utilities.TestUtil;

public class LoginTest extends TestBase {
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) throws InterruptedException, IOException {
		if (!(TestUtil.isTestRunnable("LoginTest", excel))) {

			throw new SkipException("Skipping the test " + "LoginTest".toUpperCase() + " as the Runmode is NO");
		}
		//Below skip used runmode inside the testcase
		/*
		 * if(!data.get("runmode").equals("Y")){ throw new
		* SkipException("Skipping the test " + "loginTest".toUpperCase() +
		 * "as the Runmode is NO");
		 * 
		 * }
		 */

		type("email_XPATH", data.get("email"));
		type("password_CSS", data.get("password"));
		click("loginbtn_XPATH");
		Thread.sleep(2000);
		log.debug("Login Successfull!!!");
		Thread.sleep(3000);
		// forcefully failing a test
		verifyEquals("Invalid login", "valid login");
		// Assert.fail("Invalid login");

	}

}
