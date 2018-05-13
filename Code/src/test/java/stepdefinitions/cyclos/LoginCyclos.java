package stepdefinitions.cyclos;

import java.io.IOException;

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
        atom.mobile().setup("OnePlus3");
        atom.mobile().start();
    }

	@Given("^I login into cyclos native mobile app$")
	public void i_login_into_cyclos_native_mobile_app() throws Throwable {
	    PgeCyclosConfigure configure = new PgeCyclosConfigure();
	    configure.configureCyclosURL("demo.cyclos.org");
	}

	@Then("^I must see my account home page$")
	public void i_must_see_my_account_home_page() throws Throwable {
		PgeCyclosLogin login = new PgeCyclosLogin();
		login.login("demo", "1234");
	}
	
	@After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
        	try {
        		String imgName = atom.mobile().takeScreenshot(scenario.getName());
        		String currentDir = System.getProperty("user.dir");
        		System.setProperty("user.dir", atom.environment().paths().runResults());
				Reporter.addScreenCaptureFromPath("images/"+imgName);
				System.setProperty("user.dir", currentDir);
			} catch (IOException e) {
				System.out.println("Atom INFO :: Problem while attaching screenshot to test report");
			}
        }
    }

    @After(order = 0)
    public void afterScenario() {
    	atom.mobile().end();
    }
}
