package testrunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import core.Atom;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features"},
        glue = {"stepdefinitions"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"}
)

public class Runner {
	
	public static Atom atom;
	
	@BeforeClass
    public static void setup() {
		atom = new Atom();
		logSetup();
        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath(atom.environment().paths().runResults()+"Execution Dashboard.html");
        atom.mobile().setup("SamsungGalaxyS5");
        atom.mobile().start();
    }

    @AfterClass
    public static void CreateExtentReport() {
    	atom.mobile().end();
        Reporter.loadXMLConfig(new File(atom.environment().paths().reportConfiguration()));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", "Windows 10 " + "64 Bit");
        Reporter.setSystemInfo("Selenium", "3.12");
        Reporter.setSystemInfo("Maven", "3.5.3");
        Reporter.setSystemInfo("Java Version", "1.8");
    }
    
    public static void logSetup() {	
		try {
			String logFilePath = atom.environment().paths().runResults()+"testlog.txt";
			File logFile = new File(logFilePath);
			logFile.createNewFile();
			System.out.println("Atom INFO :: Test log will be stored in the location "+logFilePath);
			System.setOut(new PrintStream(new FileOutputStream(logFilePath, true)));
			System.setErr(new PrintStream(new FileOutputStream(logFilePath, true)));
		} catch(Exception e) {
			System.out.println("Atom INFO :: Problem while creating test log file "+e.getMessage());
		}
	}
    
    public void printTestInfo(String text) {
    	System.out.println("Atom INFO "+LocalDateTime.now()+" :: [Test Runner] "+text);
    }
}