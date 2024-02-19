package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtraPage {
	
	private WebElement wait;
	private final WebDriver driver;
	private final By element_button_add_insurance = By.xpath("//button[@class='ry-button--gradient-yellow ry-button--full']");
	private final By element_button_continue = By.xpath("//button[normalize-space()='Continue']");

	
	public ExtraPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void addInsurance() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_button_add_insurance));
		wait.click();
	}
	
	public void clickContinue() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_button_continue));
		wait.click();
	}

}
