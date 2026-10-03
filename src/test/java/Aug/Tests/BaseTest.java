package Aug.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import Aug.DriverPackage.DriverFactory;
import Aug.DriverPackage.DriverManager;
import Aug.Utils.configreader;

public class BaseTest {

protected WebDriver driver;
protected configreader config = new configreader();


	@BeforeMethod(alwaysRun=true)
	public void SetupBrowser() {
		WebDriver newDriver=DriverFactory.CreateDriver(config.getPropertyval("browser"));
		DriverManager.setdriver(newDriver);
		driver=DriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().deleteAllCookies();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		DriverManager.unload();
	}
	
	
	public static String addscreenshot(String name) {
	
		String timeStamp=new SimpleDateFormat("YYYY-MM-DD-HHss").format(new Date());
		TakesScreenshot sc=(TakesScreenshot)DriverManager.getDriver();
		
		File Source=sc.getScreenshotAs(OutputType.FILE);
		
		String path= System.getProperty("user.dir")+"/Screen/"+name+timeStamp+".png";

		File target=new File(path);
		target.getParentFile().mkdir();
		
		try {
			Files.copy(Source.toPath(), target.toPath(),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
		
	}
	
	
	
	public String addscreenshotd(String name) {
	    String sd = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    TakesScreenshot sc = (TakesScreenshot) DriverManager.getDriver();
	    File source = sc.getScreenshotAs(OutputType.FILE);
	    String path = System.getProperty("user.dir")
	            + "/Screen/"
	            + name + "_" + sd + ".png";
	    File target = new File(path);
	   target.getParentFile().mkdirs();
	    try {
	        Files.copy(
	            source.toPath(),
	            target.toPath(),
	            StandardCopyOption.REPLACE_EXISTING
	        );
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return path;
	}
	
	
	
	
	
	
	
	
	
}
