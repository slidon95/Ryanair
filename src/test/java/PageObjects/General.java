package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class General {
	private WebElement wait;
	private final WebDriver driver;

	private final By element_accept_cookies = By.xpath("//button[normalize-space()='Yes, I agree']");

	public General(WebDriver driver) {
		this.driver = driver;
	}

	public void acceptCookies() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_accept_cookies));
		wait.click();
	}

}
