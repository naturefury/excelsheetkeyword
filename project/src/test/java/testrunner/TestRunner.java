package testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import interfaces.Paths;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.io.*;
import utilities.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"features"},
        glue = {"stepdefinitions"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"},
        monochrome = true
)

public class TestRunner extends Base{
	
	private static GeneralProperties GeneralProperties = new GeneralProperties();
	
	@BeforeClass
	public static void setup() throws IOException {

	    ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	    extentProperties.setReportPath(GeneralProperties.getReportPath());
	    
	}

    @AfterClass
    public static void CreateExtentReport() {
    	
        Reporter.loadXMLConfig(new File(Paths.ExtentReportConfiguration));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", 	"Windows 10 " + "64 Bit");
        Reporter.setSystemInfo("Selenium", "3.7.0");
        Reporter.setSystemInfo("Maven", "3.5.2");
        Reporter.setSystemInfo("Java Version", "1.8");
    }
    
}
