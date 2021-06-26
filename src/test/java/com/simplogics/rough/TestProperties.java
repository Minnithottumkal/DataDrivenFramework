package com.simplogics.rough;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	public static void main(String[] args) throws IOException {
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
		config.load(fis);
		config.getProperty("browser");
		Properties OR = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
		OR.load(fis);
	}

}
