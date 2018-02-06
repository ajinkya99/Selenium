package tutorial.selenium;

import java.util.concurrent.TimeUnit;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Dropdowns {
	private WebDriver driver;
	private String baseUrl1;
	private String baseUrl2;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl1 = "http://www.expedia.com/";
		baseUrl2 = "http://www.htmlcodetutorial.com/forms/_SELECT_MULTIPLE.html";
		
		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testDropdown() throws Exception {
		driver.get(baseUrl1);
		Select sel1 = new Select(driver.findElement(By.id("flight-adults")));
		Select sel2 = new Select(driver.findElement(By.id("flight-children")));
		
		List<WebElement> options = sel1.getOptions();
		int size = options.size();
		
		for (int i=0; i<size; i++) {
			String optionName = sel1.getOptions().get(i).getText();
			System.out.println(optionName);
		}
		
		// By Value
		sel1.selectByValue("2");
		
		// By Index
		sel2.selectByIndex(2);
	}
	
	@Test
	public void testMultiSelect() throws Exception {
		driver.get(baseUrl2);
		Select sel = new Select(driver.findElement(By.name("toppings")));
		sel.selectByValue("onions");
		Thread.sleep(2000);
		
		sel.deselectByValue("onions");
		Thread.sleep(2000);
		
		sel.selectByIndex(3);
		sel.selectByValue("olives");
		
		Thread.sleep(2000);
		sel.deselectAll();
		
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(6000);
	}
}
