package pages.cyclos;

import org.openqa.selenium.By;

import testrunner.Runner;
import utilities.AtomException;

public class PgeCyclosLogin extends Runner{

	By username = By.xpath("//android.widget.EditText[@content-desc='Login name']");
    By pass = By.xpath("//android.view.View[@index='9']");
    By login = By.xpath("//android.widget.Button[@content-desc='LOGIN']");
    
    public void login(String name,String password) throws AtomException{
    	atom.mobile().keywords().enterText(username, name);
    	printTestInfo("Entered "+name+" into the Cyclos username textbox");
    	
    	atom.mobile().keywords().clickAndEnterText(pass, password);
    	printTestInfo("Entered "+password+" into the Cyclos password textbox");
    	atom.mobile().extent().attachScreenshot("Cyclos login");
    	
    	atom.mobile().keywords().click(login);
    	printTestInfo("Clicked LOGIN button");
    }
    
    public void verifyIfLoginScreenPresent() throws AtomException {
    	atom.mobile().keywords().elementExists(username);
    }
}
