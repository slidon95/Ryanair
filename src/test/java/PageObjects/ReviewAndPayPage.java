package PageObjects;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewAndPayPage {
	
	private WebElement wait;
	private final WebDriver driver;
	
	private final By element_login_button = By.xpath("(//button[@type='submit'])[1]");

	
	public ReviewAndPayPage(WebDriver driver) {
		this.driver = driver;
		
	}

	public void verifyLoginButton() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_login_button));
		// Assert that the button is visible
        Assert.assertTrue("The button is not visible.", wait.isDisplayed());

	}

}
