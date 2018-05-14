package stepdefinitions.cyclos;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.cyclos.PgeCyclosConfigure;
import pages.cyclos.PgeCyclosLogin;
import testrunner.Runner;

public class LoginCyclos extends Runner{
	
	@Before
    public void beforeScenario(Scenario scenario) {
        Reporter.assignAuthor("Balabharathi Jayaraman");
        atom.environment().printBeforeScenario(scenario.getName());
    }
	
	@Given("^I open cyclos mobile app and enter the cyclos url and click submit button$")
	public void i_open_cyclos_mobile_app_and_enter_the_cyclos_url_and_click_submit_button() throws Throwable {
		PgeCyclosConfigure configure = new PgeCyclosConfigure();
	    configure.configureCyclosURL("demo.cyclos.org");
	}

	@Then("^it must take me to the login screen$")
	public void it_must_take_me_to_the_login_screen() throws Throwable {
		PgeCyclosLogin login = new PgeCyclosLogin();
		login.verifyIfLoginScreenPresent();
	}

	@Given("^I login into cyclos native mobile app$")
	public void i_login_into_cyclos_native_mobile_app() throws Throwable {
		PgeCyclosLogin login = new PgeCyclosLogin();
		login.login("demo", "1234");
	}

	@Then("^I must see my account home page$")
	public void i_must_see_my_account_home_page() throws Throwable {
		//
	}
	
	@After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
        	atom.mobile().extent().attachScreenshot(scenario.getName());
        }
    }

    @After(order = 0)
    public void afterScenario() {
    	atom.environment().printAfterScenario();
    }
}
