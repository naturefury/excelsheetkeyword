package stepdefinitions.cyclos;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import interfaces.AppLaunch;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pages.cyclos.PgeConfiguration;
import pages.cyclos.PgeHome;
import pages.cyclos.PgeLogin;
import utilities.Base;
import utilities.MobileDriver;
import utilities.Screenshot;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;

public class CyclosDemo extends Base{

    private static AppiumDriver<MobileElement> Mobile;
    private static MobileDriver MobileDriver = new MobileDriver();

    @Before
    public void beforeScenario(Scenario scenario) {

        Reporter.assignAuthor("Balabharathi Jayaraman");
        Mobile = MobileDriver.initializeMobileDriver("Samsung Galaxy S5", "Cyclos", AppLaunch.Launch_Installed);    
    }

    @Given("^I login into Cyclos mobile application$")
    public void i_login_into_Cyclos_mobile_application() throws Throwable {

    	PgeConfiguration Configuration = new PgeConfiguration(Mobile);
    	Configuration.configureToURL("demo.cyclos.org");
    	PgeLogin Login = new PgeLogin(Mobile);
    	Login.login("demo", "1234");

    }

    @Then("^I should see the home page$")
    public void i_should_see_the_home_page() throws Throwable {

    	PgeHome Home = new PgeHome(Mobile);
    	Home.verifyIfHomePageIsDisplayed();
    }
    
    @After(order = 1)
    public void afterScenario(Scenario scenario) {
    	
        if (scenario.isFailed()) {

            try{
            	
                Screenshot iScreen = new Screenshot(Mobile);
                Reporter.addScreenCaptureFromPath(iScreen.Capture(scenario.getName()));
            }catch(Exception e){

                System.out.println("Error while attaching the screenshot to report "+e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void afterScenario() {
    	
    	Mobile.quit();
    }
}
