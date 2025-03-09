package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentOptionsPage {
	@FindBy(xpath = "//input[@id='Cash On Delivery_radiobtn']") private WebElement codRadioBtn;
	@FindBy(xpath = "//input[@id='UPI_radiobtn']") private WebElement upiRadioBtn;
	@FindBy(xpath = "//input[@id='Net Banking_radiobtn']") private WebElement netBankingRadioBtn;
	@FindBy(xpath = "//input[@id='Credit Card_radiobtn']") private WebElement creditCardRadioBtn;
	@FindBy(xpath = "//input[@id='Debit Card_radiobtn']") private WebElement debitCardRadioBtn;
	@FindBy(xpath = "//input[@id='Debit Card_radiobtn']") private WebElement cashCardRadioBtn;
	@FindBy(xpath = "//a[normalize-space()='Click here to Place Order']") private WebElement placeOrderBtn;
	
	public PaymentOptionsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnCashOnDelivery() {
		codRadioBtn.click();
	}
	
	public void clickOnPlaceOrder() {
		placeOrderBtn.click();
	}
}
