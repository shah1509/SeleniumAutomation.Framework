package automation.framework.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.framework.base.BaseTest;
import automation.framework.pages.LoginPage;
import automation.framework.utils.ConfigReader;
import automation.framework.utils.DataProviderUtils;

public class LoginTest extends BaseTest{
	
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
		loginpag.Login(ConfigReader.getProperty("invalidUN"), ConfigReader.getProperty("invalidPWD"));
		Assert.assertEquals(loginpag.getloginFailMsg(), ConfigReader.getProperty("loginfailmsg"));
	}
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderUtils.class, description="validLogin", priority=1)
	public void verifyLoginDP(String username, String password) {
		loginpag.Login(username, password);
		//Assert.assertEquals(loginpag.getWelcomeMsg(), ConfigReader.getProperty("welcomemsg"));
		
		//loginpag.Logout();
		//Assert.assertEquals(loginpag.getLogoutMsg(), ConfigReader.getProperty("logoutmsg"));
	}
}
