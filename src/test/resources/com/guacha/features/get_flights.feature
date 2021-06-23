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

  Scenario Outline: Search cheap flights
    Given I want to search cheap flights in the main page
    When I input my departure and destination <city_info>
    And I input my departure and return dates <dates_info>
    And I select <adult_num> adults and <children_num> children with ages <children_ages>
    Then I should see all available flights according to parameters

    Examples: 
      | city_info            | dates_info              | adult_num | children_num | children_ages |
      | "cartagena-bogota"   | "14/09/2021-14/11/2021" |         3 |            3 | "8,12,16"     |
      | "cartagena-medellin" | "11/09/2021-15/09/2021" |         2 |            4 | "4,4,4,4"     |
      | "medellin-bogota"    | "11/09/2021-21/10/2021" |         1 |            1 | "6"           |
