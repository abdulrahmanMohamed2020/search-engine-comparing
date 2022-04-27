# Automation Search Engine Comparing
Some automated test cases for Two Search Engines
## The main Frameworks included in the project:
* Selenium Webdriver
* TestNG
* Allure Report
* Commons IO
## Project Design:
* Page Object Model (POM) design pattern
* Fluent design approach (method chaining)
* Have a supporting Core package in src/main/java file path, named **"core"** that includes Capabilities classes which serve as a core engine for the project
## How to check the execution log and open the latest execution reports from GitHub Actions:
* You need to be logged in to GitHub as a prerequisite
* Open the GitHub Actions tab
* Open the latest workflow run from the list
## Continuous Integration(CI)
* A Corn Job to schedule jobs to run automatically at fixed times, dates, or intervals.
* A web hook has been setup with Github Actions (Java, Maven), and Selenium Tests will execute with docker container during CI.
* After executing, you can easily generate the **Allure Report** by opening a command-line terminal on the project 
root path and type
```
allure serve "allure-result" path
```
(needs to be able to execute allure commands);
## Steps in github actions to setup the container and run the tests
* Setup the job to run on [Ubuntu]
* Checkout Code
* Setup Native Selenium Grid
* Set up JDK 1.8
* Check running containers
* Run all tests
* Generate Allure Report
* Post the report to public URL using Githib Pages
* If you have a workspace on Slack it can easily push the report URL to a specific channel
