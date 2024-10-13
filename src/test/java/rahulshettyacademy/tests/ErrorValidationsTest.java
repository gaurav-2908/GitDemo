package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatelogue;

public class ErrorValidationsTest extends BaseTest{

	WebDriver driver;
		@Test(groups= {"Sanity"},retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("gaurav.jangde1@gmail.com", "APassword@12345");
		String loginError = landingPage.getErrorMessage();
		Assert.assertEquals(loginError, "Incorrect email or password.");
	}
		@Test
		public void ProductErrorValidation() {
			String productName = "ZARA COAT 3";
			
			ProductCatelogue productcatelogue = landingPage.loginApplication("testingqa123@test.com", "Password@1");
			List<WebElement> products = productcatelogue.getProductList();
			productcatelogue.addProduct(productName);
			CartPage cartpage = productcatelogue.goToCart();
			Boolean match = cartpage.checkProductonCartPage("ZARA COAT 33");
			Assert.assertFalse(match);
		}

}
