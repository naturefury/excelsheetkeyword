package utilities;

import java.io.File;

public class Run {
	
	private Environment env;
	private String ID;
	
	public Run(Environment env) {
		this.env = env;
		ID = Integer.toString(this.env.currentRunID());
		this.env.paths().setRunResults("Run " + ID);
		this.createRunFolder(env.paths().runResults());
	}
	
	private void createRunFolder(String folderName) {
		try {
			File nf = new File(folderName);
			if(nf.mkdir()) {
				//System.out.println("Atom INFO :: Results of the current run will be stored in the folder "+folderName);
			}else {
				System.out.println("Atom INFO :: Prolem while creating run folder");
			}			
		}catch(Exception e) {
			System.out.println("Atom INFO :: Problem while creating run folder "+e.getMessage());
		}
	}
}
