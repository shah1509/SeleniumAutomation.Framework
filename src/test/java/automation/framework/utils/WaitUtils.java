package automation.framework.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitUtils {

	    private WebDriverWait wait;

	    public WaitUtils(WebDriver driver, long timeoutInSeconds) {
	        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    }

	    public WebElement waitForElementToBeVisible(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    public WebElement waitForElementToBeClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	    
	    public void waitForElementToBeInvisible(WebElement element) {
	    	 wait.until(ExpectedConditions.invisibilityOf(element));
	    }
	}
