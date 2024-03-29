package com.qait.MobileTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class CalculatorAppMobileTest {
	AndroidDriver<MobileElement> driver;

	@BeforeClass
	public void setUp() throws MalformedURLException{
		//Set up desired capabilities and pass the Android app-activity and app-package to Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "9.0"); 
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("platformName","Android");
	 
	   
	   capabilities.setCapability("appPackage", "com.android.calculator2");
	// This package name of your app (you can get it from apk info app)
		capabilities.setCapability("appActivity","com.android.calculator2.Calculator"); // This is Launcher activity of your app (you can get it from apk info app)
	//Create RemoteWebDriver instance and connect to the Appium server
	 //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
	   driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	}

	@Test
	public void testCal() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   //locate the Text on the calculator by using By.name()
	   MobileElement two=driver.findElement(By.id("com.android.calculator2:id/digit_9"));
	   two.click();
	   MobileElement plus=driver.findElement(By.id("com.android.calculator2:id/op_add"));
	   plus.click();
	   MobileElement four=driver.findElement(By.id("com.android.calculator2:id/digit_4"));
	   four.click();
	   MobileElement equalTo=driver.findElement(By.id("com.android.calculator2:id/eq"));
	   equalTo.click();
	   //locate the edit box of the calculator by using By.tagName()
	   MobileElement results=driver.findElement(By.id("com.android.calculator2:id/result"));
		//Check the calculated value on the edit box
	   //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	assert results.getText().equals("13"):"Actual value is : "+results.getText()+" did not match with expected value: 6";

	}

	@AfterClass
	public void teardown(){
		//close the app
		driver.quit();
	}
	}