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

	@When("I input {string} as departure and {string} as destination")
	public void iInputAsDepartureAndAsDestination(String strDeparture, String strDestination) {
	    main.inputCityParameters(strDeparture, strDestination);
	}
	
	@When("I input {string} as departure date and {string} as return date")
	public void iInputAsDepartureDateAndAsReturnDate(String strDepDate, String strRetDate) {
	    main.inputDateParameters(strDepDate, strRetDate);
	}
	
	@When("I select {int} adults and {int} children with ages")
	public void iSelectAdultsAndChildrenWithAges(Integer intAdults, Integer intChildren, List<Integer> ages) {
	    main.inputPeopleParameters(intAdults, intChildren, ages);
	    main.submitEverything();
	}

	@Then("I should see all available flights according to parameters")
	public void iShouldSeeAllAvailableFlightsAccordingToParameters() {
	    System.out.println("a");
	}
	
}
