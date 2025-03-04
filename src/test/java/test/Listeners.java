package test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import utility.ExtenReports;
import utility.Screenshot;

public class Listeners extends BaseTest implements ITestListener {
	
	public void onStart(ITestContext context) {
		reports=ExtenReports.addReports();
	}
	
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL,result.getName());
		try {
			Screenshot.screenshot(driver, result.getName());
		}catch(Exception e) {
			
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getName());
	}
	
	public void onFinish(ITestContext context) {
		reports.flush();
	}

}
