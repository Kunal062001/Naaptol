package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy (xpath = "//input[@id='gc-registration-basic-panel-mobile']") private WebElement mobileNumber;
	@FindBy (xpath = "//input[@id='gc-registration-basic-panel-submit']") private WebElement continueBtn;
	@FindBy (xpath = "//input[@id='gc-registration-otp-panel-otp']") private WebElement otpTextField;
	@FindBy (xpath = "//input[@id='gc-registration-otp-panel-submit']") private WebElement otpPanelSubmit;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterMobileNumber(String number) {
		mobileNumber.sendKeys(number);
	}
	
	public void clickOnContinueBtn() {
		continueBtn.click();
	}
	
	public void enterOtp(String otp) {
		otpTextField.sendKeys(otp);
	}
	
	public void clickOnSubmitBtn() {
		otpPanelSubmit.click();
	}
}
