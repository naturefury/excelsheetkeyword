package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileActions {
	
	private AppiumDriver<MobileElement> driver;
	private WebDriverWait wait;
	
	public MobileActions(AppiumDriver<MobileElement> driver,int locaterTimeout) {
		this.driver = driver;
		wait = new WebDriverWait(driver,locaterTimeout);
	}
	
	public void enterText(By locator,String text) {
		printDriverInfo("SentKeys");
		WebElement iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.sendKeys(text);
		clickBack();
	}
	
	public void clickAndEnterText(By locator,String text) {
		printDriverInfo("Clicked and SentKeys");
		WebElement iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.click();
		iElement.sendKeys(text);
		clickBack();
	}
	
	public void click(By locator) {
		printDriverInfo("Clicked");
		WebElement iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.click();
	}
	
	private void clickBack() {	
		driver.navigate().back();
	}
	
	private void printDriverInfo(String text) {
		System.out.println("Atom INFO :: "+text+" [Driver]");
	}
}
