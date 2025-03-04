package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtenReports {
	public static ExtentReports addReports() {
		ExtentSparkReporter htmlReporter=new ExtentSparkReporter("extentReport.html");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Machine","Kunal");
		extent.setSystemInfo("Env","SIT");
		extent.setSystemInfo("Testing", "Regression");
		extent.setSystemInfo("Browser", "Chrome");
		return extent;
	}
}
