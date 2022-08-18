package Home.learning.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainHomePage {
	private WebDriver driver;
	private By signin = By.xpath("//a[@id='nav-link-accountList']");
	
	private By cartContainer = By.id("nav-cart");
	private By cartCount = By.xpath("//a[@id='nav-cart']/div/span[@id='nav-cart-count']");
	private By activeCartView = By.xpath("activeCartViewForm");
	
	private By deleteCartItem = By.xpath("//input[@data-action='delete']");
	private By saveForLater = By.xpath("//input[@data-action='save-for-later']");
	private By searchText = By.id("twotabsearchtextbox");
	
	private By searchButton = By.id("nav-search-submit-button");
	
	public MainHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickLogin() {
		driver.findElement(signin).click();
	}
	
	public void setSearchText(String item) {
		driver.findElement(searchText).clear();
		driver.findElement(searchText).sendKeys(item);
	}
	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}
	
	public void clickCartButton() {
		System.out.println("cart clicked");
		driver.findElement(cartContainer).click();
	}
	
	public void saveForLaterItems() {
		List<WebElement> itemsInCart = driver.findElements(saveForLater);
		for(WebElement item: itemsInCart) {
			item.click();
		}
	}
	
	public void deleteItemInCart() {
		WebElement cartDriver = driver.findElement(activeCartView);
		List<WebElement> itemsInCart = cartDriver.findElements(deleteCartItem);
		for(WebElement item: itemsInCart) {
			item.click();
		}
	}
	
	public String returnCartCount() {
		return driver.findElement(cartCount).getText();
	}
	
	public WebElement getCartElement() {
		return driver.findElement(cartCount);
	}
}
