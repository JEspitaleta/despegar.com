package com.guacha.stepdefinitions;

import java.util.List;

import com.guacha.pages.DespegarMain;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetFlightsStepDefinitions {
	
	DespegarMain main;
	
	@Given("I want to search cheap flights in the main page")
	public void iWantToSearchCheapFlightsInTheMainPage() {
	    main.startBrowser();
	}

	@When("I input my departure and destination {string}")
	public void iInputAsDepartureAndAsDestination(String strCityData) {
		String[] cities = strCityData.split("-");
	    main.inputCityParameters(cities[0], cities[1]);
	}
	
	@When("I input my departure and return dates {string}")
	public void iInputAsDepartureDateAndAsReturnDate(String strDateData) {
		String[] dates = strDateData.split("-");
	    main.inputDateParameters(dates[0], dates[1]);
	}
	
	@When("I select {int} adults and {int} children with ages {string}")
	public void iSelectAdultsAndChildrenWithAges(Integer intAdults, Integer intChildren, String strChildrenAges) {
	    
		main.inputPeopleParameters(intAdults, intChildren, strChildrenAges.split(","));
	    main.submitEverything();
	}

	@Then("I should see all available flights according to parameters")
	public void iShouldSeeAllAvailableFlightsAccordingToParameters() {
	    System.out.println("Ya");
	}
	
}
