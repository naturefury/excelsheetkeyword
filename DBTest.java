package Trash;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.openqa.selenium.WebDriverException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class DBTest {

	static String ProjectLocation="C:\\Report\\";

	static Connection conn = null;
	static Statement stmtTests,smtbatch,instanceruns = null;	
	static ResultSet TestsResults,batch,instruns;
	static String[] testlog = new String[1000];
	static String[] status = new String[1000];
	static String[] screenshot = new String[1000];
	static int[] instancesss = new int[1000];
	static String[] testname = new String[1000];
	static int[] rid = new int[1000];



	

	private static ExtentTest test;
	private static ExtentReports extent;
	//static String a= new SimpleDateFormat("YYYY-MM-DD_HH:MM:SS").format(new Date());
	static String a= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
	static int number=1;


	static String batchname="Sprint_Run";

	static String userpath=ProjectLocation;


	public static void main(String[] args) throws Throwable {

		startResult(batchname);

		int i=1;
		int g=1;
		int h=1;

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		String ConnectionString = "jdbc:ucanaccess://"  +ProjectLocation + "BackEnd.accdb";
		conn = DriverManager.getConnection(ConnectionString);

		smtbatch = conn.createStatement();

		String sql1="SELECT * FROM SQS_TEST_BATCH where SQS_TB_Test_Batch_Name = '" + batchname+"'";
		batch =smtbatch.executeQuery(sql1);

		while(batch.next())
		{

			instancesss[g]=batch.getInt("SQS_TB_Test_Instance_Id");
			testname[g]=batch.getString("SQS_TB_Test_Id_Name").trim();	

			System.out.print(instancesss[g]+"\t");
			System.out.print(testname[g]+"\t");

			g++;
		}


		

		for (int x = 1; x < g; x++) 
			{
			startTestCase(testname[x]);
			
			instanceruns = conn.createStatement();

			String sql2="SELECT * FROM SQS_INSTANCE_RUNS where SQS_IR_Test_Instance_Id = " + instancesss[x];
			instruns =instanceruns.executeQuery(sql2);	

			while(instruns.next())
			{

				rid[h]=instruns.getInt("SQS_IR_Run_Id");
					
				System.out.print(rid[h]+"\t");

				h++;
			}
			
			
			
			//stmtTests = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmtTests = conn.createStatement();

			String sql3="SELECT * FROM SQS_TEST_RESULT where SQS_TR_Run_Id = " + (rid[h-1]);
			TestsResults =stmtTests.executeQuery(sql3);
			
			
	
				
				//System.out.println(TestsResults.getFetchSize());
				while(TestsResults.next()) {
					String a=TestsResults.getString("SQS_TR_Actual_Result").trim();
					testlog[i] = a;
					System.out.print(testlog[i]+"\t");
					status[i] = TestsResults.getString("SQS_TR_Step_Status").trim();
					System.out.print(status[i]+"\t");
					screenshot[i] = TestsResults.getString("SQS_TR_ScreenShotPaths").trim();
					System.out.print(screenshot[i]+"\t");
					System.out.print("\n");
	
					i++;
				}
				TestsResults.close();
	
				System.out.println("vaeofi "+i);
	
	
				for (int j = 1; j <i; j++) 
					{
		
						report(testlog[j],status[j],screenshot[j]);
		
					}
	
	
			}




		endtest();
		endflush();

	}





	public static void report(String desc,String status, String methoddesc) throws Throwable
	{



		//long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;

		try {
			new File(userpath+"\\reports\\").mkdir();
			//BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			//ImageIO.write(image, "jpg", new File(userpath+"\\reports\\"+a+"_Run_"+i+"\\images\\step "+number+"_"+methoddesc+".jpg"));
			//FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE) , new File(userpath+"\\reports\\"+a+"_Run_"+i+"\\images\\step "+number+"_"+methoddesc+".jpg"));
		} catch (WebDriverException e) {
			e.printStackTrace();
		}

		// Write if it is successful or failure or information
		if(status.toUpperCase().equals("PASSED")){
			try {
				test.log(LogStatus.PASS, desc+test.addScreenCapture(methoddesc));
			} catch (Exception e) {
				// TODO: handle exception
			}
			///test.log(LogStatus.PASS, desc+test.addScreenCapture(userpath+"\\reports\\"+a+"_Run_"+i+"\\images\\step "+number+"_"+methoddesc+".jpg"));
		}else if(status.toUpperCase().equals("FAILED")){

			try {
				test.log(LogStatus.FAIL, desc+test.addScreenCapture(methoddesc));

			} catch (Exception e) {
				// TODO: handle exception
			}
			//test.log(LogStatus.FAIL, desc+test.addScreenCapture(userpath+"\\reports\\"+a+"_Run_"+i+"\\images\\step#"+number+"_"+methoddesc+".jpg"));
			//throw new RuntimeException("FAILED");
		}else if(status.toUpperCase().equals("INFO")){
			test.log(LogStatus.INFO, desc);
		}
		number++;
	}


	public static void startResult(String batch){

		extent = new ExtentReports(userpath+"\\reports\\"+a+"_GetGoPay_Batch_"+batch+".html", false);

		//File filee=new File(userpath+"\\extent-config.xml");
		//extent.loadConfig(filee);

	}

	public static void startTestCase(String iData){
		test=extent.startTest(iData);
	}

	public static void endtest(){
		extent.endTest(test);
	}

	public static void endflush(){

		extent.flush();
	}
















}
