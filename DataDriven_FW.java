package Com.DataDriven;
import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DataDriven_FW {
	@Test
	public String[][] readExcel() throws InvalidFormatException, IOException {
           String[][] data=null;
           
           //to get a filpath
           String filepath="D:\\Prashant Selennium\\chromedriver_win32\\Book1.xlsx";
           
           
           //to make a file
           //File file=new File(filepath);
           File file=new File(filepath);
           
           //to open a excel file
           XSSFWorkbook workbook=new XSSFWorkbook(file);
           //to get a particular sheet
           
           Sheet sheet=workbook.getSheet("Sheet1");
           
           //to get a no of row
           
           int n_row=sheet.getPhysicalNumberOfRows();
           System.out.println("total number of rows are:"+n_row);
           
           
           
           //to set no row using jagged array 
           data=new String[n_row][];
           for (int i = 0; i < data.length; i++) {
			//to select a particular row
        	   Row row=sheet.getRow(i);
        	   int n_col=row.getPhysicalNumberOfCells();
        	   
        	   
        	   
        	   //set no of col for row
        	  data[i]=new String[n_col];
        	   for (int j = 0; j < data[i].length; j++) {
        		   //to select a particular col
        		   Cell cell=row.getCell(j);     		   
        		   //to set cell type to string        		   
        		   cell.setCellType(CellType.STRING);
      	           //TO GET DATA FROM A PARTICULAR CELL
        		   data[i][j]=cell.getStringCellValue();	
			}
        	}
           
		return data;
           
	}
	
	
	WebDriver driver=null;
	@Test
        public void Test() throws InterruptedException, InvalidFormatException, IOException {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Prashant Selennium\\chromedriver_win32\\chromedriver.exe");
		
		
		String[][] data=readExcel();
		for (int i = 0; i < data.length; i++) {
		
	driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(2000);

		driver.findElement(By.id("user-name")).sendKeys(data[i][0]);
		Thread.sleep(2000);
		
		driver.findElement(By.id("password")).sendKeys(data[i][1]);
		 Thread.sleep(2000);
		 
		 
		 if (driver.getCurrentUrl().equals("https://www.saucedemo.com/")) {
			 System.out.println("this is test is pass");
			
		} else {
			System.out.println("this test is Fail");

		}
	
		driver.findElement(By.id("login-button")).click();
		 Thread.sleep(1000);	

	 driver.findElement(By.id("react-burger-menu-btn")).click();
	 Thread.sleep(1000);

	 driver.findElement(By.linkText("Logout")).click();
	 Thread.sleep(1000);
	 
		driver.close();
}
	
	}
 

}

