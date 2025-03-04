package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {
	
	@FindBy (xpath ="//div[@id='square_Details']//h1") private WebElement productTitle;
	@FindBy (xpath = "//li[@id='productPriceDisplay']//span[@class='offer-price']") private WebElement productPrize;
	@FindBy (xpath = "//span[text()='Click here to Buy']") private WebElement clickToBuy;
	
	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String productTitleFromProductDetails() {
		return productTitle.getText();
	}
	
	public long productPrizeFromProductDetails() {
		return removeCommaAndAlphabetFromString(productPrize.getText().substring(0,5));
	}
	
	public void clickOnclickToBuyBtn() {
		clickToBuy.click();
	}
}
