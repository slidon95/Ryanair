package PageObjects;

import java.text.ParseException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.Helpers.PastDateException;
import PageObjects.Helpers.YearOutOfRangeException;


public class MainPage {

	private By element_month;
	private By element_day;
	Helpers date = new Helpers();

	private WebElement wait;
	private final WebDriver driver;

	private final By element_subscribeButton = By.xpath("//icon[@class='subscriber-widget__mail-icon']");

	private final By element_clear_selection = By.xpath("//button[normalize-space()='Clear selection']");
	private final By element_departure = By.xpath("//input[@id='input-button__departure']");
	private final By element_destination = By.xpath("//input[@id='input-button__destination']");
	private final By element_DUB = By.xpath("(//span[contains(text(),'Dublin')])[1]");
	private final By element_STN = By.xpath("//span[contains(text(),'London Stansted')]");
	private final By element_one_way_trip = By.xpath("//span[normalize-space()='One way']");
	private final By element_passegers_button = By.xpath("//ry-input-button[@role='button']");
	private final By element_adults = By.xpath("//ry-counter[1]//div[2]//div[3]");
	private final By element_childreen = By.xpath("//ry-counter[3]//div[2]//div[3]");
	private final By element_done_passengers = By.xpath("//button[normalize-space()='Done']");
	private final By element_search_button = By.xpath("//button[@aria-label='Search']");
	private final By element_date_icon = By.xpath("//div[@class='m-toggle__button']//icon");
	private final By element_date_icon_next_month = By.xpath("//div[@data-ref='calendar-btn-next-month']");

	public MainPage(WebDriver driver) {
		this.driver = driver;
	}

	public void navidatetoMainPage() {
		driver.get("https://www.ryanair.com/ie/en");
	}

	public void clickSubscribetoClose() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_subscribeButton));
		wait.click();
	}

	public void selectOneWay() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_one_way_trip));
		wait.click();
	}

	public void clickDeparture() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_departure));
		wait.click();
	}

	public void clickClearSelection() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_clear_selection));
		wait.click();
	}

	public void fillDeparture(String departure) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_departure));
		wait.sendKeys(departure);
	}

	public void selectDublin() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_DUB));
		wait.click();
	}

	public void clickDestination() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_destination));
		wait.click();
	}

	public void fillDestination(String destination) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_destination));
		wait.sendKeys(destination);
	}

	public void selectLondonStansted() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_STN));
		wait.click();
	}

	// Verify if the date is in the pass
	public void verifyDateInPast(int day_feature, int month_feature, int year_feature)
			throws ParseException, PastDateException {
		String date_feature = day_feature + "/" + month_feature + "/" + year_feature;
		date.checkIfFutureDate(date_feature);
	}

	// Verify if the date is in the range of 1 year and 1 month
	public void verifyDateIsInRangeOneYearAndOneMonth(int day_feature, int month_feature, int year_feature)
			throws ParseException, YearOutOfRangeException {
		String date_feature = day_feature + "/" + month_feature + "/" + year_feature;
		date.verifyDateRange(date_feature);
	}

	public void selectMonth(int day_feature, int month_feature, int year_feature) throws ParseException {
		String date_feature = day_feature + "/" + month_feature + "/" + year_feature;
		String month = "";
		// convert the int month to string month
		month = date.convertMonth(month, month_feature);
		// verify if we are in the current year
		int year = date.getCurrentYear();
		if (year_feature == year) {
			// verify if we are on the first month
			if (month.equals(date.getCurrentMonth())) {
				element_month = By.xpath("(//div[@class='m-toggle__month m-toggle__month--selected'])[1]");
				// verify if we are in the second month
			} else if (month.equals(date.getNextMonth())) {
				element_month = By.xpath("//div[@class='m-toggle__month m-toggle__month--after-selected']");
			} else {
				// for the following months
				element_month = By
						.xpath("(//div[@data-ref='m-toggle-months-item'][normalize-space()='" + month + "'])[1]");
				System.out.println(element_month);
			}

			wait = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.elementToBeClickable(element_month));
			wait.click();
		} else {
			// code that click on the icon ">"
			wait = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(element_date_icon));
			wait.click();
			// verify if we are in the penultimate month of the range of one year
			if (date.isPenultimateMonthInOneYearRange(date_feature)) {
				element_month = By.xpath("(//div[@class='m-toggle__month'][normalize-space()='" + month + "'])[1]");
				System.out.println(element_month);
				wait = new WebDriverWait(driver, Duration.ofSeconds(10))
						.until(ExpectedConditions.elementToBeClickable(element_month));
				wait.click();
				// verify if we are in the last month of the range of one year or if we are in
				// the last month of the range of one year and one month
			} else {
				if (date.isLastMonthInRangeOneYearOneMonth(date_feature)
						|| date.isLastMonthInOneYearRange(date_feature)) {
					// convert the integer date into string date minus two prior months
					month = date.convertMonth("", date.getMonthTwoMonthsPrior(date_feature));
					element_month = By.xpath("(//div[@class='m-toggle__month'][normalize-space()='" + month + "'])[1]");
					System.out.println(element_month);
					wait = new WebDriverWait(driver, Duration.ofSeconds(10))
							.until(ExpectedConditions.elementToBeClickable(element_month));
					wait.click();
					// click on the button ">" on next month
					wait = new WebDriverWait(driver, Duration.ofSeconds(10))
							.until(ExpectedConditions.elementToBeClickable(element_date_icon_next_month));
					wait.click();
					if (date.isLastMonthInRangeOneYearOneMonth(date_feature)) {
						// click on the button ">" on next month
						wait = new WebDriverWait(driver, Duration.ofSeconds(10))
								.until(ExpectedConditions.elementToBeClickable(element_date_icon_next_month));
						wait.click();
					}
				}
			}
		}
	}

	public void selectDay(int day_feature, int month_feature, int year_feature) {
		String date_feature = day_feature + "/" + month_feature + "/" + year_feature;
		// Verify if we are on week on weekend and return the specific day of the week
		String day_week = date.getDayOfWeek(date_feature);
		System.out.println(day_week);
		// for the weekend
		if (day_week.equals("Sunday") || day_week.equals("Saturday")) {
			element_day = By
					.xpath("//div[@class='calendar-body__cell calendar-body__cell--weekend'][normalize-space()='"
							+ day_feature + "']");
			// for the week
		} else {

			element_day = By.xpath("//div[@class='calendar-body__cell'][normalize-space()='" + day_feature + "']");

		}
		System.out.println(element_day);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.elementToBeClickable(element_day));
		wait.click();
	}

	public void clickOnPassenger() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(100))
				.until(ExpectedConditions.elementToBeClickable(element_passegers_button));
		wait.click();
	}

	public void addAdults(int adult) {
		// when we are selecting the adult we already have one adult selected on the
		// system
		int aux = 2;
		while (adult >= aux) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(element_adults));
			wait.click();
			aux++;
		}
	}

	public void addChildren(int children) {
		int aux = 1;
		while (children >= aux) {
			wait = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(element_childreen));
			wait.click();
			aux++;
		}
	}

	public void clickAtDonePassengers() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(100))
				.until(ExpectedConditions.elementToBeClickable(element_done_passengers));
		wait.click();
	}

	public void clicOnSearchButton() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(1000))
				.until(ExpectedConditions.elementToBeClickable(element_search_button));
		wait.click();
	}
}
