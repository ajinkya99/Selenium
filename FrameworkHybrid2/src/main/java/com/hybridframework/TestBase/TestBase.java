package com.hybridframework.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.hybridframework.Excelreader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase 
{
	public static final Logger logger = Logger.getLogger(TestBase.class);
	
	public WebDriver driver;
	public Properties OR;
	public File f1;
	public FileInputStream file;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public ExcelReader excelR;
	
	static // want to execute only once //Resource will be availabe before any test case 
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "\\src\\main\\java\\com\\hybridframework\\report\\test" + formater.format(calendar.getTime()) + ".html", false);
		//false means data will not be overwritten
	}
	public void getBrowser(String browser)
	{
		if(System.getProperty("os.name").contains("Window"))  //For Windows
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.out.println("Windows " + System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\jars\\chromedriver_win32 (1)\\chromedriver.exe");
			    driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				System.out.println("Windows " + System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.18.0-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		else if(System.getProperty("os.name").contains("Mac"))   // For Mac (changes .exe should be removed from path)
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\jars\\chromedriver_win32 (1)\\chromedriver");
			    driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				
				System.setProperty("webdriver.gecko.marionette", System.getProperty("user.dir")+"\\src\\jars\\geckodriver-v0.18.0-win64\\geckodriver");
				driver = new FirefoxDriver();	
			}	
		}
	}
	}
	
	public void loadPropertiesdata() throws IOException    // Loading All property files
	{
		String log4jConfigPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfigPath);
		
		
		OR = new Properties();  //Properties class is used and its object is created 'OR'
		f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\hybridframework\\config\\config.properties");    // File is used  //path to config.properties file
	    file = new FileInputStream(f1);  // To read the data
		OR.load(file);
		logger.info("Loading Config propertoes");
		
		f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\hybridframework\\config\\or.properties");    // File is used  //path to config.properties file
	    file = new FileInputStream(f1);  // To read the data
		OR.load(file);
		logger.info("Loading OR propertoes");
		
		f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\hybridframework\\config\\homepage.properties");    // File is used  //path to config.properties file
	    file = new FileInputStream(f1);  // To read the data
		OR.load(file);
		logger.info("Loading Homepage propertoes");
	}
	
        public String getScreenShot(String imageName) throws IOException{
		
		if(imageName.equals("")){
			imageName = "blank";
		}
		File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagelocation = System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/screenshot/";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);
		return actualImageName;
}
	
	public void  getPropertiesdata()
	{
		
	}
	
	public WebElement waitforelement(WebDriver driver, long time, WebElement element)  //Explicitwait
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebElement waitforelementWithPollingInterval(WebDriver driver, long time, WebElement element)   //Explicitwait
	{
		WebDriverWait wait = new WebDriverWait(driver,time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void implicitWait(long time) //Implicit wait
	{
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " Test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " Test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " Test is failed" + result.getThrowable());
			String screen = getScreenShot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " Test is started");
		}
}
	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}
	
	@AfterClass(alwaysRun = true)
	public void endTest() {
		//driver.quit();
		extent.endTest(test);
		extent.flush();
}
	
	public WebElement getLocator(String locator) throws Exception
	{
	        String[] split = locator.split(":");     //element and locator seprated by ':'
			String locatorType = split[0];
			String locatorValue = split[1];
			System.out.println("locatorType:-"+locatorType);
			System.out.println("locatorValue:-"+locatorValue);
			if (locatorType.toLowerCase().equals("id"))
				return driver.findElement(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElement(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
				return driver.findElement(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
				return driver.findElement(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElement(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
				return driver.findElement(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElement(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public List<WebElement> getLocators(String locator) throws Exception
	{
	        String[] split = locator.split(":");     //element and locator seprated by ':'
			String locatorType = split[0];
			String locatorValue = split[1];
			//System.out.println("locatorType:-"+locatorType);
			//System.out.println("locatorValue:-"+locatorValue);
			if (locatorType.toLowerCase().equals("id"))
				return driver.findElements(By.id(locatorValue));
			else if (locatorType.toLowerCase().equals("name"))
				return driver.findElements(By.name(locatorValue));
			else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
				return driver.findElements(By.className(locatorValue));
			else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
				return driver.findElements(By.linkText(locatorValue));
			else if (locatorType.toLowerCase().equals("partiallinktext"))
				return driver.findElements(By.partialLinkText(locatorValue));
			else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
				return driver.findElements(By.cssSelector(locatorValue));
			else if (locatorType.toLowerCase().equals("xpath"))
				return driver.findElements(By.xpath(locatorValue));
			else
				throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public WebElement getWebElement(String locator) throws Exception{      
		return getLocator(OR.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception{
        return getLocators(OR.getProperty(locator));
	}
	
	public String[][] getData(String excelName,String sheetName)
	{
        excelR = new ExcelReader();
		String excellocation = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\hybridframework\\data\\" + excelName;
		ExcelReader excel = new ExcelReader();
		return excelR.getExcelData(excellocation, sheetName);
	}
	

	public static void main(String[] args) throws Exception
	{
		TestBase test = new TestBase();         // Object of the class
		//test.getBrowser("chrome");
		test.loadPropertiesdata();
		//System.out.println(test.OR.getProperty("url"));
		//System.out.println(test.OR.getProperty("test"));
		//test.OR.getProperty("url");
		System.out.println(test.OR.getProperty("password"));
		test.getWebElement("username");
		
	}

}
