package com.simplogics.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.simplogics.base.TestBase;
import com.simplogics.utilities.TestUtil;

public class CreateCompany extends TestBase{
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp",priority=3)
	public void createCompany(Hashtable<String, String> data) throws InterruptedException, IOException{
        if(!(TestUtil.isTestRunnable("createCompany", excel))){
			
			throw new SkipException("Skipping the test "+"createCompany".toUpperCase()+ "as the Run mode is NO");
		}		
				
		click("Create_Button_CSS");
		click("Click_name_CSS");
		type("Company_Name_CSS",data.get("COMPANYNAME"));
		type("Company_Code_CSS",data.get("COMPANYCODE"));
		type("Company_Domain_CSS",data.get("COMPANYDOMAIN"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,1000)");  
		click("Create_CSS");
		
		Thread.sleep(2000);
		Asserttoast(OR.getProperty("expected_companycreate_toast"),"Company_create_toast_CSS");
		
	}	

}
