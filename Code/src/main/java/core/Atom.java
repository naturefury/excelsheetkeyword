package core;

import utilities.Environment;
import utilities.Mobile;
import utilities.Run;

public class Atom {
	
	private Environment env;
	private Run run;
	private Mobile mobile;
	
	public Atom() {
		this.env = new Environment();
		this.run = new Run(this.env);
		this.mobile = new Mobile(this.env);
	}
	
	public Environment environment() {
		return env;
	}
	
	public Run run() {
		return run;
	}

	public Mobile mobile() {
		return mobile;
	}
}
