package tutorial.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class SliderActions {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.dhtmlx.com/docs/products/dhtmlxSlider/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testSliderActions() throws Exception {
		driver.get(baseUrl);
		Thread.sleep(3000);
		
		// Using the actions class
		WebElement element = driver.findElement(By.xpath("//div[@id='sliderBox2']//div[@class='dhxsl_runner']"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(element, 100, 0).build().perform();

		// Using the text box
		Thread.sleep(3000);
		WebElement textElement = driver.findElement(By.id("rate"));
		textElement.clear();
		textElement.sendKeys("0");
		textElement.sendKeys(Keys.RETURN);
		
		Thread.sleep(4000);
		textElement.clear();
		textElement.sendKeys("30");
		textElement.sendKeys(Keys.RETURN);
	}

	@After
	public void tearDown() throws Exception {
	}
}
