package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverConfig {

	public static WebDriver driver;

	public static WebDriver startBrowser(String browser) {
		if (driver == null) {
			{
				switch (browser.toLowerCase()) {
				case "chrome":
					String chromeDriverPath = "drivers/chromedriver.exe";
					System.setProperty("webdriver.chrome.driver", chromeDriverPath);
					driver = new ChromeDriver();
					break;
				case "firefox":
					String geckoDriverPath = "drivers/geckodriver.exe";
					System.setProperty("webdriver.gecko.driver", geckoDriverPath);
					driver = new FirefoxDriver();
					break;
				case "edge":
					String edgeDriverPath = "drivers/msedgedriver.exe";
					System.setProperty("webdriver.edge.driver", edgeDriverPath);
					driver = new EdgeDriver();
					break;
				case "safari":
					driver = new SafariDriver();
					break;
				default:
					throw new IllegalArgumentException("Unsupported browser: " + browser);
				}
			}
		}
		return driver;
	}

	public static void closeBrowser(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}

}
