package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	private Properties prop = new Properties();
	private FileInputStream in;
	private FileOutputStream out;
	private String file;
	
	public Configuration(String fileName) {
		try {
			file = fileName;
			in = new FileInputStream(new File (file));
			this.loadProperties();
		}catch(FileNotFoundException e) {
			System.out.println("Atom ERROR :: File "+fileName+" not found in the location!!! ");
		}
	}
	
	private void loadProperties() {
		try {
			prop.load(in);
		}catch(Exception e) {
			System.out.println("Atom ERROR :: Problem while reading the file " + in + " | " + e.getMessage());
		}
	}
	
	public void writeProperty(String propertyName,String propertyValue) {
		try {
			out = new FileOutputStream(new File(file));
			prop.replace(propertyName, propertyValue);
			prop.store(out, "Atom updated "+propertyName);
			out.close();
		} catch (Exception e) {
			System.out.println("Atom ERROR :: Problem while updating the file " + e.getMessage());
		}
	}
	
	public void closeFile() {
		try {
			in.close();
		} catch (IOException e) {
			System.out.println("Atom ERROR :: Problem while closing the file " + e.getMessage());
		}
	}
	
	public String propertyValue(String propertyName) {
		return prop.getProperty(propertyName);
	}
}