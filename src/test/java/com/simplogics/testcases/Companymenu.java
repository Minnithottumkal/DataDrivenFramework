package com.simplogics.testcases;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.simplogics.base.TestBase;
import com.simplogics.utilities.TestUtil;

public class Companymenu extends TestBase{
	@Test(priority=2)
	public void companymenu() throws InterruptedException, IOException{		
		if(!(TestUtil.isTestRunnable("companymenu", excel))){
			
			throw new SkipException("Skipping the test "+"Createvisits".toUpperCase()+ "as the Run mode is NO");
		}		
        click("Company_linkText");
        
       
        
}

}
