package singleclaskeyword;

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

public class ClassSingleRun {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		String[][] runData = null;



		FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\Runsinglefile"+".xlsx"));
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
				Class<?> getclass=null;
				getclass=Class.forName("Core.WebKeywordLibrary");

				Object obj=getclass.newInstance();
				//loading the methods of Test attributes her

				TestAttributes.loadobjectsprop();

				//int ccolcount=csh.getRow(0).getLastCellNum();


				String keyword;
				String locator;
				String data;

				keyword=row.getCell(2).getStringCellValue().trim();
				locator=row.getCell(3).getStringCellValue().trim();
				data=row.getCell(4).getStringCellValue().trim();

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
			else if(tc.isEmpty())
			{
				System.out.println("There is a problem in the RunPlanner excel sheet and issue could be a null/blank values in the excel sheet");
			}
		}
	}
}
