package automation.framework.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class capturescreenshot {

	public static void capture() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://github.com/shah1509/SeleniumAutomation.Framework/blob/main/src/test/java/automation/framework/utils/ScreenshotsUtils.java");
	
	TakesScreenshot ts =(TakesScreenshot) driver;
	
	String timestamp = new SimpleDateFormat("yyyyMMdd.hhmmss").format(new Date());
	String path = System.getProperty("user.dir")+"/screenshots/"+"myScreenshot"+timestamp+".png";
	
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(path);
	
	try {
		FileUtils.copyFile(source, target);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
	
}
