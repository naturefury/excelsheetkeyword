package utilities;

import java.time.LocalDateTime;
import com.cucumber.listener.Reporter;
import testrunner.Runner;

public class LogExtent extends Runner{

	public void attachScreenshot(String fileName) {
		try {
		String imgName = atom.mobile().takeScreenshot(fileName);
		String currentDir = System.getProperty("user.dir");
		System.setProperty("user.dir", atom.environment().paths().runResults());
		Reporter.addScreenCaptureFromPath("images/"+imgName);
		System.setProperty("user.dir", currentDir);
		}catch(Exception e) {
			System.out.println("ATOM INFO "+LocalDateTime.now()+" :: Problem while attaching the screenshot to extent report");
		}
	}
}
