package tutorialselenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropActions {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.dhtmlx.com/docs/products/dhtmlxTree/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testDragAndDrop() throws Exception {
		driver.get(baseUrl);
		Thread.sleep(1000);
		
		WebElement fromElement1 = driver.findElement(By.xpath("//div[@id='treebox1']//span[text()='James Johns']"));
		WebElement toElement1 = driver.findElement(By.xpath("//div[@id='treebox2']//span[text()='Bestsellers']"));
		
		Actions action = new Actions(driver);
		
		// Click and hold, move to element, release, build and perform
		action.clickAndHold(fromElement1).perform();
		Thread.sleep(1000);
		action.moveToElement(toElement1).perform();
		Thread.sleep(1000);
		action.release(toElement1).perform();
		
//		THIS CODE IS NOT WORKING WITH LATEST SELENIUM JARS, I HAVE VERIFIED IT NOT WORKING FROM AT LEAST 2.46
//		IT IS POSSIBLE THAT IT MAY NOT WORK WITH OTHER VERSIONS ALSO
//		PLEASE USE THE ABOVE METHOD TO DRAG AND DROP UNTILL THIS IS FIXED BY SELENIUM
//		I HAVE NOT FILED ANY BUG FOR SELENIUM, BUT IF YOU LIKE YOU CAN FILE A BUG FOR SELENIUM ON GITHUB
		
//		WebElement fromElement2 = driver.findElement(By.xpath("//div[@id='treebox1']//span[text()='Ian Rankin']"));
//		WebElement toElement2 = driver.findElement(By.xpath("//div[@id='treebox1']//span[text()='Bestsellers']"));
//		
//		// Drag and drop
//		action.dragAndDrop(fromElement2, toElement2).build().perform();
	}

	@After
	public void tearDown() throws Exception {
		// Thread.sleep(3000);
		driver.quit();
	}
}
