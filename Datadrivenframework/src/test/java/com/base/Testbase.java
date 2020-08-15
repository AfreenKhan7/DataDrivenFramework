package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Testbase { 
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void setup() throws IOException {
		
		if(driver==null) {
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
	        config.load(fis);
	        log.debug("Config file loaded");
	        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
	        OR.load(fis);
	        log.debug("OR file loaded");
	        if(config.getProperty("browser").equals("firefox")) {
	        	//system.setProperty("webdriver.gecko.driver","gecko.exe");
	        	driver=new FirefoxDriver();
	        }else if(config.getProperty("browser").equals("chrome")) {
	        	System.setProperty("webdriver.chrome.driver","D:\\Eclipse\\DATADRIVENFRAMEWORK\\Datadrivenframework\\src\\test\\resources\\executable\\chromedriver.exe");
	        	driver=new ChromeDriver();
	        }else if(config.getProperty("browser").equals("IE")) {
	        	System.setProperty("webdriver.iedriver.driver",System.getProperty("user.dir")+"src\\test\\resources\\executable\\IEDriverServer.exe");
	        	driver=new InternetExplorerDriver();
	        }
	         driver.get(config.getProperty("testsiteurl"));
	         log.debug("Navigated to :" +  config.getProperty("testsiteurl"));
	         driver.manage().window().maximize();
	         //driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);

		}
	}
		public boolean isElementPresent(By by) {
			
			try {
				driver.findElement(by);
				return true;
			}catch(NoSuchElementException e) {
				return false;
			}
		}
	
	 @AfterSuite
	  public void teardown() {
		  
		  
			  driver.quit();
		 
		  log.debug("Execution completed");
	  }
}
