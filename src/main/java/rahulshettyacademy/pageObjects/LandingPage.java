package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstructComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement UserEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userID;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='ng-trigger-flyInOut']")
	WebElement errorMessage;
	
	public void launchURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatelogue loginApplication(String userName, String password) {
		userID.sendKeys(userName);
		passwordEle.sendKeys(password);
		loginBtn.click();
		return new ProductCatelogue(driver);		
	}
	public String getErrorMessage() {
		waitForWebElementToDisplay(errorMessage);
		return errorMessage.getText();	
	}

}
