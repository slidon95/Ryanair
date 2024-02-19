Feature: Flight Search and Booking

  Scenario Outline: Valid flight search and booking
    Given a user searches for a flight from "<departure>" to "<destination>" on <date> for <adults> adults and <children> children using "<browser>"
    When a user choose basic fare and skip login after fare select
    And Navigate through extras pages selecting seats and 20kg bags
    Then a login should appear

	    	Examples: 
	      | browser  | departure | destination | date       | adults | children |
	      | chrome   | DUB       | STN         | 20/3/2024  |  2     |  1       |
	      | chrome   | DUB       | STN         | 12/1/2025  | 2      | 3        |
