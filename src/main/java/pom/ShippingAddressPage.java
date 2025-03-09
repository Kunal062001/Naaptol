package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShippingAddressPage {
	
	@FindBy (xpath = "//select[@id='fktitle_id']") private WebElement title;
	@FindBy (xpath = "//input[@id='firstName']") private WebElement firstName;
	@FindBy (xpath = "//input[@id='lastName']") private WebElement lastName ;
	@FindBy (xpath = "//textarea[@id='address']") private WebElement address ;
	@FindBy (xpath = "//input[@id='landmark']") private WebElement landMark;
	@FindBy (xpath = "//input[@id='pincode']") private WebElement pinCode;
	@FindBy (xpath = "//select[@id='state']") private WebElement state;
	@FindBy (xpath = "//select[@id='city']") private WebElement city;
	@FindBy (xpath = "//input[@id='mobile']") private WebElement mobileNumber;
	@FindBy (xpath = "//input[@id='landline']") private WebElement landline;
	@FindBy (xpath = "//a[@id='addEditButton']") private WebElement saveAndProceedBtn ;
	@FindBy (xpath = "//a[@id='shipAddress1']") private WebElement shipToThisAddressBtn ;
//	@FindBy (xpath = "") private WebElement ;
	
	public ShippingAddressPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectTitle(WebDriver driver,String titleString) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(title));
		Select select = new Select(title);
		select.selectByVisibleText(titleString);
	}
	
	public void enterFirstName(String firstname) {
		firstName.sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) {
		lastName.sendKeys(lastname);
	}
	
	public void enterAddress(String addr) {
		address.sendKeys(addr);
	}
	
	public void enterLandmark(String landmark) {
		landMark.sendKeys(landmark);
	}
	
	public void enterPincode(String code) {
		pinCode.sendKeys(code);
	}
	
	public void selectState(WebDriver driver,String stateName) {
		Select select = new Select(state);
		select.selectByVisibleText(stateName);
	}
	
	public void selectCity(WebDriver driver,String cityName) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(city));
		Select select = new Select(city);
		select.selectByVisibleText(cityName);
	}
	
	public void enterMobileNumber(String number) {
		mobileNumber.sendKeys(number);
	}
	
	public void clickOnSaveAndProceedBtn() {
		saveAndProceedBtn.click();
	}
	
	public void clickOnShipOnThisAddress() {
		shipToThisAddressBtn.click();
	}
	

}
