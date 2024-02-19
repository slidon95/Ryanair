package PageObjects;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseFlightPage {
	
	private WebElement wait;
	private final WebDriver driver;
	
	Helpers name = new Helpers();

	
	private By element_title_passenger_button;
	private By element_first_name_passagenger_adult;
	private By element_last_name_passagenger_adult;
	private By element_first_name_passagenger_child;
	private By element_last_name_passagenger_child;

	
	private final By element_select_flight_button = By.xpath("//button[normalize-space()='Select']");
	private final By element_select_basic_fare = By.xpath("//div[@class='fare-table__fare-column-border fare-table__fare-column-border--regular']");
	private final By element_continue_basic_fare = By.xpath("//ry-spinner[normalize-space()='Continue with Basic']");
	private final By element_login_later_button = By.xpath("//span[@class='login-touchpoint__login-later title-m-lg title-m-sm']");
	private final By element_select_mr =  By.xpath("//div[@class='dropdown-item__label body-l-lg body-l-sm'][normalize-space()='Mr']");
	private final By element_continue = By.xpath("//button[normalize-space()='Continue']");

	

	
	public ChooseFlightPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void selectflight() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_select_flight_button));
		wait.click();
	}
	public void selectBasicFare() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_select_basic_fare));
		wait.click();
	}
	public void clickContinueBasicFare() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_continue_basic_fare));
		wait.click();
	}
	
	public void clickLoginLater() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_login_later_button));
		wait.click();
	}
	
	public void click_and_choose_mr_title_passenger_adult(int iteration) {
		element_title_passenger_button = By.xpath("(//button[@type='button'])[" + iteration + "]");
		System.out.println(element_title_passenger_button);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_title_passenger_button));
		wait.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_select_mr));
		wait.click();
	}
	
	public void fillInfoAdult(int iteration, String fisrtName, String Lastname) {
		iteration--;
		element_first_name_passagenger_adult = By.xpath("//input[@id='form.passengers.ADT-"+ iteration + ".name']");
		element_last_name_passagenger_adult = By.xpath("//input[@id='form.passengers.ADT-" + iteration + ".surname']");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.elementToBeClickable(element_first_name_passagenger_adult));
		wait.sendKeys(fisrtName);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.elementToBeClickable(element_last_name_passagenger_adult));
		wait.sendKeys(Lastname);

		}
	
	public void fillInfoChild(int iteration,String fisrtName, String Lastname) {
		iteration--;
		element_first_name_passagenger_child = By.xpath("//input[@id='form.passengers.CHD-"+ iteration + ".name']");
		element_last_name_passagenger_child = By.xpath("//input[@id='form.passengers.CHD-" + iteration + ".surname']");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_first_name_passagenger_child));
		wait.sendKeys(fisrtName);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_last_name_passagenger_child));
		wait.sendKeys(Lastname);

	}
	public void clickContinue() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_continue));
		wait.click();
	}

}
