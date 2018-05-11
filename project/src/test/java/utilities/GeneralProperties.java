package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import interfaces.Paths;

public class GeneralProperties extends Base{

    private static Properties Prop;
    public GeneralProperties(){

        try{

            File Src = new File("../run-configurations/general.properties");
            FileInputStream Fis = new FileInputStream(Src);
            Prop = new Properties();
            Prop.load(Fis);
        }catch(Exception e){
        	
            System.out.println("Error while opening the Configuration(.properties) file "+e.getMessage());
        }
    }

    public String ChromeDriverPath(){

        String Path = Prop.getProperty("ChromeDriver");
        return Path;

    }

    public String IEDriverPath(){

        String Path = Prop.getProperty("IEDriver");
        return Path;

    }

    public String FirefoxDriverPath(){

        String Path = Prop.getProperty("FirefoxDriver");
        return Path;

    }
    
    public long getTimeout() {

    	long timeout = Integer.parseInt(Prop.getProperty("Locate_Element_Time_Out"));  	
    	return timeout;
    	   	
    }

    public String getHostIP() {
    	
    	String IP = Prop.getProperty("Host_IP");
        return IP;
    }
    
    public String getRunID() throws IOException {
    	
    	File Src = new File("../run-configurations/general.properties");
        FileOutputStream Fis = new FileOutputStream(Src);
    	int lastRunID = Integer.parseInt(Prop.getProperty("Last_RunID"));
    	int currentRunID = lastRunID + 1;
    	Prop.replace("Last_RunID", Integer.toString(currentRunID));
    	Prop.store(Fis, "Updating Run ID");
    	Fis.close();
        return Integer.toString(currentRunID);
    }
    
    public String getScreenshotPath() {
    	String reportPath = Paths.RunResults + "Run " + Prop.getProperty("Last_RunID") + "/images/";
    	return reportPath;
    }
    
    public String getReportPath() throws IOException {
    	
    	String reportPath = Paths.RunResults + "Run " + getRunID() + "/Report " + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".html";
    	return reportPath;
    }
    
    public String getHostPort() {
    	
    	String port = Prop.getProperty("Host_Port");
        return port;
    }
    
    public String getAppActivity() {
    	String Path = Prop.getProperty("Mobile_Application_Activity");
        return Path;
    }
    
    public String getAppPackage() {
    	String Path = Prop.getProperty("Mobile_Application_Package");
        return Path;
    }
    
    public String getAppFileName() {
    	String Path = Prop.getProperty("Mobile_Application_FileName");
        return Path;
    }
}
