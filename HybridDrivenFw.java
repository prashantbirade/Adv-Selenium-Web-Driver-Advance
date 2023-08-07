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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HybridDrivenFw {
	@DataProvider(name = "abhi")
	public Object[][] readExcel() throws InvalidFormatException, IOException {
           Object[][] data=null;
           
           //to get a filpath
           String filepath="C:\\abhi1\\2nd_march_poi.xlsx";
           
           
           //to make a file
           //File file=new File(filepath);
           File file=new File(filepath);
           
           //to open a excel file
           XSSFWorkbook workbook=new XSSFWorkbook(file);
           //to get a particular sheet
           
           Sheet sheet=workbook.getSheet("Sheet3");
           
           //to get a no of row
           
           int n_row=sheet.getPhysicalNumberOfRows();
           System.out.println("total number of rows are:"+n_row);
           
           
           
           //to set no row using jagged array 
           data=new Object[n_row][];
           for (int i = 0; i < data.length; i++) {
			//to select a particular row
        	   Row row=sheet.getRow(i);
        	   int n_col=row.getPhysicalNumberOfCells();
        	   
        	   
        	   
        	   //set no of col for row
        	  data[i]=new Object[n_col];
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
		    @Test(dataProvider = "abhi")
	        public void Test(String keyword) throws InterruptedException, InvalidFormatException, IOException {
			
	System.setProperty("webdriver.chrome.driver","C:\\abhi1\\chromedriver_win32\\chromedriver.exe");
			Object[][] data= readExcel();
			
			if (keyword.equals("open browser")) {
				driver=new ChromeDriver();
				Thread.sleep(2000);
			}
	                else if (keyword.equals("enter url")) {
				driver.get("https://www.saucedemo.com/");
				Thread.sleep(3000);

			}
			else if (keyword.equals("enter name")) {
				driver.findElement(By.id("user-name")).sendKeys("standard_user");
				Thread.sleep(2000);
			}
			else if (keyword.equals("enter password")) {
				driver.findElement(By.id("password")).sendKeys("secret_sauce");
				 Thread.sleep(2000);
			}
			else if (keyword.equals("click login")) {
				driver.findElement(By.id("login-button")).click();
				 Thread.sleep(1000);	
				 
				 if (driver.getCurrentUrl().equals("https://www.saucedemo.com/")) {
					 System.out.println("this is test is pass");
					
				} else {
					System.out.println("this test is Fail");

				}
				 driver.findElement(By.id("react-burger-menu-btn")).click();
				 Thread.sleep(1000);

				 driver.findElement(By.linkText("Logout")).click();
				 Thread.sleep(1000);
			
			}
			else if (keyword.equals("close browser")) {
				driver.close();
			}
			
	}

}
