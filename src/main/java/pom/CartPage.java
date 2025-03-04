package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
	
	@FindBy (xpath = "//h1[text()='My Shopping Cart: ']") private WebElement cartHeading;
	@FindBy (xpath = "//ul[@id='cartData']") private List<WebElement> products;
	@FindBy (xpath = "//div[@class='cart_info']//h2") private List<WebElement> productsTitles;
	@FindBy (xpath = "//div[@id='cartItems']//li[@class='head_UPrice']") private List<WebElement> productsPrice;
	
	
	public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public  Boolean isDisplayCartHeadingOrNot() {
		return cartHeading.isDisplayed();
	}
	
	public int countNoOfProducts() {
		return products.size();
	}
	
	public long getProductPrice(int index) {
		return removeCommaAndAlphabetFromString(productsPrice.get(index).getText().substring(3));
	}
	
	public String getProducTitle(int index) {
		return productsTitles.get(index).getText();
	}

}
