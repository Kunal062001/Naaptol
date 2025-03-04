package test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pom.CartPage;
import pom.HomePage;
import pom.ProductDetailsPage;

public class CartTest extends BaseTest {
	
	@Test
	public void clickSingleProductAddToCartAndVerifyInCart() {
		int count=0;
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver, "Cooker");
		homePage.clickOnSearchBtn();
		
		homePage.clickOnParticularProduct(0);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> i= handles.iterator();
		driver.switchTo().window(i.next());
		driver.switchTo().window(i.next());
		
		ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
		String title=detailsPage.productTitleFromProductDetails();
		long price=detailsPage.productPrizeFromProductDetails();
		detailsPage.clickOnclickToBuyBtn();
		count++;
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.countNoOfProducts(), count);
		Assert.assertEquals(cartPage.getProducTitle(0), title);
		Assert.assertEquals(cartPage.getProductPrice(0), price);		
		
	}
	
	@Test

	public void clickMultipleProductAddToCartAndVerifyInCart() {
		int count=0;
			HomePage homePage=new HomePage(driver);
			homePage.enterSearchProduct(driver, "Cooker");
			homePage.clickOnSearchBtn();
			
			homePage.clickOnParticularProduct(0);
			Set<String> handles=driver.getWindowHandles();
			Iterator<String> j= handles.iterator();
			driver.switchTo().window(j.next());
			driver.switchTo().window(j.next());
			
			ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
			String title1=detailsPage.productTitleFromProductDetails();
			long price1=detailsPage.productPrizeFromProductDetails();
			detailsPage.clickOnclickToBuyBtn();
			count++;
			
			driver.switchTo().defaultContent();
			homePage.clickOnParticularProduct(1);
			Set<String> handle1=driver.getWindowHandles();
			Iterator<String> i= handles.iterator();
			driver.switchTo().window(i.next());
			driver.switchTo().window(i.next());
			
			String title2=detailsPage.productTitleFromProductDetails();
			long price2=detailsPage.productPrizeFromProductDetails();
			detailsPage.clickOnclickToBuyBtn();
			count++;
			
			
			
			CartPage cartPage=new CartPage(driver);
			//Assert.assertEquals(cartPage.countNoOfProducts(), count);
			Assert.assertEquals(cartPage.getProducTitle(0), title1);
			Assert.assertEquals(cartPage.getProductPrice(0), price1);
			Assert.assertEquals(cartPage.getProducTitle(1), title2);
			Assert.assertEquals(cartPage.getProductPrice(1), price2);
			
	}
		

}
