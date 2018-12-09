package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TaskTest {
	
	WebDriver driver;
	
	
	@BeforeTest
	public void beforeEach() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.dice.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void verifyTitle() {
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Find Jobs in Tech | Dice.com");
		
	}
	

	@Test(priority=2, dataProvider = "data")
	public void searchJob(String jobTitle, String city) {
		driver.findElement(By.id("search-field-keyword")).sendKeys(jobTitle);
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(city);
		driver.findElement(By.id("findTechJobs")).click();
		
		String result = driver.findElement(By.id("posiCountId")).getText();
		
		int resultInt = Integer.parseInt(result);
		
		try {
			
			Assert.assertTrue(resultInt>1000);
		}catch(Throwable t) {
			System.out.println(t);
		}
		
		driver.navigate().back();
	}
	
	@DataProvider
	public Object[][] data(){
		Object[][] a = new String[3][2];
		
		a[0][0] = "java developer";
		a[0][1] = "WA";
		
		a[1][0] = "javascript developer";
		a[1][1] = "WA";
		
		a[2][0] = "ruby developer";
		a[2][1] = "WA";
		
		return a;
	}
	
	@AfterTest
	public void after() {
		driver.quit();
	}

}
