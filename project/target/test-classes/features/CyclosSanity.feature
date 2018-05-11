Feature: Cyclos Sanity
	
	@Sanity	@All_Cyclos_Scripts @CyclosLogin
	Scenario: Login into Cyclos Online and Mobile banking software
		Given Open the browser and Navigate to "https://demo.cyclos.org/"
		When I click Sign In
		And Enter the username "demo" and password "1234"
		Then I must see the "Welcome to the Cyclos4 Demo" Message
	
	@Sanity	@All_Cyclos_Scripts @Account_Info
	Scenario: Check the Account balance
		Given Open the browser and Navigate to "https://demo.cyclos.org/"
		When I click Sign In
		And Enter the username "demo" and password "1234"
		When I click the Account Info link
		Then I should see the total remaining balance in my bank account