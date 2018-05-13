package pages.cyclos;

import org.openqa.selenium.By;

import testrunner.Runner;

public class PgeCyclosLogin extends Runner{

	By username = By.xpath("//android.widget.EditText[@text='Login name']");
    By pass = By.xpath("//android.widget.EditText[@text='Password']");
    By login = By.xpath("//android.widget.Button[@text='LOGIN']");
    
    public void login(String name,String password){
    	atom.mobile().keywords().enterText(username, name);
    	printTestInfo("Entered "+name+" into the Cyclos username textbox");
    	atom.mobile().keywords().enterText(pass, password);
    	printTestInfo("Entered "+password+" into the Cyclos password textbox");
    	atom.mobile().keywords().click(login);
    	printTestInfo("Clicked LOGIN button");
    }
}
