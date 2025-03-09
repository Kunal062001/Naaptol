package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage {
	@FindBy (xpath = "//h3[normalize-space()='Your transaction is successful.']") private WebElement thanksMessage;
	
	public ThankYouPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getThanksMessage() {
		return thanksMessage.getText();
	}
}
