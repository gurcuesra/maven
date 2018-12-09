package com.newtours;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	final String file = "/Users/esrakartal/Selenium/testing-maven/maven-properties-test/src/test/resources/config.properties";
	
	WebDriver driver=null;
	Properties prop;
	
	@BeforeTest
	public void setup() throws IOException {
		// Create an instance of class
		prop = new Properties();
		
		//Provide the path of the file to constructor of the class
		FileInputStream ip = new FileInputStream(file);
		
		prop.load(ip);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	@Test
	public void login() {
		driver.findElement(By.name("userName")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.name("login")).click();
	}
	
	
	@AfterTest
	public void closeUp() {
		driver.quit();
	}

}
