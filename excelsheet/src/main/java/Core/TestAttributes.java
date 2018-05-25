package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import com.experitest.selenium.MobileWebDriver;

import com.google.common.io.Files;



/**
 * @author BNP Training
 *
 */
public class TestAttributes extends WebKeywordLibrary{

	public static WebDriver driver;
	public static Properties prop=new Properties();
	public static String ProjectLocation="";
	public static String i;
	static FileOutputStream out;
	static Logger logger;  
	public static String userpath=System.getProperty("user.dir");
	
	protected static String a= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
	static int number=1;
	static String methoddesc;
	
	/**
	 * runId() -->>> method is for generating RunID for the test and writing the Run log in a separate property file
	 *
	 */

	public static void runId() throws IOException
	{	
		int irunId;	
		Properties obj = new Properties();
		FileInputStream runfile=new FileInputStream(new java.io.File(userpath+"\\runprpconfig.properties"));
		obj.load(runfile);
		i=obj.getProperty("runid");
		irunId=Integer.parseInt(i)+1;
		i=Integer.toString(irunId);
		java.io.File oFile=new java.io.File(userpath+"\\runprpconfig.properties");
		out = new FileOutputStream(oFile,true);

		obj.replace("runid", Integer.toString(irunId));
		//obj.setProperty("runid", Integer.toString(irunId));
		obj.store(out,">>>>>>>>>runid updation log >>>>  RunID : "+Integer.toString(irunId));
		out.close();	

		//java.io.File file = new java.io.File("E:\\Gopi\\CorrectWS\\Gerty\\Artemis\\cyclossampledemo\\locatorobjects.properties");
		//PropertiesConfiguration config = new PropertiesConfiguration();
		//PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
		//layout.load(new InputStreamReader(new FileInputStream(file)));
		//FileWriter fw = new FileWriter("E:\\Gopi\\CorrectWS\\Gerty\\Artemis\\cyclossampledemo\\locatorobjects.properties",false);
		//config.setProperty("runid", Integer.toString(irunId));
		//layout.save(fw);
	}

	/**
	 * loadobjectsprop() -> method is used to load the locator objects pasted in the below property file
	 *
	 */
	public static void loadobjectsprop() throws IOException 
	{

		try {
			FileInputStream file=new FileInputStream(new java.io.File(userpath+"\\locatorobjects.properties"));
			prop.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("Error in rectreving the excel sheet "+e.printStackTrace());
		}

	}


	public void getProjectLocation() {
		try {			
			String PackageLocation = TestAttributes.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

			String[] PackageLocationSplit = PackageLocation.split("/");


			if (!PackageLocation.toLowerCase().contains(".jar"))
			{
				for (int i = 1;i<PackageLocationSplit.length-3;i++)
					ProjectLocation = ProjectLocation + PackageLocationSplit[i] + "/";				
			}

			else
			{
				for (int i = 1;i<PackageLocationSplit.length-1;i++)
					ProjectLocation = ProjectLocation + PackageLocationSplit[i] + "/";			
			}

		} catch (Exception e) {
			System.out.println("Error while retrieving the project location. "+e.getMessage());

		}
	}

	public static void logsetup() throws IOException
	{
		logger = Logger.getLogger(TestAttributes.class.getName());
		FileHandler fh;  

		try { 
			// This block configure the logger with handler and formatter  

			//new FileOutputStream("E:\\Gopi\\CorrectWS\\Gerty\\Artemis\\cyclossampledemo\\reports\\Run "+i+"_LogFile.log");
			/*new java.io.File(
					"E:\\Gopi\\CorrectWS\\Gerty\\Artemis\\cyclossampledemo\\reports\\Run "+i+"_LogFile.log"
					).createNewFile();
			 */

			fh = new FileHandler(userpath+"/reports/"+a+"_Run_"+i+"\\Run "+i+"_LogFile.log");  
			//fh = new FileHandler("E:\\Gopi\\CorrectWS\\Gerty\\Artemis\\cyclossampledemo\\reports\\Run_"+i+"\\Run_"+i+"_LogFile.log");  
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  

		} catch (SecurityException e) {  
			e.printStackTrace(); 
		}

	}
	public static void createrunfolder()
	{
		java.io.File runIdFolder= new java.io.File(userpath+"\\reports\\"+a+"_Run_"+i);
		runIdFolder.mkdir();
	}
	
	public static  void getmethoddesc(String imethoddesc)
	{
		methoddesc=imethoddesc;
	}

	public static String capturescreenshot() throws IOException
	{
		//long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		//String methoddesc="test";

		try {
			String path=userpath+"/reports/"+a+"_Run_"+i+"/images/step "+number+"_"+methoddesc+".jpg";
			java.io.File filess=new java.io.File(userpath+"/reports/"+a+"_Run_"+i+"/images/step "+number+"_"+methoddesc+".jpg");
			java.io.File sefiles=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Files.copy(sefiles , filess);
			number++;
			return path;
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return null;

		// Write if it is successful or failure or information
	
	

	}
}


