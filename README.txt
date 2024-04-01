
Maven Test execution command:
mvn clean test -Dtest=runner.RunCucumberIT


For allure report generation with screenshot on a failed step:

allure instalation

-prerequisites:
	1. installing Scoop:
Open a PowerShell terminal (version 5.1 or later) and from the PS C:\> prompt, run:

Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression

	2. Install allure via cmd command: scoop install allure
	3. Use command in VSCode - allure serve
For generating allure-report folder with index.html file:
	1. Use command in VSCode - allure generate --clean
-the allure-report folder can be converted into an exportable(not only local) report



