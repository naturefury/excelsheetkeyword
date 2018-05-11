package pages.cyclos;

import org.openqa.selenium.By;

import actions.Keywords;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PgeHome {

    private static Keywords keywords;
    
    public PgeHome(AppiumDriver<MobileElement> getDriver){
    	
        keywords = new Keywords(getDriver);
    }

    By WelcomeMessage = By.xpath("//android.view.View[@index='9']");
    
    public void verifyIfHomePageIsDisplayed() {
    	
    	keywords.verifyElementExists(WelcomeMessage);
    }
}
