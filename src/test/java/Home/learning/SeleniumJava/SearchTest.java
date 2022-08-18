package Home.learning.SeleniumJava;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Home.learning.pageObjects.ItemPage;
import Home.learning.pageObjects.MainHomePage;
import Home.learning.pageObjects.SearchPage;
import Home.learning.resources.Base;
import Home.learning.resources.DataDriven;

public class SearchTest extends Base {

	public Properties prop;
	public WebDriver driver;
	private WebDriverWait wdWait;
	
	private int totalCount = 0;

	public void searchNavigation() throws IOException {
		driver = initializeDriver();
		prop = initializePropertyFile();
		driver.get(prop.getProperty("URL"));

		MainHomePage mainHomePage = new MainHomePage(driver);

		// clear cart items
		// mainHomePage.clickCartButton();
		// mainHomePage.deleteItemInCart();

		// get item list from Excel
		DataDriven dataPage = new DataDriven();
		HashMap<String, Integer> itemList = dataPage.getItemList();

		for (Map.Entry<String, Integer> item : itemList.entrySet()) {
			// Thread.sleep(2000);
			// enter search items and click search
			mainHomePage.setSearchText(item.getKey());
			mainHomePage.clickSearchButton();

			// select Amazon's choice item
			SearchPage searchPage = new SearchPage(driver);
			int i = searchPage.clickAmazonChoiceItem();

			// get window handles

			// add to cart if amazon's choice item is present
			if (i == 1) {
				ItemPage itemPage = new ItemPage(driver);
				String count = item.getValue().toString();
				System.out.println("count : " + count);
				itemPage.selectQuantity(count);
				itemPage.clickAddToCart();
			}
		}

	}

	@Test
	public void selectFirstItem() throws IOException, InterruptedException {
		driver = initializeDriver();
		prop = initializePropertyFile();
		driver.get(prop.getProperty("URL"));
		MainHomePage mainHomePage = new MainHomePage(driver);

		// get item list from Excel
		DataDriven dataPage = new DataDriven();
		HashMap<String, Integer> itemList = dataPage.getItemList();

		for (Map.Entry<String, Integer> item : itemList.entrySet()) {
			
			// enter search items and click search
			mainHomePage.setSearchText(item.getKey());
			mainHomePage.clickSearchButton();
			SearchPage searchPage = new SearchPage(driver);
			
			//click on 1st item displayed in Results
			searchPage.clickFirstItem();

			Set<String> windowSet = driver.getWindowHandles();

			Iterator<String> iterator = windowSet.iterator();
			String parentId = iterator.next();

			while (iterator.hasNext()) {
				String childId = iterator.next();
				ItemPage itemPage = new ItemPage(driver);
				driver.switchTo().window(childId);
				
				int count = item.getValue();
				totalCount+= count;
				itemPage.selectQuantity(String.valueOf(count));
				itemPage.clickAddToCart();
				driver.close();
				driver.switchTo().window(parentId);
			}
		}
		mainHomePage.clickCartButton();
		wdWait = new WebDriverWait(driver, Duration.ofMillis(3000));
		wdWait.until(ExpectedConditions.visibilityOf(mainHomePage.getCartElement()));
		assertEquals(mainHomePage.returnCartCount(), String.valueOf(totalCount));
	}
}
