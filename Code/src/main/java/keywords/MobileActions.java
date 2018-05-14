package keywords;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utilities.AtomException;

public class MobileActions{
	
	private AppiumDriver<MobileElement> driver;
	private WebDriverWait wait;
	private int locaterTimeout;
	private String PresenceofElementLocatedFail = "[FAIL] Driver cannot find the specified element ";
	
	public MobileActions(AppiumDriver<MobileElement> driver,int locaterTimeout) {
		this.driver = driver;
		this.locaterTimeout = locaterTimeout;
		wait = new WebDriverWait(driver,locaterTimeout);
	}
	
	public void enterText(By locator,String text) throws AtomException {
		try {
			WebElement iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			iElement.sendKeys(text);
			clickBack();
			printDriverInfo("[PASS] SentKeys");
		}catch(Exception e) {
			throw new AtomException(PresenceofElementLocatedFail + locator.toString());
		}
	}
	
	public void elementExists(By locator) throws AtomException {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}catch(Exception e) {
			throw new AtomException("Element doesn't exists, driver waited for "+locaterTimeout+" seconds to find the element" + locator.toString());
		}
	}
	
	public void clickAndEnterText(By locator,String text) throws AtomException {
		try {
		WebElement iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.click();
		iElement.sendKeys(text);
		clickBack();
		printDriverInfo("Clicked and SentKeys");
		}catch(Exception e) {
			throw new AtomException(PresenceofElementLocatedFail + locator.toString());
		}
	}
	
	public void click(By locator) throws AtomException {
		try {
		WebElement iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.click();
		printDriverInfo("Clicked");
		}catch(Exception e) {
			throw new AtomException(PresenceofElementLocatedFail + locator.toString());
		}
	}
	
	private void clickBack() {	
		driver.navigate().back();
	}
	
	private void printDriverInfo(String text) {
		System.out.println("Atom INFO "+LocalDateTime.now()+" :: [Driver] "+text);
	}
}
