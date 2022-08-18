package Home.learning.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public class Base{

	public WebDriver driver;
	private Properties properties;
	
	public Properties initializePropertyFile() throws IOException {
		properties = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\admins\\eclipse-workspace\\SeleniumJava\\src\\main\\java\\Home\\learning\\resources\\data.properties");
		
		properties.load(fis);
		return properties;
	}
	
	public WebDriver initializeDriver() throws IOException {
		
		initializePropertyFile();
		
		String browserName = properties.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(properties.getProperty("chrome.webdriver"), properties.getProperty("user.dir")+"\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			
		}else if (browserName.equalsIgnoreCase("edge")) {
			
		}else if (browserName.equalsIgnoreCase("safari")) {
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}	
}
