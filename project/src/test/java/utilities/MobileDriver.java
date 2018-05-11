package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import interfaces.Paths;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileDriver extends Base{

	private static AppiumDriver<MobileElement> driver;
	private static GeneralProperties genProperties = new GeneralProperties();
	
	public AppiumDriver<MobileElement> initializeMobileDriver(String deviceName,String applicationName,String launchType) {
		
		String hostURL = "http://" + genProperties.getHostIP() + ":" + genProperties.getHostPort() + "/wd/hub";
		try {
			driver = new AndroidDriver<MobileElement>(new URL(hostURL), getMobileCapabilities(deviceName,applicationName,launchType));
			return driver;
		}catch(Exception e) {
			System.out.println("Atom::Error while launching the Mobile driver, Reason: "+e.getMessage());
		}
		return null;
	}
	
	private DesiredCapabilities getMobileCapabilities(String deviceName,String applicationName,String launchType) {
		
		Properties Prop = new Properties();
		DesiredCapabilities cap = new DesiredCapabilities();
		try{

            Prop.load(new FileInputStream(new File(Paths.DeviceCapabilities + deviceName + ".properties")));
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, Prop.getProperty("Device_Name"));
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, Prop.getProperty("Platform_Name"));
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, Prop.getProperty("Platform_Version"));
            if(launchType.equalsIgnoreCase("fresh installation")) {
            	
            	cap.setCapability(MobileCapabilityType.APP, genProperties.getAppFileName()+".apk");
            }else {
     
            	cap.setCapability("appPackage", genProperties.getAppPackage());
            	cap.setCapability("appActivity", genProperties.getAppActivity());
            }
            return cap;
        }catch(Exception e) {
        	
            System.out.println("Error while opening the Configuration(.properties) file "+e.getMessage());
        }
		return null;
	}
}
