package AllyTest;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GunnisonAlly {

	WebDriver driver;
	//String title;
	
	private static final URL scriptUrl = GunnisonAlly.class.getResource("/axe.min.js");

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//driver.get("https://gunnisonconsulting.com/");
	}
	
	
	public void gunnisonAllyTest(String pageName) {
		JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray violations = responseJson.getJSONArray("violations");
	
	if(violations.length() == 0) {
		System.out.println("no errors");
	}
	else {
		AXE.writeResults(pageName, responseJson);
		Assert.assertTrue(false, AXE.report(violations));
		}
	}

	@BeforeMethod
	public void goToGunHome() {
		driver.get("https://gunnisonconsulting.com/");
	}
	
	
	@Test 
	public void verifyGunnisonHome(){
		gunnisonAllyTest(driver.getTitle());
	}
	
	
	@Test 
	public void verifyCapabilities(){
		driver.findElement(By.id("menu-item-26")).click();
		gunnisonAllyTest(driver.getTitle());
	}
	
	@Test 
	public void verifyAboutUs(){
		driver.findElement(By.id("menu-item-28")).click();
		gunnisonAllyTest(driver.getTitle());
	}
	
	@AfterClass
	public void closebrowser() {
		driver.quit();
	}
	
	
}
