package com.cybertek;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelTaskTest {
	
	String path = "/Users/esrakartal/desktop/taskData.xlsx";
	Xls_Reader data = new Xls_Reader(path);
	WebDriver driver;
	List<String> list = new ArrayList<>();
	
	@BeforeTest
	public void before() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.get("https://www.dice.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test(priority=1, dataProvider="dataP")
	public void testCase(String title) {
		driver.findElement(By.id("search-field-keyword")).sendKeys(title + " developer");
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys("WA");
		driver.findElement(By.id("findTechJobs")).click();
		String result = driver.findElement(By.id("posiCountId")).getText();
		list.add(result);
		driver.navigate().back();
	}
	
	@DataProvider
	public Object[] dataP() {
		Object[] a = new String[data.getRowCount("Task")-1];
		a[0] = data.getCellData("Task", "Title", 2);   // Java
		a[1] = data.getCellData("Task", "Title", 3);   // JavaScript
		a[2] = data.getCellData("Task", "Title", 4);   // Ruby
		a[3] = data.getCellData("Task", "Title", 5);   // VBScript
		a[4] = data.getCellData("Task", "Title", 6);   // Python
		
		return a;
	}
	
	@Test(priority=2)
	public void settingData() {
		for(int i=0; i<list.size(); i++) {
			data.setCellData("Task", "Result", i+2, list.get(i));
		}
		for(int p=0; p<list.size(); p++) {
			System.out.println(data.getCellData("Task", "Result", p+2));
		}
		
		/*Output:
		 * 972
		 * 916
		 * 896
		 * 871
		 * 983
		 */
		
	}
	
	@AfterTest
	public void after() {
		driver.quit();
	}

}
