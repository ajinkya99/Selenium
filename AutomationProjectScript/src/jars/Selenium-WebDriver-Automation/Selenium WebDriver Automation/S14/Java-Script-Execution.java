package tutorial.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaScriptExecution {
	private WebDriver driver;
	private JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testJavaScriptExecution() throws Exception {
		// Navigation
		// driver.get(baseUrl);
		js.executeScript("window.location = 'https://www.expedia.com/'");
		driver.findElement(By.id("tab-flight-tab")).click();
		
		Thread.sleep(2000);
		// Finding Element
		// driver.findElement(By.id("flight-origin")).sendKeys("New York");
		
		WebElement flight_origin = null;
		flight_origin = (WebElement) js.executeScript("return document.getElementById('flight-origin');");
		flight_origin.sendKeys("New York");
		driver.findElement(By.id("header-history")).click();
		
		Thread.sleep(2000);
		
		WebElement flight_destination = null;
		flight_destination = (WebElement) js.executeScript("return document.getElementById('flight-destination');");
		flight_destination.sendKeys("New York");
		
		driver.findElement(By.id("flight-departing")).sendKeys("09/03/2014");
		driver.findElement(By.id("flight-returning")).clear();
		driver.findElement(By.id("flight-returning")).sendKeys("09/10/2014");
		
		// Changing the style
		js.executeScript("document.getElementById('flight-destination').style.borderColor = 'Red'");
		
		// Getting Attributes
		// String attribute =  driver.findElement(By.id("flight-origin")).getAttribute("type");
		String attribute = (String) js.executeScript("return document.getElementById('flight-origin').getAttribute('type');");
		System.out.println("The attribute is: " + attribute);
		
		// Size of window
		long size = (Long) js.executeScript("return window.innerHeight;");
		System.out.println("Size is: " + size);
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
