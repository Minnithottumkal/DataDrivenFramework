package com.simplogics.utilities;

public class TestConfig {
	public static String server="smtp.gmail.com";
	public static String from = "testingt919@gmail.com";
	public static String password = "Power123!";
	public static String[] to ={"minni.thottumkal@simplogics.com"};
	public static String subject = "Selenium Automation Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath="/home/appus/Desktop/SMTP/UdemyFramework/src/test/Files/logs/Selenium.log";
	public static String attachmentName="TestResult.log";
	
	
	
	//SQL DATABASE DETAILS	
	public static String driver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	//MYSQL DATABASE DETAILS
	public static String mysqldriver="com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/acs";
	
	

}
