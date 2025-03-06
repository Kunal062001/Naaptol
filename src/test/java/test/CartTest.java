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
public class CartTest extends BaseTest {
	
	@Test
	public void clickSingleProductAddToCartAndVerifyInCart() throws InterruptedException {
		test=reports.createTest(getClass().getName());
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
	public void clickMultipleProductAddToCartAndVerifyInCart() throws InterruptedException {
		test=reports.createTest(getClass().getName());
		int count = 0;
	    HomePage homePage = new HomePage(driver);
	    homePage.enterSearchProduct(driver, "Cooker");
	    homePage.clickOnSearchBtn();
	    String parentHandle = driver.getWindowHandle();  

	    homePage.clickOnParticularProduct(0);
	    switchToNewWindow(parentHandle);

	    ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
	    String title1 = detailsPage.productTitleFromProductDetails();
	    long price1 = detailsPage.productPrizeFromProductDetails();
	    detailsPage.clickOnclickToBuyBtn(driver);
	    count++;

	    driver.close();
	    driver.switchTo().window(parentHandle);  

	    homePage.clickOnParticularProduct(1);
	    switchToNewWindow(parentHandle);

	    String title2 = detailsPage.productTitleFromProductDetails();
	    long price2 = detailsPage.productPrizeFromProductDetails();
	    detailsPage.clickOnclickToBuyBtn(driver);
	    count++;

	    driver.close();
	    driver.switchTo().window(parentHandle);
	    
	    CartPage cartPage=new CartPage(driver);
	    homePage.clickOnCart();
	    
	    Assert.assertEquals(cartPage.countNoOfProducts(driver), count);
		Assert.assertEquals(cartPage.getProducTitle(0), title2);
		Assert.assertEquals(cartPage.getProductPrice(0), price2);
		Assert.assertEquals(cartPage.getProducTitle(1), title1);
		Assert.assertEquals(cartPage.getProductPrice(1), price1);		
	}
	
	@Test
	public void verifyRemoveFromCart() throws InterruptedException {
		test=reports.createTest(getClass().getName());
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
		test=reports.createTest(getClass().getName());
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
		test=reports.createTest(getClass().getName());
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
		Assert.assertEquals(cartPage.getOrderPrice(0),(orderAmount*2)-shippingAmount);		
	}
	
	@Test
	public void verifyTotalOrdersAmountToCartAmount() {
		test=reports.createTest(getClass().getName());
		 HomePage homePage = new HomePage(driver);
		    homePage.enterSearchProduct(driver, "Cooker");
		    homePage.clickOnSearchBtn();
		    String parentHandle = driver.getWindowHandle();  

		    homePage.clickOnParticularProduct(0);
		    switchToNewWindow(parentHandle);

		    ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
		    detailsPage.clickOnclickToBuyBtn(driver);

		    driver.close();
		    driver.switchTo().window(parentHandle);  

		    homePage.clickOnParticularProduct(1);
		    switchToNewWindow(parentHandle);

		    detailsPage.clickOnclickToBuyBtn(driver);

		    driver.close();
		    driver.switchTo().window(parentHandle);
		    
		    CartPage cartPage=new CartPage(driver);
		    homePage.clickOnCart();
		    long price1= cartPage.getOrderPrice(0);
			long price2= cartPage.getOrderPrice(1);
			long finalOrderAmount = price1 + price2;
			
			Assert.assertEquals(cartPage.getCartAmount(),finalOrderAmount);
	}
	
	@Test
	public void verifyCalculationCartAmountAndGiftVoucher() {
		test=reports.createTest(getClass().getName());
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
