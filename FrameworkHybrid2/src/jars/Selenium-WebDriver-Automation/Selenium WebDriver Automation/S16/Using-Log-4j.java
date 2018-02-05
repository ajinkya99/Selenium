package tutorial.selenium;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import page.classes.SearchPage;

public class UsingLog4j {
	private WebDriver driver;
	private String baseUrl;
	static Logger log = Logger.getLogger(UsingLog4j.class);

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.expedia.com/";
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PropertyConfigurator.configure("log4j.properties");
	}

	@Test
	public void test() {
		driver.get(baseUrl);
		SearchPage.navigateToFlightsTab(driver);
		//log.info("Navigate to flights tab");
		SearchPage.fillOriginTextBox(driver, "New York");
		//log.info("Enter origin city");
		SearchPage.fillDestinationTextBox(driver, "Chicago");
		//log.info("Enter destination city");
		SearchPage.departureDateTextBox(driver).sendKeys("12/25/2014");
		//log.info("Enter departure date");
		SearchPage.returnDateTextBox(driver).sendKeys("12/31/2014");
		//log.info("Enter return date");
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
