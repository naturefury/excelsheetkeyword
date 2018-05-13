package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import keywords.MobileActions;

public class Mobile {
	
	private Environment env;
	private AppiumDriver<MobileElement> driver;
	private DesiredCapabilities mobCap = new DesiredCapabilities();
	private MobileActions actions;
	
	public Mobile(Environment env) {
		this.env = env;
	}
	
	public void setup(String deviceName) {
		String i = env.config().propertyValue(deviceName);
		String[] deviceProperties = i.split("##");
		String osType = deviceProperties[0].trim();
		if(osType.equalsIgnoreCase("android")) {
			setAndroidCapabilities(deviceProperties);
		}else if(osType.equalsIgnoreCase("ios")) {
			setIOSCapabilities(deviceProperties);
		}
	}
	
	public void start() {
		try {
			driver = new AppiumDriver<MobileElement>(new URL(env.appiumURL()),mobCap);
			initialiseActions();
			System.out.println("Atom INFO :: Launched the mobile driver with "+mobCap+" on the appium server "+env.appiumURL());
			System.out.println("Atom INFO :: Default timeout to locate each element is set to "+env.locaterTimeout());
		} catch (MalformedURLException e) {
			System.out.println("Atom ERROR :: Problem while launching the mobile driver with "+mobCap+" on the appium server "+env.appiumURL());
		}
	}
	
	public void end() {
		driver.quit();
		System.out.println("Atom INFO :: Closed the mobile driver");
	}
	
	private void initialiseActions() {
		actions = new MobileActions(driver,env.locaterTimeout());
	}
	
	public String takeScreenshot(String fileName) {
		String screenshotName = fileName+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".png";
		try{
            File img = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(img, new File(env.paths().runResults()+"images/"+screenshotName));
        }catch(Exception e){
            System.out.println("Error while taking the screenshot "+e.getMessage());
        }
        return screenshotName;
	}
	
	public MobileActions keywords() {
		return actions;
	}
	
	private void setAndroidCapabilities(String[] dProperties) {
		String platformName = dProperties[0].trim();
		mobCap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		mobCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, dProperties[1].trim());
		mobCap.setCapability(MobileCapabilityType.DEVICE_NAME, dProperties[2].trim());
		if(env.config().propertyValue("installMobileApplicationEverytime").equalsIgnoreCase("yes")) {
			mobCap.setCapability(MobileCapabilityType.APP, env.paths().apps()+env.appName()+".apk");
		}else {
			mobCap.setCapability("appPackage", "org.cyclos.mobile");
			mobCap.setCapability("appActivity", "org.cyclos.mobile.Cyclos4");
		}
	}
	
	private void setIOSCapabilities(String[] dProperties) {
		
	}
}