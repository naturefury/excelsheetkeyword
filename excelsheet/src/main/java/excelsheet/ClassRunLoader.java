package excelsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Core.TestAttributes;

public class ClassRunLoader extends TestAttributes {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String[][] runData = null;

		ClassLoaderTestcase loader=new ClassLoaderTestcase();
		
		TestAttributes.loadobjectsprop();
		TestAttributes.runId();
		TestAttributes.createrunfolder();
		utility.Reporter.startResult();			
		TestAttributes.logsetup();
		

		FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\RunPlanner.xlsx"));
		XSSFWorkbook wb=new XSSFWorkbook(file);
		XSSFSheet sh=wb.getSheet("runner");
		int rowcount=sh.getLastRowNum()+1;
		//int colcount=sh.getRow(0).getLastCellNum();
		//System.out.println(rowcount);
		//System.out.println(colcount);
		for (int i = 1; i < rowcount; i++) {

			

			XSSFRow row=sh.getRow(i);

			String tc=row.getCell(0).getStringCellValue();

			if((row.getCell(1).getStringCellValue().toLowerCase().equals("yes"))&& !tc.isEmpty())
			{
				loader.mainrunner(System.getProperty("user.dir")+"\\"+tc+".xlsx",tc);
			}

			else if(tc.isEmpty())
			{
				System.out.println("There is a problem in the RunPlanner excel sheet and issue could be a null/blank values in the excel sheet");
			}


		}	

		utility.Reporter.endflush();

	}

}
