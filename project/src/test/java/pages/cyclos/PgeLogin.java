package pages.cyclos;

import org.openqa.selenium.By;

import actions.Keywords;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PgeLogin {

    private static Keywords keywords;
    
    public PgeLogin(AppiumDriver<MobileElement> getDriver){
    	
        keywords = new Keywords(getDriver);
    }

    By Username = By.xpath("//android.widget.EditText[@content-desc='Login name']");
    By Password = By.xpath("//android.view.View[@index='9']");
    By Login = By.xpath("//android.widget.Button[@content-desc='LOGIN']");
    
    public void login(String username,String password) {
    	
    	keywords.enterText(Username, username);
    	keywords.clickAndEnterText(Password, password);
    	keywords.click(Login);
    }
}
