package Home.learning.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ItemPage {

	private WebDriver driver;
	private By addToCartButton = By.name("submit.add-to-cart");
	private By quantity = By.xpath("//select[@name='quantity']");
	
	public ItemPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAddToCart() {
		driver.findElement(addToCartButton).click();
	}
	
	public void selectQuantity(String count) {
		//driver.findElement(quantity);
		try {
			Select dropDown = new Select(driver.findElement(quantity));
			dropDown.selectByValue(count);
		} catch (Exception e) {
			System.out.println("default one count");
		}
		
	}
}
