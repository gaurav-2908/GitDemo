package rahulshettyacademy.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstructComponents.AbstractComponents;

public class ProductCatelogue extends AbstractComponents {
	
	WebDriver driver;
	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement>products;
	
	By productsBy = By.xpath("//div[contains(@class,'mb-3')]/div");
	By productName1 = By.xpath("//h5/b");
	By addToCart = By.xpath("//button[text()=' Add To Cart']");
	By toast = By.id("toast-container");
	By animation = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProduct(String givenProduct) {
		
		WebElement reqProduct = products.stream().filter(product->product.findElement(productName1).getText().equals(givenProduct)).findFirst().orElse(null);
		return reqProduct;
	}
	
	public void addProduct(String givenProduct) {
		WebElement product = getProduct(givenProduct);
		product.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(animation);
		
	}

}
