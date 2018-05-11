Feature: Login feature

	@Smoke @Positive
  Scenario: Login into mocktrading webpage with invalid username & valid password

    Given Open the browser and Navigate to "http://www.mocktrading.com/"
    When I enter the username "Invalid Username", password "Test@123" and click login button
    Then Application should throw an error with "Failure" message

	@Smoke @Negative @Outline
  Scenario Outline: Login into mocktrading webpage with valid username & invalid password

    Given Open the browser and Navigate to "http://www.mocktrading.com/"
    When I enter the username "<username>", password "<password>" and click login button
    Then Application should throw an error with "Failure" message
   
  Examples:
  
  |username|password|
  |balajaysavi@gmail|Test@123|
  |balajaysavi@gmail.com|Test@123|