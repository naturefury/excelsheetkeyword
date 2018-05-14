package pages.cyclos;

import org.openqa.selenium.By;
import testrunner.Runner;
import utilities.AtomException;

public class PgeCyclosConfigure extends Runner{

	By cyclosURL = By.xpath("//android.widget.EditText[@content-desc='Cyclos URL']");
    By submitButton = By.xpath("//android.widget.Button[@content-desc='SUBMIT']");
    
    public void configureCyclosURL(String url) throws AtomException{
    	atom.mobile().keywords().enterText(cyclosURL, url);
    	printTestInfo("Entered "+url+" into the Cyclos URL textbox");
    	atom.mobile().extent().attachScreenshot("Cyclos configure");
    	
    	atom.mobile().keywords().click(submitButton);
    	printTestInfo("Clicked SUBMIT button");
    }
}