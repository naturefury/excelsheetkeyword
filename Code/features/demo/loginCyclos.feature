Feature: demo

	@Sanity
	Scenario: Configure Cyclos
	Given I open cyclos mobile app and enter the cyclos url and click submit button
	Then it must take me to the login screen
	
	@Sanity
	Scenario: Login with valid credentials
	Given I login into cyclos native mobile app
	Then I must see my account home page