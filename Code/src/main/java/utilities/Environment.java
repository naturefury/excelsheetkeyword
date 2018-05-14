package utilities;

public class Environment {

	private Configuration configuration;
	private Paths paths;
	private int currentRunID;
	
	public Environment() {
		paths = new Paths(this.getProjectLocation());
		configuration = new Configuration(paths.environmentConfiguration());
		setCurrentRunID();
		//configuration.closeFile();
	}
	
	public Paths paths() {
		return paths;
	}
	
	public String appiumURL() {
		return "http:" + configuration.propertyValue("hostIP") + ":" + configuration.propertyValue("hostPort") + "/wd/hub";
	}
	
	public int locaterTimeout() {
		return Integer.parseInt(configuration.propertyValue("locaterTimeout"));
	}
	
	public void setCurrentRunID() {
		int lastRunID = Integer.parseInt(configuration.propertyValue("lastRunID"));
		currentRunID = lastRunID+1;
		configuration.writeProperty("lastRunID", Integer.toString(currentRunID));
	}
	
	public int currentRunID() {
		return currentRunID;
	}
	
	public String appName() {
		return configuration.propertyValue("mainApplication");
	}
	
	public Configuration config() {
		return configuration;
	}
	
	public String getProjectLocation() {
		try {			
			String PackageLocation = Environment.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			String[] PackageLocationSplit = PackageLocation.split("/");
			String CodePath = "";
			if (!PackageLocation.toLowerCase().contains(".jar"))
				for (int i = 1;i<PackageLocationSplit.length-3;i++)
					CodePath = CodePath + PackageLocationSplit[i] + "/";
			else				
				for (int i = 1;i<PackageLocationSplit.length-1;i++)
					CodePath = CodePath + PackageLocationSplit[i] + "/";
			return CodePath.trim();		
		} catch (Exception e) {
			System.out.println("Atom ERROR :: Problem while fetching the project location "+e.getMessage());
			return null;
		}
	}
	
	public void printBeforeScenario(String scenarioName) {
		System.out.println("*************************************************************************");
		System.out.println("         Executing Scenario [ "+scenarioName.toUpperCase()+" ]");
		System.out.println("*************************************************************************");
	}
	
	public void printAfterScenario() {
		System.out.println(" ");
		System.out.println("");
	}
}