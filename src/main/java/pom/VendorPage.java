package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorPage {
	
	@FindBy (xpath = "//span[@id='pageHeading']") private WebElement pageHeading;
	
	public VendorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public boolean headingDisplayed() {
		return pageHeading.isDisplayed();
	}
}
