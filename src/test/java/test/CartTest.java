package test;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pom.CartPage;
import pom.HomePage;
import pom.ProductDetailsPage;

public class CartTest extends BaseTest {
	
	@Test
	public void clickSingleProductAddToCartAndVerifyInCart() throws InterruptedException {
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
		long shippingPrice=detailsPage.productShippingPriceFromProductDetails();
		detailsPage.clickOnclickToBuyBtn(driver);
		count++;
		
		CartPage cartPage=new CartPage(driver);

		Assert.assertEquals(cartPage.countNoOfProducts(driver),count);
		Assert.assertEquals(cartPage.getProducTitle(0), title);
		Assert.assertEquals(cartPage.getProductPrice(0), price);
		Assert.assertEquals(cartPage.getProductShippingPrice(0), shippingPrice);
	}
	
	@Test
	public void clickMultipleProductAddToCartAndVerifyInCart() {
		 	int count=0;
		 	String title2="";
		 	long price2=0;
			HomePage homePage=new HomePage(driver);
			homePage.enterSearchProduct(driver, "Cooker");
			homePage.clickOnSearchBtn();
			String parentHandle=driver.getWindowHandle();
			
			homePage.clickOnParticularProduct(0);
			
			Set<String> handles=driver.getWindowHandles();
			Iterator<String> j= handles.iterator();
			driver.switchTo().window(j.next());
			driver.switchTo().window(j.next());
			
			ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
			String title1=detailsPage.productTitleFromProductDetails();
			long price1=detailsPage.productPrizeFromProductDetails();
			detailsPage.clickOnclickToBuyBtn(driver);
			count++;
			
			driver.switchTo().window(parentHandle);
			String switchTitle=homePage.getProductTitle(1);
			
			homePage.clickOnParticularProduct(1);
//			
//			Set<String> hand1=driver.getWindowHandles();
//			ArrayList list=new ArrayList(hand1);
//			System.out.println(list.size());
//			for(int i=0;i<list.size();i++) {
//				WebDriver dri=driver.switchTo().window((String) list.get(i));
//				if(dri.getCurrentUrl()== switchTitle) {
//					title2=detailsPage.productTitleFromProductDetails();
//					price2=detailsPage.productPrizeFromProductDetails();
//					detailsPage.visibilityOfAddToCartBtn(driver);
//					detailsPage.clickOnclickToBuyBtn(driver);
//					count++;
//				
//				}
//			}
			
			
//			
			Set<String> handle1=driver.getWindowHandles();
			Iterator<String> i= handles.iterator();
			while(i.hasNext()) {
				driver.switchTo().window(i.next());
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
				String title=driver.getTitle();
				if(title.contains(switchTitle)) {
					title2=detailsPage.productTitleFromProductDetails();
					price2=detailsPage.productPrizeFromProductDetails();
					detailsPage.visibilityOfAddToCartBtn(driver);
					detailsPage.clickOnclickToBuyBtn(driver);
					count++;
					break;
				}
			}				
			
			CartPage cartPage=new CartPage(driver);
			//Assert.assertEquals(cartPage.countNoOfProducts(), count);
			Assert.assertEquals(cartPage.getProducTitle(0), title1);
			Assert.assertEquals(cartPage.getProductPrice(0), price1);
			Assert.assertEquals(cartPage.getProducTitle(1), title2);
			Assert.assertEquals(cartPage.getProductPrice(1), price2);			
	}
	
	@Test
	public void verifyRemoveFromCart() throws InterruptedException {
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
		detailsPage.clickOnclickToBuyBtn(driver);
		count++;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		CartPage cartPage=new CartPage(driver);
		cartPage.clickOnRemoveBtn(0);
		count--;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		if(count==0) {
			Assert.assertEquals(cartPage.getEmptyMessage(),"You have No Items in Cart !!!");
		}
		else {
			Assert.assertEquals(cartPage.countNoOfProducts(driver), count);
		}		
	}
	
	@Test
	public void verifyCalculationOfUnitPriceAndShippingPriceToOrderAmount() {
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver, "Cooker");
		homePage.clickOnSearchBtn();
		
		homePage.clickOnParticularProduct(0);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> i= handles.iterator();
		driver.switchTo().window(i.next());
		driver.switchTo().window(i.next());
		
		ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
		detailsPage.clickOnclickToBuyBtn(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		CartPage cartPage=new CartPage(driver);
		long unitPrice=cartPage.getProductPrice(0);
		long shippingPrice=cartPage.getProductShippingPrice(0);
		Assert.assertEquals(cartPage.getOrderPrice(0), (unitPrice+shippingPrice));
	}
	
	@Test
	public void verifyCalculationOfOrderAmountAfterIncreasingQuantity() throws InterruptedException {
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver, "Cooker");
		homePage.clickOnSearchBtn();
		
		homePage.clickOnParticularProduct(0);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> i= handles.iterator();
		driver.switchTo().window(i.next());
		driver.switchTo().window(i.next());
		
		ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
		detailsPage.clickOnclickToBuyBtn(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		CartPage cartPage=new CartPage(driver);
		long orderAmount=cartPage.getOrderPrice(0);
		long shippingAmount=cartPage.getProductShippingPrice(0);
		cartPage.clearQuantity(driver,0);
		cartPage.sendQuantities(0, "2");
		Thread.sleep(5000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		Assert.assertEquals(cartPage.getOrderPrice(0),(orderAmount*2)-shippingAmount);		
	}
	
	@Test
	public void verifyCalculationCartAmountAndGiftVoucher() {
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver, "Cooker");
		homePage.clickOnSearchBtn();
		
		homePage.clickOnParticularProduct(0);
		Set<String> handles=driver.getWindowHandles();
		Iterator<String> i= handles.iterator();
		driver.switchTo().window(i.next());
		driver.switchTo().window(i.next());
		
		ProductDetailsPage detailsPage=new ProductDetailsPage(driver);
		detailsPage.clickOnclickToBuyBtn(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		CartPage cartPage=new CartPage(driver);
		Assert.assertEquals(cartPage.getTotalAmount(),(cartPage.getCartAmount()+cartPage.getGiftVoucher()));
	}
		

}
