
Visual Studio/ VSCode require extension to read Cucumber/Gherkin

Tests can run without Maven via righ-click on the RunCucumberIT class

With Maven - 
Maven Test execution command:
//Cleans the "logs" and "allure-results" folders. New folders/files generate on every execution with the mvn command
Command:
mvn clean test -Dtest=runner.RunCucumberIT


For allure report generation with screenshot on a failed step:

-Prerequisites:
--allure instalation:

	1. installing Scoop:
		Open a PowerShell terminal (version 5.1 or later) and from the PS C:\> prompt, run:

		Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
		Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression

	2. Install allure via cmd command: scoop install allure
	3. Use command in Terminal - allure serve

For generating allure-report folder with index.html file:
	1. Use command in Terminal - allure generate --clean
-The allure-report folder can be converted into an exportable(not only local) report

Temporary Cucumber report can be found in the Terminal after each test execution in section - "View your Cucumber Report at:  "URL"

Surefire report can be found in - target/surefire-reports/emailable-report.html

Log from the test execution can be found in - logs/application.log



