package week6.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ShadowLearning {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev137890.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@123");
		driver.findElement(By.id("sysverb_login")).click();
		Shadow sw= new Shadow(driver);
		sw.setImplicitWait(20);
//		Click All and select Incidents in filter
		sw.findElementByXPath("//div[text()='All']").click();
		sw.setImplicitWait(20);
		sw.findElementByXPath("//span[text()='Incidents']").click();
		
//		Moving to frame under Shadow , click New and get the new incident number
		WebElement frame = sw.findElementByXPath("//iframe[@title='Main Content']");
		driver.switchTo().frame(frame);
		driver.findElement(By.xpath("//button[text()='New']")).click();
		WebElement incNum = driver.findElement(By.xpath("//input[@id='incident.number']"));
		String attribute = incNum.getAttribute("value");
		System.out.println("The new incident number is: "+attribute);
		
//		Fill the details about incident
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Test incident");
		driver.findElement(By.xpath("//button[@id='sysverb_insert_bottom']")).click();
		System.out.println("Incident Created");
		
//		Verify the incident number in Search box
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(attribute,Keys.ENTER);
		String verify = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(verify.equals(attribute))
		{
			System.out.println("Incident verified");
		}
		
		
		
		
		
		
		
		
		
		
	} 

}
