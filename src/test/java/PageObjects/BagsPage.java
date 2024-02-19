package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BagsPage {

	private WebElement wait;
	private final WebDriver driver;

	private final By element_button_all_small_bag_only = By.xpath("(//icon[@class='icon ng-star-inserted'])[1]");
	private final By element_button_continue = By.xpath("(//button[normalize-space()='Continue'])[1]");
	private final By element_button_select_all_passanger = By.xpath("(//span[@role='button'][normalize-space()='Select for all passengers'])[2]");
	private final By element_button_select_all_passanger_for_bags = By.xpath("(//span[@role='button'][normalize-space()='Select for all passengers'])[4]");
 


	public BagsPage(WebDriver driver) {
		this.driver = driver;

	}

	public void selectSmallBagOnly() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_button_all_small_bag_only));
		wait.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_button_select_all_passanger));
		wait.click();
	}

	public void addBags() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(100))
				.until(ExpectedConditions.elementToBeClickable(element_button_select_all_passanger_for_bags));
		wait.click();
	}
	public void clickContinue() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10000))
				.until(ExpectedConditions.elementToBeClickable(element_button_continue));
		wait.click();
		
	}
}
