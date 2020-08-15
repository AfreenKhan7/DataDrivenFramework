package com.testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.Testbase;

public class LoginTest extends Testbase{

	
	@Test
	public void login() throws InterruptedException {
		log.debug("Inside login Test");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")).sendKeys("9179612504");
		driver.findElement(By.xpath(OR.getProperty("Cancelbutton"))).click();
		Thread.sleep(3000);
		
		
        log.debug("Login successful");
	}
}
