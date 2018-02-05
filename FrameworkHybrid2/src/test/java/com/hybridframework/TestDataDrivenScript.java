package com.hybridframework;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybridframework.TestBase.TestBase;

public class TestDataDrivenScript extends TestBase 
{
	@DataProvider(name="TestData")
	public  Object[][] dataSource()
	{
		return getData("TestData.xlsx", "LoginTestData");
	}
	@Test(dataProvider="TestData")
	public void testLogin(String UserName,String Password,String RunMode)
	{
		System.out.println("UserName -" +UserName);
		System.out.println("Password -" +Password);
		System.out.println("RunMode -" +RunMode);
	}

}
