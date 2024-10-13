package rahulshettyacademy.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstructComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="(//button[contains(@class,'list-group-item')])[2]")
	WebElement reqCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	By countryList = By.xpath("//button[contains(@class,'list-group-item')]");
	
	public void selectCountry(String country) {
		Actions action = new Actions(driver);
		action.sendKeys(selectCountry, country).build()
				.perform();
		waitForElementToAppear(countryList);
		reqCountry.click();
	}
	public ConfirmationPage placeOrder() {
		placeOrderBtn.click();
		return new ConfirmationPage(driver);
	}
	
	
}
