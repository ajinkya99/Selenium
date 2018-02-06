package tutorial.selenium;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Table {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.dhtmlx.com/docs/products/dhtmlxGrid/";

		// Maximize the browser's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testTableData() {
		driver.get(baseUrl);
		String book = "The Green Mile";
		String authorActual = "Stephen King";
		String authorExpected = getAuthors(book);
		
		System.out.println("The author from the table is: " + authorExpected);
		assertTrue(authorActual.equals(authorExpected));
	}
	
	public String getAuthors(String book) {
		int index = -1;
		WebElement element = driver.findElement(By.id("gridbox"));
		List<WebElement> rows = element.findElements(By.xpath("//tr[contains(@class,'skyblue')]"));
		
		for (int i=0; i<rows.size(); i++) {
			if (rows.get(i).getText().contains(book)) {
				index = i;
				System.out.println("The index is: " + i);
				break;
			}
		}
		
		WebElement author = rows.get(index).findElements(By.tagName("td")).get(2);
		return author.getText();
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
