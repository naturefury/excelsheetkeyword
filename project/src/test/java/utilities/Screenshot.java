package utilities;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    private static WebDriver driver;
    private static String ScreenshotFilename;
    private static GeneralProperties GeneralProperties = new GeneralProperties();
    public Screenshot(AppiumDriver<MobileElement> getdriver){

        driver = getdriver;

    }

    public String Capture(String Filename){

        try{

            ScreenshotFilename = GeneralProperties.getScreenshotPath() +Filename+" "+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".png";
            //ScreenshotFilename = "/" +Filename+" "+new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".png";
            File ScreenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(ScreenshotFile, new File(ScreenshotFilename));

        }catch(Exception e){

            System.out.println("Error while taking the screenshot "+e.getMessage());
        }
        return ScreenshotFilename;
    }
}
