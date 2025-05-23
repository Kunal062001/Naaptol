package test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pom.CartPage;
import pom.HomePage;
import pom.ProductDetailsPage;

@Listeners(test.Listeners.class)
public class ProductDetailsTest extends BaseTest {
	
	
	@Test
	public void verifyClickableProductFromHomePageToProductDetailsPage() {
		test=reports.createTest(getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver,"Cooker");
		homePage.clickOnSearchBtn();
		ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
		String title=homePage.getProductTitle(0);
		long price=homePage.productPrice(0);
		
		homePage.clickOnParticularProduct(0);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> i= handles.iterator();
		driver.switchTo().window(i.next());
		driver.switchTo().window(i.next());
		Assert.assertEquals(detailsPage.productTitleFromProductDetails(),title);
		Assert.assertEquals(detailsPage.productPrizeFromProductDetails(), price);
	}
	
	@Test
	public void verifyClickHereToBuyProductFromProductDetailsPageAndCartVisible() {
		test=reports.createTest(getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver,"Cooker");
		homePage.clickOnSearchBtn();
		homePage.clickOnParticularProduct(0);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> i= handles.iterator();
		driver.switchTo().window(i.next());
		driver.switchTo().window(i.next());
		
		ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		detailsPage.clickOnclickToBuyBtn(driver);
		
		CartPage cartPage=new CartPage(driver);
		cartPage.isDisplayCartHeadingOrNot();
		
	}
}
