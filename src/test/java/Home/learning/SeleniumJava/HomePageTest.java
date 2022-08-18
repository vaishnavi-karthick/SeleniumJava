package Home.learning.SeleniumJava;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.checkerframework.common.reflection.qual.NewInstance;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Home.learning.pageObjects.ItemPage;
import Home.learning.pageObjects.LoginPage;
import Home.learning.pageObjects.MainHomePage;
import Home.learning.pageObjects.SearchPage;
import Home.learning.resources.Base;
import Home.learning.resources.DataDriven;

public class HomePageTest extends Base {

	public Properties prop;
	
	private SoftAssert softAssert;
	public WebDriver driver;
	private boolean loginAttempt = false;
	private boolean passwordCheck = false;
	private boolean emailCheck = false;
	@Test
	public void homePageNavigation() throws IOException, InterruptedException
	{
		DataDriven dataPage = new DataDriven();
		HashMap<String, String> loginTests = dataPage.getDataFromExcel();
		System.out.println("no. of testcase : "+loginTests.entrySet());
		for(Map.Entry<String,String> entry: loginTests.entrySet()) {
			
		driver = initializeDriver();
		prop = initializePropertyFile();
		driver.get(prop.getProperty("URL"));
		
		MainHomePage mainHomePage = new MainHomePage(driver);
		
			//clicking on sing-in on Homepage
			mainHomePage.clickLogin();
			
			//login test - enter email or phone number and click continue
			LoginPage loginPage = new LoginPage(driver);
			//String email = (String) prop.get("phoneNum");
			String email = entry.getKey();
			loginPage.setEmailElement(email);
			
			loginPage.clickContinueButton();
			
			//check email success
			emailCheck = loginPage.checkEmailSuccess();
			System.out.println("emailCheck : "+emailCheck);
			softAssert = new SoftAssert();
			assertEquals(emailCheck, true);
			
			
			
			//enter password and click signin
			
			//String password = entry.getValue();
			//emailCheck = loginPage.setPassword(password);
			//if(emailCheck) {
			//passwordCheck = loginPage.clickSignIn();
			//}
			//if (passwordCheck) {
			//	loginAttempt = true;
			//}
			
		//}
	}
	}
/*	@Test (dataProvider = "getSearchItems")
	public void searchAndAddItem(String item) throws IOException 
	{
		
		MainHomePage mainHomePage = new MainHomePage(driver);
		//enter search items and click search
		mainHomePage.setSearchText(item);
		mainHomePage.clickSearchButton();
		
		//select Amazon's choice item
		SearchPage searchPage = new SearchPage(driver);
		int i = searchPage.clickAmazonChoiceItem();
		
		//get window handles
		
		//add to cart if amazon's choice item is present
		if(i == 1) {
		ItemPage itemPage = new ItemPage(driver);
		itemPage.clickAddToCart();
		}
	}
	
	@DataProvider
	public Object getSearchItems() {
		Object[][] items = new Object[3][1];
		
		//item list to search
		items[0][0] = "Bag";
		items[1][0] = "pencil box";
		items[2][0] = "water bottle";
		
		return items;
		
	}
*/
}
