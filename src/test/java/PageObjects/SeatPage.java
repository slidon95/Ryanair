package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeatPage {
	
	private WebElement wait;
	private final WebDriver driver;
	
	private final By element_button_ok_got_it = By.cssSelector(".seats-modal__cta.ry-button--gradient-blue");
	private final By element_button_recommended_seats = By.xpath("(//button[normalize-space()='Add recommended seats'])[1]");
	private final By element_button_fast_track = By.xpath("//button[normalize-space()='Add Fast Track']");

	
	public SeatPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void acceptFamilySeating() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10000))
				.until(ExpectedConditions.elementToBeClickable(element_button_ok_got_it));
		wait.click();
	}
	
	public void clickAddRecommendedSeats() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10000))
				.until(ExpectedConditions.elementToBeClickable(element_button_recommended_seats));
		wait.click();
	}
	
	public void clickFastTrack() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10000))
				.until(ExpectedConditions.elementToBeClickable(element_button_fast_track));
		wait.click();
	}

}
