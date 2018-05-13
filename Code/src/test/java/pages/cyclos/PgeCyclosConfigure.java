package pages.cyclos;

import org.openqa.selenium.By;

import testrunner.Runner;

public class PgeCyclosConfigure extends Runner{

	By cyclosURL = By.xpath("//android.widget.EditText[@text='Cyclos URL']");
    By submitButton = By.xpath("//android.widget.Button[@text='SUBMIT']");
    
    public void configureCyclosURL(String url){
    	atom.mobile().keywords().enterText(cyclosURL, url);
    	printTestInfo("Entered "+url+" into the Cyclos URL textbox");
    	atom.mobile().keywords().click(submitButton);
    	printTestInfo("Clicked SUBMIT button");
    }
}
