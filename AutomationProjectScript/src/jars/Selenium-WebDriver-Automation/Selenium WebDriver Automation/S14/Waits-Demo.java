package tutorial.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.WaitTypes;

public class WaitsDemo {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.expedia.com/";
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("header-history")).click();
		driver.findElement(By.id("tab-flight-tab")).click();
		
		WebElement origin = WaitTypes.getWhenVisible(driver, By.id("flight-origin"), 30);
		origin.sendKeys("New York");
		
		WaitTypes.clickWhenReady(driver, By.id("search-button"), 30);	
	}

	@After
	public void tearDown() throws Exception {
	}
}
