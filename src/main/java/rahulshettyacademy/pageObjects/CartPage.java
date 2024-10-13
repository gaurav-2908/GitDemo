package rahulshettyacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath="//div[@class='cartSection']/h3")
	List<WebElement>cartProducts;
	
	@FindBy (xpath="//li[@class='totalRow']/button")
	WebElement checkOutBtn;
	
	public boolean checkProductonCartPage(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goTOCheckout() {
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}
	
	
	
}
