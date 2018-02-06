package tutorial.selenium;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import page.classes.SearchPage;

//@Listeners(utilities.SeleniumListeners.class)
public class TestNG_TestCase {
	private WebDriver driver;
	
	public WebDriver getDriver() {
        return driver;
	}
        
	private String baseUrl;
	static Logger log = Logger.getLogger(UsingLog4j.class);

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		baseUrl = "https://www.expedia.com/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		PropertyConfigurator.configure("log4j.properties");
	}
	@BeforeMethod
	public void beforeMethod() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testMethod() throws Exception {
		driver.get(baseUrl);
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.fillOriginTextBox(driver, "New York");
		SearchPage.fillDestinationTextBox(driver, "Chicago");
	}
	
	@Test
	public void testMethod1() throws Exception {
		SearchPage.fillDepartureDateTextBox(driver, "12/25/2014");
		SearchPage.fillReturnDateTextBox(driver, "12/31/2014");
	}

	@AfterMethod
	public void afterMethod() {
	}

}
