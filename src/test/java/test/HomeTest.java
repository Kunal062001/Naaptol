package test;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pom.CartPage;
import pom.HomePage;
import pom.QuickViewPage;
import pom.VendorPage;
@Listeners(test.Listeners.class)
public class HomeTest extends BaseTest{
	
	@Test
	public void verifyUrlIsStableOrNot() {
		test=reports.createTest(reports.getClass().getName());
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.naaptol.com/");
	}
	
	@Test
	public void verifyVendorButtonWorkingOrNot() {
		test=reports.createTest(reports.getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.clickOnVendorBtn();
		
		VendorPage vendorPage=new VendorPage(driver);
		Assert.assertTrue(vendorPage.headingDisplayed());
	}
	
	@Test
	public void verifyProductsIsVisibleInShoppingCategories() {
		test=reports.createTest(getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.hoverOnShoppingCategories(driver);
		Assert.assertTrue(homePage.productsIsDisplayedOrNot(0));
	}

	@Test
	public void verifySearchProductToProductInformation() {
		test=reports.createTest(getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver,"Cooker");
		homePage.clickOnSearchBtn();
		String title=homePage.getProductTitle(0);
		if(title.contains("Cooker") || title.contains("cooker")){
			Assert.assertTrue(true);
		}
		else Assert.fail();
	}
	
	@Test
	public void verifyInvalidSearchProductToProductInformation() {
		test=reports.createTest(getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver, "MacBook");
		homePage.clickOnSearchBtn();
		Assert.assertTrue(homePage.errorMessage());
	}
	
	@Test
	public void verifyProductDetailsInQuickView() {
		test=reports.createTest(getClass().getName());
		QuickViewPage quickView=new QuickViewPage(driver);
		HomePage homePage=new HomePage(driver);
		homePage.enterSearchProduct(driver,"Cooker");
		homePage.clickOnSearchBtn();
		String productTitle=homePage.getProductTitle(0);
		long productPrize=homePage.productPrice(0);
		homePage.hoverOnProductAndClickQuickView(driver,0);
		
		Assert.assertEquals(quickView.title(driver),productTitle);
		Assert.assertEquals(quickView.price(driver), productPrize);
	}
	
	@Test
	public void verifyAfterClickCartBtnCartVisibleOrNot() {
		test=reports.createTest(getClass().getName());
		HomePage homePage=new HomePage(driver);
		homePage.clickOnCart();
		
		CartPage cartPage=new CartPage(driver);
		Assert.assertTrue(cartPage.isDisplayCartHeadingOrNot());
	}
}
