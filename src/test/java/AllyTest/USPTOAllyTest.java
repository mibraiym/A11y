package AllyTest;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import io.github.bonigarcia.wdm.WebDriverManager;

public class USPTOAllyTest {
	
	WebDriver driver;
	
	private static final URL scriptUrl = USPTOAllyTest.class.getResource("/axe.min.js");

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://uspto.gov");
	}
	
	@Test
	public void usptoAllyTest() {
		JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray violations = responseJson.getJSONArray("violations");
	
	if(violations.length() == 0) {
		System.out.println("no errors");
	}
	else {
		AXE.writeResults("usptoAllyTest", responseJson);
		Assert.assertTrue(false, AXE.report(violations));
		}
	}

	
	/*
	@Test
	public void verifyTitle(){
		String title = driver.getTitle();
		Assert.assertEquals(title, "Home - Gunnison Consulting Group");
	}
	*/
	
	
	/*
	@AfterMethod
	public void closebrowser() {
		driver.quit();
	}
	*/
}

