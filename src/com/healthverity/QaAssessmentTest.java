package com.healthverity;

import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QaAssessmentTest {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	
	public static final String URL = "https://ec2instances.info/";
	public static final String DRIVER_LOCATION = "drivers/chromedriver";	

	
	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       
		//Navigate to url
		driver.navigate().to(URL);
		
		//Click on RDS
		driver.findElement(By.xpath("//a[contains(text(),'RDS')]")).click();

		//Get the Actual Header Name
		String actual_header = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(actual_header);
		String expected_header = ("EC2Instances.info Easy Amazon RDS Instance Comparison");

	    //Verify Header name
		Assert.assertEquals(actual_header, expected_header);
		System.out.println("Header is as expected");

		// Select Any Row
		driver.findElement(By.xpath("//tr[contains(@class,'instance odd')][15]")).click();
		
		//Get the background color for selected row
		String expected_color = ("#d3ffff");
		String actual_color = driver.findElement(By.id("db.r4.2xlarge")).getCssValue("background-color");
		String hexColor = Color.fromString(actual_color).asHex();

		// Verify the selected row color
		Assert.assertEquals(hexColor, expected_color);
		System.out.println("Background color is as expected");
		
		//Click on Compare selected
		
		 driver.findElement(By.xpath("//button[contains(text(),'Compare Selected')]")).click();
		 String expectedButtonCaption = driver.findElement(By.xpath("//button[1]")).getText();
		 
		 //Confirm the button Change
		 System.out.println(expectedButtonCaption);
		 Assert.assertEquals(expectedButtonCaption, "End Compare");

		// Confirm only one row is displayed

		int count = 0;
		List<WebElement> rows = driver.findElements(By.xpath("//table//tbody/tr"));
		for (WebElement row : rows) {
			if (row.isDisplayed())
				count++;
		}
		System.out.println("The number of rows that are visible is: " + count);
        Assert.assertTrue(count == 1 );

		
		driver.close();
	}
	

}
