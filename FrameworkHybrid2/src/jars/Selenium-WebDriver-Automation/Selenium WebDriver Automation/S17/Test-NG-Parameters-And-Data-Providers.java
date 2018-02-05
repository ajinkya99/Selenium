package tutorial.selenium;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import page.classes.SearchPage;

public class TestNG_ParametersAndDataProviders {
	private WebDriver driver;
	private String baseUrl;
	static Logger log = Logger.getLogger(TestNG_DependentTests.class);
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		baseUrl = "https://www.expedia.com/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
		driver.get(baseUrl);
	}
	
	@DataProvider(name="fieldsInputs")
	public static Object[][] searchData() {
		return new Object[][] {{"New York", "Chicago", "10/28/2014", "10/31/2014"},
				{"New York", "Boston", "12/28/2014", "12/31/2014"}};
	}
	
	@Parameters( { "origin", "dest", "depDate", "retDate" } )
	@Test
	public void searchFlights(String origin, String dest,
			String depDate, String retDate) throws Exception {
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.fillOriginTextBox(driver, origin);
		SearchPage.fillDestinationTextBox(driver, dest);
		SearchPage.fillDepartureDateTextBox(driver, depDate);
		SearchPage.fillReturnDateTextBox(driver, retDate);
	}
	
	@Test(dataProvider="fieldsInputs")
	public void searchFlightsWithMultiData(String origin, String dest,
			String depDate, String retDate) throws Exception {
		SearchPage.navigateToFlightsTab(driver);
		SearchPage.clearAllFields(driver);
		Thread.sleep(3000);
		SearchPage.fillOriginTextBox(driver, origin);
		SearchPage.fillDestinationTextBox(driver, dest);
		SearchPage.fillDepartureDateTextBox(driver, depDate);
		SearchPage.fillReturnDateTextBox(driver, retDate);
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
	}

}
