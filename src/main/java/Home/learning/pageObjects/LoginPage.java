package Home.learning.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;
	private By emailBy = By.id("ap_email");
	private By contineButton = By.id("continue");
	private By passwordIn = By.id("ap_password");
	private By signIn = By.id("signInSubmit");
	private By loginError = By.xpath("//div[@class='a-alert-content']/ul/li");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setEmailElement(String email) {
		driver.findElement(emailBy).sendKeys(email);
	}
	
	public void clickContinueButton() {
		driver.findElement(contineButton).click();
	}
	
	public boolean setPassword(String password) {
		boolean emailCheck = false;
		try {
			driver.findElement(passwordIn).sendKeys(password);
			emailCheck = true;
		} catch (Exception e) {
			System.out.println(driver.findElement(loginError).getText());
			emailCheck = false;
		}
		return emailCheck;
	}
	
	public boolean checkEmailSuccess() {
		return driver.findElement(passwordIn).isDisplayed();
	}
	
	public boolean clickSignIn() {
		boolean passwordCheck = false;
		try {
			driver.findElement(signIn).click();
			passwordCheck = true;
		} catch (Exception e) {
			System.out.println(driver.findElement(loginError).getText());
			passwordCheck = false;
		}
		return passwordCheck;
	}

}
