# Introduction

Welcome to the Ryanair Labs Technical Challenge test automation project. This project use Cucumber, Selenium(Java) and Allure to perform end-to-end testing of a web application. 

The primary objective of this project is to develop an automated test script for the Ryanair website (https://www.ryanair.com/ie/en/). The test script will cover the flight booking process, including initiating a search for flights, selecting the VALUE/BASIC fare option, bypassing the login process after fare selection, navigating through extras pages, selecting seats, adding 20kg bags to the booking, and ensuring the presence of a login popup before progressing to the payment stage. 

```python
Feature: Flight Search and Booking

  Scenario Outline: Valid flight search and booking

    Given a user searches for a flight from "<departure>" to "<destination>" on <date> for <adults> adults and <children> children using "<browser>"
    When a user choose basic fare and skip login after fare select
    And Navigate through extras pages selecting seats and 20kg bags
    Then a login should appear

	    	Examples: 
	      | browser  | departure | destination | date       | adults | children |
	      | chrome   | DUB       | STN         | 20/3/2024  |  2     |  1       |
	      | edge     | DUB       | STN         | 18/3/2025  |  3      | 2       |

```
## Requirements

- Java JDK 8 or higher
- Maven
- WebDriver for your preferred browser (Chrome and Edge)

## Installation

 - Allure Reporting

1. Install Scoop (if not installed) - Open PowerShell with administrator privileges and run the following commands to install Scoop:

```bash
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

   Typical Installation - Run this command from a non-admin PowerShell to install scoop with default configuration,scoop will be install to C:\Users\<YOUR USERNAME>\scoop.

```bash
irm get.scoop.sh | iex 
```
2.Install Allure using Scoop - Once Scoop is installed, you can install Allure using the following command:

```bash
scoop install allure
```
This command will download and install the Allure package.

3.Verify the installation - To verify that Allure is installed successfully, you can run:
```bash
allure –version
```

## Project organization

In this project, there are 4 packages located in the src/test/java directory. 

![image](https://github.com/slidon95/Ryanair/assets/132678833/7ec735c7-eeb5-4960-be0a-c20a9768bd03)


- The 'Config' package is dedicated to browser configuration. 

- The 'PageObjects' package, we have the following classes: 

     - BagsPage - methods related to the bags page; 
     - ChooseFlight - methods related to flight selection and passenger information;
     -  ExtraPage -methods related to the extras page;
     -  General - methods with general utility that can be applicable to all web pages;
     - Helpers - methods related to randomly generated names and methods related to the dates;
     -  MainPage - methods associated with the main page where the origin and destination of the flight, the number of passengers, and the flight date are selected;
     - ReviewAndPayPage - containing only one method that checks if the login button is displayed;
     - SeatPage- methods related to the seat selection page. 

- The 'ryanairBDD' package, there is the 'TestRunner' used to execute tests and display results.

- In the 'RyanairBDD.steps' package, you will find the 'RyanairTest' class utilizing methods from the classes created in the 'PageObjects' package. While it is possible to consolidate all the code directly into this class, I have opted for separation to facilitate potential reuse in alternative test scenarios and to improve overall readability.

In the src/test/java directory, you will find the searchAndBooking.feature,a scenario written in Gherkin, that provide a clear and understandable representation of test cases.

![image](https://github.com/slidon95/Ryanair/assets/132678833/db108b37-1dd3-4ba7-955c-8de2b6bcd261)

In the driver folder, you can locate the executable files for Chrome and Edge browsers.

![image](https://github.com/slidon95/Ryanair/assets/132678833/4decb5d9-9b9e-465c-a006-0c637efc0841)

On the target directory, you'll find the surefire_report containing the Allure reports generated for the tests.

![image](https://github.com/slidon95/Ryanair/assets/132678833/f6c92c73-d129-435d-93f9-0958a8bd66aa)

Lastly, there is the pom.xml which serves for dependencies, build configurations, and plugins.

![image](https://github.com/slidon95/Ryanair/assets/132678833/0a718bd9-9bfd-4f3d-ad8f-b8d776d92c22)

## Running Tests

Ensure that the project is converted to TestNG, and then go to the project's root directory. Right-click on the project source, select 'Run As' and choose 'Maven Test'.

![image](https://github.com/slidon95/Ryanair/assets/132678833/dab08d3b-7e52-472e-a383-777791f54328)


## Report

To view the results in Allure, open PowerShell and enter the command:

```bash
allure serve .\target\surefire-reports
```
![image](https://github.com/slidon95/Ryanair/assets/132678833/6e7e97f6-60e6-420c-a5e9-d95d4e892aab)


## DEMO

The dynamic automated test is designed for the DUB to STN flight. This allows you to include any desired number of children and adults, as well as choose a date within a range of one year and one month. This flexibility is based on the observation that purchasing a ticket is only feasible within this specific timeframe.

https://github.com/slidon95/Ryanair/assets/132678833/24e3ee82-2774-473b-8cb3-797fa3bb0e7d


In the automated test, I have integrated error exceptions for both past dates and dates exceeding one year and one month into the future.

https://github.com/slidon95/Ryanair/assets/132678833/bec067bc-f565-4869-b196-2e2d6df3e012

