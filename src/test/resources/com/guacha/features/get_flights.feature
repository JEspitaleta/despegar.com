#Author: julian.espitaleta@sophossolutions.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Find cheapest flights
  I want to automate the search of cheap flights

  Scenario: Search cheap flights
    Given I want to search cheap flights in the main page
    When I input "cartagena" as departure and "bogota" as destination
    And I input "30/08/2021" as departure date and "30/09/2021" as return date
    And I select 2 adults and 2 children with ages
      |    5 |
      |   10 |
    Then I should see all available flights according to parameters
