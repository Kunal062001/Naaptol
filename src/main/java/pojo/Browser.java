package pojo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
	public static WebDriver launchbrowser() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.naaptol.com/");
		driver.manage().window().maximize();
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		return driver;
	}
}
