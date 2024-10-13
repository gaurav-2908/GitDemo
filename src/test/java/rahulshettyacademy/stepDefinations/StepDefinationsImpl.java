package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatelogue;

public class StepDefinationsImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatelogue productCatelogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on eCommerce page")
	public void I_landed_on_eCommerce_page() throws IOException{
		landingPage = launchApplication();
	}
	
	@Given("^I login with username (.+) and password (.+)$")
	public void I_login_with_username_and_password(String userName, String password) {
		productCatelogue = landingPage.loginApplication(userName, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productname) {
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProduct(productname);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void And_checkout_productName_and_submit_the_order(String productName) {
		CartPage cartpage = productCatelogue.goToCart();
		
		Boolean match = cartpage.checkProductonCartPage(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goTOCheckout();
		
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String confirmationMsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void validation_message_displayed(String string) {
		String loginError = landingPage.getErrorMessage();
		Assert.assertEquals(loginError, string);
		driver.close();
	}
	

}
