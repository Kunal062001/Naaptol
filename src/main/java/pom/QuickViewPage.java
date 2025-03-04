package pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuickViewPage extends BasePage {
	
	
	@FindBy (xpath = "(//h1)[3]") private WebElement productTitle;
	@FindBy (xpath = "//li[@id='productPriceDisplay']//span[@class='offer-price']") private WebElement productPrize;
	
	public QuickViewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String title(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf(productTitle));
		return productTitle.getText();
	}

	public long price(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOf(productPrize));
		return removeCommaAndAlphabetFromString(productPrize.getText().substring(0,5));
	}
}
