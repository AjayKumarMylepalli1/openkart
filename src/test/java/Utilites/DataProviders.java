package Utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [] [] getData() throws IOException{
		String path=".\\testData\\Opencart_LoginData.xlsx"; // taking xl file from test data
		ExcelUtility xlutil =new ExcelUtility(path);  // creating an object for XLUtility
		
		int totalrows = xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("sheet1",1);
		
		String logindata [] []= new String[totalrows][totalcols]; // created for twwo demnsional
		
		for(int i=1;i<=totalrows;i++)//1  // read the data from xl storing in two demisional array
		{
			for(int j=0;j<totalcols;j++) //0 i is rows j is cols 
			{
				logindata[i-1][j] =xlutil.getCellData("sheet1", i, j); //1,0
				//System.out.println(" Data  from excel :" +logindata[i-1][j]);
			}
		}
		return logindata;  // return ing TWo - demensioal Array
	}
	
	// Dataprovider 2
	
	
	//Data provider 3
	
	
	//Dataprovider 4
}
