package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders // in this class we can add multiple data provider methods 
{

	//Dataprovider 1 
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testData\\Opencart_logindata.xlsx"; //taking xl file from testdata
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object of xlutility file
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String [totalrows][totalcols]; //create for two dimensional  array 
		
		for(int i=1; i<=totalrows; i++)  //1 //read the data from xl storing in two dimensional array 
		{
			for(int j =0; j<totalcols; j++) //0 //i is rows j is col
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1, 0 (i-1 means array index will start from zero)
			}
		}
		
		return logindata; // returning two dimension array 
		
		
		
		
		
		
		
		
	}
}
