package automation.framework.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.framework.base.BaseTest;
import automation.framework.pages.RegistrationPage;
import automation.framework.utils.ConfigReader;
import automation.framework.utils.DataProviderUtils;
import automation.framework.utils.RegistrationDataPOJO;

public class RegistrationTest extends BaseTest {

	RegistrationPage regPage;

	@BeforeMethod
	public void setUpReg() {
		launchUrl(ConfigReader.getProperty("registerURL"));
		regPage = new RegistrationPage(getDriver());
	}

	@Test(dataProvider = "registrationData", dataProviderClass = DataProviderUtils.class)
	public void verifyUserRegistration(RegistrationDataPOJO data) {
		regPage.registerUser(data);
	}

}
