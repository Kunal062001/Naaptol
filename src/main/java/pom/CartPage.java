package pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
	
	@FindBy (xpath = "//h1[text()='My Shopping Cart: ']") private WebElement cartHeading;
	@FindBy (xpath = "//ul[@id='cartData']") private List<WebElement> products;
	@FindBy (xpath = "//div[@class='cart_info']//h2//a") private List<WebElement> productsTitles;
	@FindBy (xpath = "//div[@id='cartItems']//li[@class='head_UPrice']") private List<WebElement> productsPrice;
	@FindBy (xpath = "//div[@id='cartItems']//li[@class='head_ship']") private List<WebElement> productShippingPrice;
	@FindBy (xpath = "//div[@id='cartItems']//li[@class='head_Amount']") private List<WebElement> orderPrice;
	@FindBy (xpath = "//input[@class='input_Special_2']") private List<WebElement> quantities;
	@FindBy (xpath = "//ul[@id='cartTotal']//li//label") private WebElement cartAmount;
	@FindBy (xpath = "//span[@id='cvDiscount']") private WebElement giftVoucher;
	@FindBy (xpath = "//span[@id='totalPayableAmount']") private WebElement total;
	@FindBy (xpath = "//a[text()='Remove']") private List<WebElement> removeBtn;
	@FindBy (xpath = "//span[text()='You have No Items in Cart !!! ']") private WebElement emptyMessage;
	
	
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public  Boolean isDisplayCartHeadingOrNot() {
		return cartHeading.isDisplayed();
	}
	
	public int countNoOfProducts(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfAllElements(products));
		return products.size();
	}
	
	public long getProductPrice(int index) {
		return removeCommaAndAlphabetFromString(productsPrice.get(index).getText().substring(3));
	}
	
	public long getProductShippingPrice(int index) {
		return removeCommaAndAlphabetFromString(productShippingPrice.get(index).getText().substring(3));
	}
	
	public long getOrderPrice(int index) {
		return removeCommaAndAlphabetFromString(orderPrice.get(index).getText());
	}
	
	public String getProducTitle(int index) {
		return productsTitles.get(index).getText();
	}
	
	public void clearQuantity(WebDriver driver,int index) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = '';", quantities.get(index));
	}
	
	public void sendQuantities(int index,String quantity) {
		quantities.get(index).sendKeys(quantity);
	}
	
	public long getCartAmount() {
		return removeCommaAndAlphabetFromString(cartAmount.getText());
	}
	
	public long getGiftVoucher() {
		return removeCommaAndAlphabetFromString(giftVoucher.getText());
	}
	
	public long getTotalAmount() {
		return removeCommaAndAlphabetFromString(total.getText());
	}
	
	public void clickOnRemoveBtn(int index) {
		removeBtn.get(index).click();
	}
	
	public String getEmptyMessage() {
		return emptyMessage.getText();
	}
	
	

}
