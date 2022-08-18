package Home.learning.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {

	private WebDriver driver;
	
	private By amazonChoiceItem = By.xpath("//span[@aria-label=\"Amazon\'s Choice\"]");
	private By firstItem = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[@data-index='2']");
	
	private By firstItemImg = By.xpath("//a[@class='a-link-normal s-no-outline']");
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public int clickAmazonChoiceItem() {
		int i = 0;
		try {
		if(driver.findElement(amazonChoiceItem) != null) {
			driver.findElement(amazonChoiceItem).click();
			i = 1;
		}}
		catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Amazon's Choice item is not found");
			i = 0;
		}
		return i;
	}
	
	public void clickFirstItem() {
		WebElement firstItemScope = driver.findElement(firstItem);
		
		firstItemScope.findElement(firstItemImg).click();
		
	}
	
}
