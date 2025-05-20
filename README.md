
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

