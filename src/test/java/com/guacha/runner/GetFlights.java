package com.guacha.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
			glue="com.guacha.stepdefinitions",
			features="src/test/resources/com/guacha/features/get_flights.feature",
			snippets=SnippetType.CAMELCASE,
			strict=true
			
		)
public class GetFlights {
	
}
