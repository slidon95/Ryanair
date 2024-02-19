# Introduction

Welcome to the Ryanair Labs Technical Challenge test automation project! This project use Cucumber, Selenium(Java) , and Allure to perform end-to-end testing of a web application. 

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

```

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

![Captura de ecrã 2024-02-19 191728](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/dcaa77b4-b04e-41f8-9194-5e9d7e666e28)


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

![image](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/ff75ddbe-1d6b-4a0c-8de6-f0d069fb087f)

In the driver folder, you can locate the executable files for Chrome and Edge browsers.

![Captura de ecrã 2024-02-19 191935](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/2a3a61b0-fed1-4601-9eae-ea282f11f805)

On the target directory, you'll find the surefire_report containing the Allure reports generated for the tests.

![Captura de ecrã 2024-02-19 192016](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/94c810eb-fef2-4530-98f0-e9a69564a3fa)

Lastly, there is the pom.xml which serves for dependencies, build configurations, and plugins.

![image](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/f9d2a952-84aa-465d-aaa6-c91902529979)

## Running Tests

Ensure that the project is converted to TestNG, and then go to the project's root directory. Right-click on the project source, select 'Run As' and choose 'Maven Test'.

![image](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/8bfae436-cbb4-4012-a0d9-cc5323fda172)


## Report

To view the results in Allure, open PowerShell and enter the command:

```bash
allure serve .\target\surefire-reports
```
![image](https://github.com/RyanairLabs/qa-web-challenge-slidon95/assets/132678833/1e110ee4-d450-4b99-b97b-59d7b6945b9a)


