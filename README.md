
# AddTeq Interview Framework - Dhruv Khandelwal

## Setup
- Install Java 11 or above 
- Install Eclipse IDE 
- Install Git 
- Clone the project from repo : https://github.com/dkdhruvkhandelwal67/AddTeqInterview_Dhruv.git
- Once project if cloned , open the framework and right click on the project >> Maven >> Update Project 
- Setup is Done 

## Execution 
- We  can execute test in two ways, either using testng.xml or by command mvn clean test 
- We have configured two testng xml files, one for single threaded run and other for parallel test with two threads.
- Once execution is done we can see the reports in test-output folder if its a pure testng run. Else we can see target folder if its a maven run.
- Once we are done we run maven clean , to cleanup all old reports and junk data .

## Framework Structure 

- SwabLabs_BaseConfig.java: Configures browser details and setup
- SwabLabs_Logger.java : Logging setup
- SwabLabs_SupportingCapabilities.java : Property file extraction or any commen code can we added here
- SwabLabs_PageObjectFirst.java : This is the page object model
- SwabLabs_PageTest.java : Test cases , contains all test cases 
- SwabLabs_PageTestParalleltest : test class configure for parallel testing 
- testconfig.properties : Contains test data properties 
- userDetails.json : Used for configuring data providers test
- chromedriver.exe : Used to open a interact with real browser


### Below Feature have been implemented 

1. Functional Test Cases to Automate:
- Login with valid credentials
Username: standard_user , Password: secret_sauce


2. Verify that the user is redirected to the products page.
- Login with invalid credentials
Username: locked_out_user , Password: secret_sauce

3. Verify that an error message appears.
- Add a product to the cart
After logging in, add any product to the cart.
Validate that the cart shows the correct item count.

4. Checkout flow
Complete the checkout process with mock customer data.
Validate the final order confirmation screen.


### Technical Requirements:
1. Use Selenium WebDriver with Java.
2. Use a test framework like TestNG.
3. Implement the Page Object Model (POM).
4. Include test reporting (e.g., TestNG reports / ExtentReports / Allure (choose one))
5. Implement basic logging (Log4j / SLF4J or standard java.util.logging )


### Provide README with:
1. Setup instructions
2. How to run tests
3. Summary of framework design decisions

### Bonus (Recommended for Senior Candidates):
1. Data-driven testing using TestNG DataProviders or external files (e.g., Excel, CSV, JSON).
2. Parallel test execution using TestNG.
