Feature: Test user login
	
	Scenario Outline: User logins succesfully with credentials
	Given Browser is open
	And user is in login page
	When user enters <username> and <password>
	And user press login button
	Then website shows main page
	
	Examples:
	|username||password|
	|Leonel||12345|
	|Ernesto||12345|