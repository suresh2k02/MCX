package mcxindia;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskOne {
	public WebDriver driver;

	

	@Test
	public void login() throws Exception  {
		
		       
		Thread.sleep(5000);
		driver.findElement(By.linkText("MARKET DATA")).click();
		Thread.sleep(1000);

		driver.findElement(By.linkText("Market Watch")).click();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		new Select(driver.findElement(By.id("ddlInstrumentName"))).selectByVisibleText("FUTCOM");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//a[@target='_blank'][normalize-space()='GOLD'])[1]")).click();
		Thread.sleep(1000);
		
		String defaulWin =driver.getWindowHandle();

		Set<String> allwin=driver.getWindowHandles();
		
		Iterator<String> it=allwin.iterator();
		
		it.next();
		
		String childwin=it.next();
		
		driver.switchTo().window(childwin);
		//System.out.println(driver.getTitle());

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//System.out.println(driver.getTitle());
		
		
		driver.findElement(By.xpath("//a[@id='aRefresh']//strong[contains(text(),'Refresh')]")).click();
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		String befour_refresh=driver.findElement(By.xpath("//span[@id='litPrice']")).getText();
        

		String dateTimeBeforeRefresh = getCurrentDateTime();
        System.out.println("Before Refresh: " + dateTimeBeforeRefresh);
        
        
		System.out.println("The gold value:"+ befour_refresh);
		
		
		int res1=Integer.parseInt(befour_refresh);  
		

		Thread.sleep(120000);

		
		
		driver.findElement(By.xpath("//a[@id='aRefresh']//strong[contains(text(),'Refresh')]")).click();
		//driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String after_refresh=driver.findElement(By.xpath("//span[@id='litPrice']")).getText();

		String dateTimeAfterWait = getCurrentDateTime();
	    
		System.out.println("After Waiting 2 Minutes: " + dateTimeAfterWait);

		
		System.out.println("The gold value:"+ after_refresh);
		

		int res2=Integer.parseInt(after_refresh);  

		
		int diff=res2-res1;
		System.out.println("The Gold Diffrence is :"+diff);




		
	}
	
	private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
	
	@BeforeTest
	public void openurl() throws Exception {

		//WebDriverManager.firefoxdriver().setup();
		WebDriverManager.chromedriver().setup();

		
		
		//  driver = new FirefoxDriver();
		  driver = new ChromeDriver();

		  driver.get("https://www.mcxindia.com/");
		  driver.manage().window().maximize();
		  Thread.sleep(5000);
		  
		
	}

	@AfterTest
	public void afterTest() {
		
		driver.quit();
		

	}

}
