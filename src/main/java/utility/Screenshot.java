package utility;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	public static void screenshot(WebDriver driver,String name) {
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//screenshots.//"+name+".png");
	}

}
