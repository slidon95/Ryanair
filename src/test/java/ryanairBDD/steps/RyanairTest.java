package ryanairBDD.steps;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;

import Config.WebDriverConfig;
import PageObjects.BagsPage;
import PageObjects.ChooseFlightPage;
import PageObjects.ExtraPage;
import PageObjects.General;
import PageObjects.Helpers;
import PageObjects.Helpers.PastDateException;
import PageObjects.Helpers.YearOutOfRangeException;
import PageObjects.MainPage;
import PageObjects.ReviewAndPayPage;
import PageObjects.SeatPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RyanairTest {

	WebDriver driver;
	General generalPage;
	MainPage main;
	ChooseFlightPage choose_flight;
	SeatPage seat;
	BagsPage bag;
	ExtraPage extra;
	ReviewAndPayPage pay;
	
	int numberAdults = 0;
	int numberChilds = 0;

	@Given("a user searches for a flight from {string} to {string} on {int}\\/{int}\\/{int} for {int} adults and {int} children using {string}")
	public void a_user_searches_for_a_flight_from_to_on_for_adults_and_children_using(String departure,
			String destination, Integer day, Integer month, Integer year, Integer number_adults, Integer number_childs,
			String browser) throws ParseException, PastDateException, YearOutOfRangeException{
		
		numberAdults = number_adults;
		numberChilds=number_childs;
		driver = WebDriverConfig.startBrowser(browser);
		generalPage = new General(driver);
		seat= new SeatPage(driver);
		pay= new ReviewAndPayPage(driver);

		bag = new BagsPage(driver);
		main = new MainPage(driver);
		extra = new ExtraPage(driver);
		choose_flight = new ChooseFlightPage(driver);
		main.navidatetoMainPage();
		generalPage.acceptCookies();
		main.clickSubscribetoClose();
		main.selectOneWay();
		main.clickDeparture();
		main.clickClearSelection();
		main.fillDeparture(departure);
		main.selectDublin();
		main.clickDestination();
		main.fillDestination(destination);
		main.selectLondonStansted();
		main.verifyDateInPast(day, month, year);
		main.verifyDateIsInRangeOneYearAndOneMonth(day, month, year);
		main.selectMonth(day,month, year);
		main.selectDay(day, month, year);
		main.clickOnPassenger();
		main.addChildren(number_childs);
		main.addAdults(number_adults);
		main.clickAtDonePassengers();
		main.clicOnSearchButton();
	}
	
	@When("a user choose basic fare and skip login after fare select")
	public void a_user_choose_basic_fare_and_skip_login_after_fare_select() {
		choose_flight.selectflight();
		choose_flight.selectBasicFare();
		choose_flight.clickContinueBasicFare();
		choose_flight.clickLoginLater();
		for( int i = 1; i <= numberAdults; i++ ) {
		choose_flight.click_and_choose_mr_title_passenger_adult(i);
		choose_flight.fillInfoAdult(i, Helpers.generateFirstName(),Helpers.generateLastName());
		}
		for( int i = 1; i <= numberChilds; i++ ) {
		choose_flight.fillInfoChild(i, Helpers.generateFirstName(),Helpers.generateLastName());
		}
		choose_flight.clickContinue();
	}

	@When("Navigate through extras pages selecting seats and 20kg bags")
	public void navigate_through_extras_pages_selecting_seats_and_20kg_bags() {
		seat.acceptFamilySeating();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		seat.clickAddRecommendedSeats();
		seat.clickFastTrack();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bag.selectSmallBagOnly();
		bag.addBags();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bag.clickContinue();
		extra.addInsurance();
		extra.clickContinue();
		extra.clickContinue();

	}

	@Then("a login should appear")
	public void a_login_should_appear() {
		pay.verifyLoginButton();
	}

	@After
	public void closeBrowser() {
		WebDriverConfig.closeBrowser(driver);
	}

}
