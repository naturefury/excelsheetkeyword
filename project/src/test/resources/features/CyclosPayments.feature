Feature: Cyclos Payments

	@Payment @All_Cyclos_Scripts
	Scenario: Make a valid payment in Cyclos to a user
		Given Open the browser and Navigate to "https://demo.cyclos.org/"
		When I click Sign In
		And Enter the username "demo" and password "1234"
		And Click Pay User link
		And Select the Payment as "Contact", Contact name as "brain", Enter payment amount = "12", description as "Just a demo!"
		And Click Pay
		Then I must see a payment successfull message