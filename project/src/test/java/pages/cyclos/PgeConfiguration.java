package pages.cyclos;

import org.openqa.selenium.By;

import actions.Keywords;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PgeConfiguration {

    private static Keywords keywords;
    
    public PgeConfiguration(AppiumDriver<MobileElement> getDriver){
    	
        keywords = new Keywords(getDriver);
    }

    By CyclosURL = By.xpath("//android.widget.EditTex[@content-desc='Cyclos URL']");
    By Submit = By.xpath("//android.widget.Button[@content-desc='SUBMIT']");
    
    public void configureToURL(String URL) throws InterruptedException {
    	
    	keywords.enterText(CyclosURL, URL);
    	keywords.click(Submit);
    }
}
