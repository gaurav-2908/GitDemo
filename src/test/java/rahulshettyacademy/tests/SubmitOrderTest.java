package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrdersPage;
import rahulshettyacademy.pageObjects.ProductCatelogue;

public class SubmitOrderTest extends BaseTest{

	WebDriver driver;
	String productName1 = "ZARA COAT 3";
	String country = "India";
		@Test(dataProvider="getDate")
		public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
			
		ProductCatelogue productcatelogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatelogue.getProductList();
		productcatelogue.addProduct(input.get("productName"));
		CartPage cartpage = productcatelogue.goToCart();
		
		Boolean match = cartpage.checkProductonCartPage(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goTOCheckout();
		
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		String confirmationMsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));
	}
		
		@Test(dependsOnMethods={"SubmitOrder"})
		public void OrderHistory() {
			ProductCatelogue productcatelogue = landingPage.loginApplication("gaurav.jangde1@gmail.com", "Password@1");
			OrdersPage ordersPage = landingPage.goToOrders();
			Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName1));
			
		}
		
		
		
		
		@DataProvider
		public Object[][] getDate() throws IOException {
			//return new Object[][] {{"gaurav.jangde1@gmail.com","Password@1","ZARA COAT 3"},{"testingqa123@test.com","Password@1","ZARA COAT 3"}};
			/*HashMap <String,String> map = new HashMap<String,String>();
			map.put("email", "gaurav.jangde1@gmail.com");
			map.put("password", "Password@1");
			map.put("productName", "ZARA COAT 3");
			
			HashMap<String,String>map1 = new HashMap<String,String>();
			map1.put("email", "testingqa123@test.com");
			map1.put("password", "Password@1");
			map1.put("productName", "ZARA COAT 3");*/
			
			
			List<HashMap<String,String>> data = getJsonDataToMap((System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"));
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		

}
