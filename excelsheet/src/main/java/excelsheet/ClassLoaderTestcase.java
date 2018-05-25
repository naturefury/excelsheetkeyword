package excelsheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Core.TestAttributes;



public class ClassLoaderTestcase extends TestAttributes {

	public void mainrunner(String filename,String testcase) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		System.out.println("Got file name "+filename);
		Class<?> getclass=null;
		getclass=Class.forName("Core.WebKeywordLibrary");

		Object obj=getclass.newInstance();
		//loading the methods of Test attributes her
		
		
		utility.Reporter.startTestCase(testcase);
		

		FileInputStream cfile=new FileInputStream(new File(filename));
		XSSFWorkbook cwb=new XSSFWorkbook(cfile);
		XSSFSheet csh=cwb.getSheetAt(0);
		int crowcount=csh.getLastRowNum()+1;
		//int ccolcount=csh.getRow(0).getLastCellNum();
		for (int i = 1; i < crowcount; i++) {
			XSSFRow crow=csh.getRow(i);


			String keyword;
			String locator;
			String data;

			keyword=crow.getCell(0).getStringCellValue().trim();
			locator=crow.getCell(1).getStringCellValue().trim();
			data=crow.getCell(2).getStringCellValue().trim();

			Method[] cmethod=getclass.getDeclaredMethods();

			for (Method method : cmethod) {

				if(keyword.toLowerCase().equals(method.getName().toLowerCase()))
				{
					if(locator.equals("") && data.equals(""))
						method.invoke(obj);
					else if(locator.equals(""))
						method.invoke(obj,data);
					else if(data.equals(""))
						method.invoke(obj,locator);
					else
						method.invoke(obj,locator,data);
				}

			}





		}
		utility.Reporter.endtest();
		



	}

}
