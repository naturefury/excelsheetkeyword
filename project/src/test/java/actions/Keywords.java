package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.Wait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utilities.Base;

public class Keywords extends Base{
	
	private static AppiumDriver<MobileElement> driver;
	private static WebDriverWait wait;
	private static WebElement iElement;
	
	public Keywords(AppiumDriver<MobileElement> getDriver) {
		
		driver = getDriver;
		wait = new WebDriverWait(driver, Wait.DriverWaitTime);
	}
	
	public void click(By locator) {
		
		iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.click();
	}
	
	public void enterText(By locator,String text) {
		
		iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.sendKeys(text);
		clickBack();
	}
	
	public void clickAndEnterText(By locator,String text) {
		
		iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		iElement.click();
		iElement.sendKeys(text);
		clickBack();
	}
	
	public void verifyElementExists(By locator) {
		
		iElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		System.out.println(iElement.getAttribute("content-disc"));
	}
	
	private void clickBack() {
		
		driver.navigate().back();
	}
}
