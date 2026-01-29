package automation.framework.test;

import org.testng.Assert;
<<<<<<< HEAD
import org.testng.annotations.BeforeMethod;
=======
>>>>>>> 8cfbd888dc8a88bc914d1d18b1b2d9d8a57e1a62
import org.testng.annotations.Test;

import automation.framework.base.BaseTest;
import automation.framework.pages.LoginPage;
import automation.framework.utils.ConfigReader;
import automation.framework.utils.DataProviderUtils;

public class LoginTest extends BaseTest{
	
<<<<<<< HEAD
	LoginPage loginpag;

    @BeforeMethod
    public void setUpPage() {
        loginpag = new LoginPage(getDriver());
    }
	
	@Test(description="validLogin", priority=1)
	public void verifyValidLogin() {
		
		loginpag.Login(ConfigReader.getProperty("validUN"), ConfigReader.getProperty("validPWD"));
		//Assert.assertEquals(loginpag.getWelcomeMsg(), ConfigReader.getProperty("welcomemsg"));
		
		//loginpag.Logout();
		//Assert.assertEquals(loginpag.getLogoutMsg(), ConfigReader.getProperty("logoutmsg"));
	}
	
	@Test(description="invalidLogin", priority=2)
	public void verifyInvalidLogin() {
=======
	@Test(description="validLogin", priority=1)
	public void verifyValidLogin() {
		
		LoginPage loginpag = new LoginPage(getDriver());
		loginpag.Login(ConfigReader.getProperty("validUN"), ConfigReader.getProperty("validPWD"));
		Assert.assertEquals(loginpag.getWelcomeMsg(), ConfigReader.getProperty("welcomemsg"));
		
		loginpag.Logout();
		Assert.assertEquals(loginpag.getLogoutMsg(), ConfigReader.getProperty("logoutmsg"));
	}
	
	@Test(description="validLogin", priority=2)
	public void verifyInvalidLogin() {
		LoginPage loginpag = new LoginPage(getDriver()); 
>>>>>>> 8cfbd888dc8a88bc914d1d18b1b2d9d8a57e1a62
		loginpag.Login(ConfigReader.getProperty("invalidUN"), ConfigReader.getProperty("invalidPWD"));
		Assert.assertEquals(loginpag.getloginFailMsg(), ConfigReader.getProperty("loginfailmsg"));
	}
	
<<<<<<< HEAD
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderUtils.class, description="validLogin", priority=1)
	public void verifyLoginDP(String username, String password) {
		loginpag.Login(username, password);
		//Assert.assertEquals(loginpag.getWelcomeMsg(), ConfigReader.getProperty("welcomemsg"));
		
		//loginpag.Logout();
		//Assert.assertEquals(loginpag.getLogoutMsg(), ConfigReader.getProperty("logoutmsg"));
=======
	//@Test(dataProvider = "loginData", dataProviderClass = DataProviderUtils.class, description="validLogin", priority=1)
	public void verifyLoginDP(String username, String password) {
		
		LoginPage loginpag = new LoginPage(getDriver());
		loginpag.Login(username, password);
		Assert.assertEquals(loginpag.getWelcomeMsg(), ConfigReader.getProperty("welcomemsg"));
		
		loginpag.Logout();
		Assert.assertEquals(loginpag.getLogoutMsg(), ConfigReader.getProperty("logoutmsg"));
>>>>>>> 8cfbd888dc8a88bc914d1d18b1b2d9d8a57e1a62
	}
}
