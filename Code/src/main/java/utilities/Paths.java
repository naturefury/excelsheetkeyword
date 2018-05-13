package utilities;

public class Paths {
	
	private String configurations,testResults,frameworkLocation,runResults;
	
	public Paths(String projectLocation) {
		this.frameworkLocation = projectLocation;
		this.configurations = frameworkLocation + "Configurations/";
		this.testResults = frameworkLocation + "Results/";
	}
	
	public String environmentConfiguration() {
		return configurations + "env.properties";
	}
	
	public String reportConfiguration() {
		return configurations + "extent.xml";
	}
	
	public void setRunResults(String folderName) {
		runResults = testResults+folderName+"/";
	}
	
	public String runResults() {
		return runResults;
	}

	public String apps() {
		return frameworkLocation + "apps/";
	}
}
