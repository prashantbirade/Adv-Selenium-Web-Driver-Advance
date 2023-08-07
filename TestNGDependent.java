import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class TestNGDependent {
	WebDriver driver = null;
	@Test
	public void beforetest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Prashant Selennium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(2000);
	}
	@Ignore
	@Test(dependsOnMethods = {"beforetest"})
	public void Login() throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		Thread.sleep(2000);
	}
	@Test(dependsOnMethods = {"Login"})
		public void password() throws InterruptedException {
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			Thread.sleep(2000);
		}
	@Test(dependsOnMethods = {"password"})
		public void loginbutton() throws InterruptedException {
			driver.findElement(By.id("login-button")).click();
			Thread.sleep(2000);
		}
	@Test(dependsOnMethods = {"loginbutton"})
		public void menu() throws InterruptedException {
			driver.findElement(By.id("react-burger-menu-btn")).click();
			Thread.sleep(2000);
	
		}
	@Test(dependsOnMethods = {"menu"})
		public void logout() throws InterruptedException {
			driver.findElement(By.id("logout_sidebar_link")).click();
			Thread.sleep(2000);	
		}

	@AfterTest
	public void aftertest() {
		driver.close();

	}

}

