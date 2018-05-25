package Core;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.w3c.dom.xpath.XPathResult;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Reporter;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;

public class WebKeywordLibrary   {


	public static WebDriverWait wait;
	public static WebElement ele;



	public static void LAUNCHAPP(String browser) throws Throwable {
		//TestAttributes.TakeScreenShotFlag= true;	

		try {
			String osType = System.getProperty("os.name");
			String webDriversLocation = TestAttributes.ProjectLocation  + "Web Drivers/";

			switch(browser.trim().toLowerCase()) {

			case "firefox":		

				if (osType.trim().toLowerCase().contains("windows")) {
					System.setProperty("webdriver.gecko.driver", webDriversLocation  + "geckodriver.exe");
					// Driver initialization code for firefox Windows *************
					TestAttributes.driver = new FirefoxDriver();


				} else { 

					// Driver initialization code for firefox Mac or Some other OS *************
					TestAttributes.driver = new FirefoxDriver();

				}

				break;

			case "chrome":

				if (osType.trim().toLowerCase().contains("windows")) {

					// Driver initialization code for firefox Windows *************
					System.setProperty("webdriver.chrome.driver", webDriversLocation  + "chromedriver.exe");

				} else { 

					// Driver initialization code for firefox Mac or Some other OS *************
					System.setProperty("webdriver.chrome.driver", webDriversLocation  + "chromedriver-mac");
				}

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				TestAttributes.driver = new ChromeDriver(options);

				break;	

			case "ie":
				System.setProperty("webdriver.ie.driver", webDriversLocation + "IEDriverServer.exe");					
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				//webDriver = new InternetExplorerDriver();
				TestAttributes.driver = new InternetExplorerDriver(ieCapabilities);

				break;

			case "safari":
				TestAttributes.driver = new SafariDriver();

				break;
			}

			TestAttributes.driver.get(TestAttributes.prop.getProperty("url"));
			TestAttributes.driver.manage().window().maximize();
			TestAttributes.driver.manage().timeouts().implicitlyWait(Integer.parseInt(TestAttributes.prop.getProperty("timeout")), TimeUnit.SECONDS);

			wait=new WebDriverWait(TestAttributes.driver,60);
			Thread.sleep(2000);
			//return webDriver;

			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TestAttributes.prop.getProperty("cyclos_loginpage.username_txtbox.xpath"))));

			//TestAttributes.getmethoddesc("LAUNCHAPP");

			//Reporter.addStepLog("The url: "+TestAttributes.prop.getProperty("url")+" is launched sucessfully");
			//Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
			Reporter.report("The url: "+TestAttributes.prop.getProperty("url")+" is launched sucessfully", "PASS","LAUNCHAPP");
			TestAttributes.logger.info("The url: "+TestAttributes.prop.getProperty("url")+ " is launched sucessfully"); 
			TestAttributes.logger.info("The url: "+TestAttributes.prop.getProperty("url")+ " is launched sucessfully"); 

			//TestAttributes.driver.get(TestAttributes.Data);	
			//TestAttributes.ActualResult = "Application '" + TestAttributes.Data + "' is launched.";

		} catch (Exception e) {		

			TestAttributes.getmethoddesc("LAUNCHAPP");
			System.out.println("Error while launching the application "+e.getMessage());
			Reporter.report("The url: "+TestAttributes.prop.getProperty("url")+" is not launchec properly..", "FAIL","LAUNCHAPP");
			TestAttributes.logger.warning("The url: "+TestAttributes.prop.getProperty("url")+" is not launched properly..");

			//TestAttributes.Status = "Error";
			//TestAttributes.ActualResult = "Error while launching the application '" + TestAttributes.Data + "'.";
			//TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}

	//========================================================================================================================================'	
	public static void ClickByXpath(String xpath) throws Throwable {

		try {
			ele=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			//TestAttributes.driver.findElement(By.xpath(xpath)).click();
			ele.click();
			Thread.sleep(4000);
			///TestAttributes.getmethoddesc("ClickByXpath");
			//Reporter.addStepLog("The data: "+xpath+" is clicked");
			//Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
			Reporter.report("The data: "+xpath+" is clicked", "PASS","ClickByXpath");	
			//Thread.sleep(6000);
			TestAttributes.logger.info("the element with xpath "+xpath+" is clicked");
		} catch (Exception e) {
			// TODO: handle exception
			//Reporter.report("Error in clicking the element "+xpath+"", "FAIL","ClickByXpath");
			TestAttributes.getmethoddesc("ClickByXpath");
			TestAttributes.logger.warning("the element with xpath -->>> "+xpath+" is not clicked and there is an error.....");
			System.out.println("Error in clicking the element of the method ClickByXpath "+e.getMessage());
		}



	}

	//========================================================================================================================================'	
	public static void EnterByXpath(String xpath,String value) throws Throwable {

		try {
			ele=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			//TestAttributes.driver.findElement(By.xpath(xpath)).clear();
			//TestAttributes.driver.findElement(By.xpath(xpath)).sendKeys(value);
			ele.clear();
			ele.sendKeys(value);
			///TestAttributes.getmethoddesc("EnterByXpath");
			//Reporter.addStepLog("The locator Xpath : "+xpath+" is entered with value "+value);
			//Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
			Reporter.report("The locator Xpath : "+xpath+" is entered with value "+value, "PASS","EnterByXpath");
			TestAttributes.logger.info("the element with xpath "+xpath+" is entered with value "+value);

		} catch (Exception e) {
			// TODO: handle exception
			//Reporter.report("Error in entering the value "+value+"element "+xpath+"", "FAIL","EnterByXpath");
			TestAttributes.getmethoddesc("EnterByXpath");
			System.out.println("Error in entering value the element of the method EnterByXpath "+e.getMessage());
			TestAttributes.logger.warning("the element with xpath "+xpath+" is not entered with value "+value + "and there is a error");

		}

	}

	//========================================================================================================================================'	

	public static boolean VerifyTitle(String title) throws Throwable {

		boolean breturn=false;

		try {
			System.out.println(TestAttributes.driver.getTitle());
			System.out.println(title);

			if(TestAttributes.driver.getTitle().endsWith(title))
			{
				//Reporter.addStepLog("The title: "+title+" matches");
				//Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
				//Reporter.report("The title: "+title+" matches", "PASS","VerifyTitle");
				breturn=true;
			}

			else
			{
				System.out.println("title of the page mismatches");
				//Reporter.addStepLog("The title: "+title+" is not matched and pls check the details.....");
				//Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
				//Reporter.report("The title: "+title+" mismatches", "FAIL","VerifyTitle");
				breturn=false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			//Reporter.addStepLog("The title: "+title+" is not matched and the is an error in fetching the title of the page.....");
			//Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
			//Reporter.report("Error in the provide tile page information "+title+"", "FAIL","VerifyTitle");
			System.out.println("Error in finding the title of the page "+e.getMessage());
		}


		return breturn;

	}

	public static void closebrowser() throws Throwable {

		try {
			TestAttributes.driver.close();
			//Reporter.report("Browser closed sucessfully ", "PASS","closebrowser");
			TestAttributes.logger.info("Browser closed sucessfully");
		}
		catch(Exception e)
		{
			System.out.println("There is some execptions in closing the browser..pls check the closebrowser() method");
			Reporter.report("Browser not closed sucessfully and there is some isssue ", "FAIL","closebrowser");
			TestAttributes.logger.info("Browser not closed properly and There is some execptions in closing the browser..pls check the closebrowser() method");
		}
	}
	/*public static boolean VerifyLabelByXpath(String xpath,String label) throws Throwable {
		boolean breturn=false;
		try {
			ele=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			//String ilabel=TestAttributes.driver.findElement(By.xpath(xpath)).getText();

			String ilabel=ele.getText();
			if(ilabel.contentEquals(label))
			{
				//Reporter.report("The label: "+label+" verfifed sucessfully", "PASS","VerifyLabelByXpath");
				breturn=true;
				Assert.assertTrue(breturn);
				TestAttributes.getmethoddesc("VerifyLabelByXpath");
				Reporter.addStepLog("The label: "+label+" matches with the xpath "+xpath);
				Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
				TestAttributes.logger.info("the element with xpath -->>> "+xpath+" is verfied with value -->>> "+label);
			}
			else
			{
				//Reporter.report("The label: "+label+" mismacthes ", "FAIL","VerifyLabelByXpath");
				breturn=false;
				Assert.assertTrue(breturn);
				TestAttributes.getmethoddesc("VerifyLabelByXpath");
				Reporter.addStepLog("The label: "+label+" is not matched and pls check the details.....");
				Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
				TestAttributes.logger.warning("the element with xpath-->>> "+xpath+" is not verfied with value -->>> "+label);
			}


		} catch (Exception e) {
			// TODO: handle exception
			//Reporter.report("Error in the verifying the labels "+label+"", "FAIL","VerifyLabelByXpath");
			TestAttributes.getmethoddesc("VerifyLabelByXpath");
			Reporter.addStepLog("The label: "+label+" is not matched and the is an error in fetching the field label of the page.....");
			Reporter.addScreenCaptureFromPath(TestAttributes.capturescreenshot());
			System.out.println("Error in verifying the labels"+e.getMessage());
			TestAttributes.logger.warning("there is a excption in verfying the element with xpath-->>> "+xpath+" and value --->> "+label);
		}
		return breturn;


	}*/


}