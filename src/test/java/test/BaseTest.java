package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pojo.Browser;


public class BaseTest {
	public static WebDriver driver;
	public static ExtentReports reports;
	public static ExtentSparkReporter html;
	public static ExtentTest test;
	
	
	@BeforeMethod
	public void launchBrowser() {
		driver=Browser.launchbrowser();
	}
	
	@AfterMethod
	public void exitBrowser() {
		driver.quit();
	}
	
	
	
	
	
}
