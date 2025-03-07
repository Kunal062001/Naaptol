package pom;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	
	@FindBy (xpath = "//span[normalize-space()='Shopping Categories']") private WebElement shoppingCategories;
	@FindBy (xpath = "//div[@id='mainMenuContent']//li") private List<WebElement> categories;
	@FindBy (xpath = "//input[@id='header_search_text_fix']") private WebElement search;
	@FindBy (xpath = "//form[@id='header_search']//div[@class='search']//a[@href='javascript:autoSuggestion.headerSearch()']") private WebElement searchIcon;
	@FindBy (xpath = "//div[@class='grid_Square ']") private List<WebElement> products;
	@FindBy (xpath = "//div[@class='item_title']") private List<WebElement> productsTitle;
	@FindBy (xpath = "//span[@class='offer-price']") private List<WebElement> productPrize;
	@FindBy (xpath = "//span[text()='Quick View']") private WebElement quickView;
	@FindBy (xpath = "//div[@class='errorMsg']") private WebElement errorMessage;
	@FindBy (xpath = "//span[@class='cartIcon']") private WebElement cartBtn;
	@FindBy (xpath = "//p[@class='call']//span") private WebElement vendorBtn;
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void hoverOnShoppingCategories(WebDriver driver) {
		Actions action=new Actions(driver);
		action.moveToElement(shoppingCategories).perform();
	}
	
	public int countNoOfProductsInShoppingCategories() {
		return categories.size();
	}
	
	public boolean productsIsDisplayedOrNot(int index) {
		return categories.get(index).isDisplayed();
	}
	
	public void enterSearchProduct(WebDriver driver,String name) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value',arguments[1])",search,name);
	}
	
	public void clickOnSearchBtn() {
		searchIcon.click();
	}
	
	public void clickOnCart() {
		cartBtn.click();
	}
	
	public String getProductTitle(int index) {
		return productsTitle.get(index).getText();
	}
	
	public boolean errorMessage() {
		return errorMessage.isDisplayed();
	}
	
	public long productPrice(int index) {
		return removeCommaAndAlphabetFromString(productPrize.get(index).getText());
	}
	
	public void hoverOnProductAndClickQuickView(WebDriver driver,int index) {
		Actions actions =new Actions(driver);
		actions.moveToElement(products.get(index)).perform();
		quickView.click();		
	}
	
	public void clickOnParticularProduct(int index) {
		products.get(index).click();
	}
	
	public void clickOnVendorBtn() {
		vendorBtn.click();
	}
}
