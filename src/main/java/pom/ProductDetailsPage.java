package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage extends BasePage {
	
	@FindBy (xpath ="//div[@id='square_Details']//h1") private WebElement productTitle;
	@FindBy (xpath = "//li[@id='productPriceDisplay']//span[@class='offer-price']") private WebElement productPrize;
	@FindBy (xpath = "//li[@id='productPriceDisplay']//span[@class='ship-price']") private WebElement productShippingPrize;
	@FindBy (xpath = "//a[@id='cart-panel-button-0']") private WebElement clickToBuy;
	
	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String productTitleFromProductDetails() {
		return productTitle.getText();
	}
	
	public long productPrizeFromProductDetails() {
		return removeCommaAndAlphabetFromString(productPrize.getText().substring(0,5));
	}
	
	public long productShippingPriceFromProductDetails() {
		return removeCommaAndAlphabetFromString(productShippingPrize.getText().substring(2,5));
	}
	
	public void clickOnclickToBuyBtn(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2000));
		wait.until(ExpectedConditions.visibilityOf(clickToBuy));
		clickToBuy.click();
	}
	
	public void visibilityOfAddToCartBtn(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2000));
		wait.until(ExpectedConditions.visibilityOf(clickToBuy));
	}
	
	public void hoverAndClickToCart(WebDriver driver) {
		Actions actions=new Actions(driver);
		actions.moveToElement(clickToBuy);
	}
}
