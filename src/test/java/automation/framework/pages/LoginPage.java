package automation.framework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.framework.utils.WaitUtils;

public class LoginPage {

	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	private WaitUtils wait;

	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		this.wait = new WaitUtils(driver, 10);
	}
	
	
	//Locators
	@FindBy(xpath="//input[@name=\"username\"]")
	private WebElement usernameBox; 
	
	@FindBy(css="input[name='password']")
	private WebElement passwordBox; 
	
	@FindBy(css="input[value='Log In']")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[text()='Welcome']")
	private WebElement welcomeMsg;
	
	@FindBy(xpath="//*[text()='Log Out']")
	private WebElement logoutBtn;
	
	@FindBy(xpath="//*[text()='Customer Login']")
	private WebElement logoutMsg;
	
	@FindBy(xpath="//*[text()='Error!']")
	private WebElement loginFailureMsg;
	
	//Actions
	public void enterUsername(String username) {
		logger.info("Entering username");
		wait.waitForElementToBeVisible(usernameBox).sendKeys(username);
	}
	public void enterPassword(String password) {
		logger.info("Entering password");
		wait.waitForElementToBeVisible(passwordBox).sendKeys(password);
	}
	public void clickLogin() {
		logger.info("clicking login button");
		wait.waitForElementToBeClickable(loginBtn).click();
	}
	public void Logout() {
		logger.info("Clicking logout button");
		logoutBtn.click();
	}
	public String getWelcomeMsg() {
		String msg=wait.waitForElementToBeVisible(welcomeMsg).getText();
		return msg;
	}
	public String getLogoutMsg() {
		String outmsg=wait.waitForElementToBeVisible(logoutMsg).getText();
		return outmsg;
	}
	public String getloginFailMsg() {
		String failmsg=wait.waitForElementToBeVisible(loginFailureMsg).getText();
		return failmsg;
	}
	
	//Functions
	public void Login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}	
	
}
