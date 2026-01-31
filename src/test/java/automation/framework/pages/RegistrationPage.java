package automation.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.framework.utils.RegistrationDataPOJO;

public class RegistrationPage {

	private WebDriver driver;
	
	//Constructor
		public RegistrationPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	//Locators	
	@FindBy(xpath="//input[@id='customer.firstName']")
	WebElement firstname;
	
	@FindBy(xpath="//input[@id='customer.lastName']")
	WebElement lastname;
	
	@FindBy(xpath="//input[@id='customer.address.street']")
	WebElement address;
	
	@FindBy(xpath="//input[@id='customer.address.city']")
	WebElement city;
	
	@FindBy(xpath="//input[@id='customer.address.state']")
	WebElement state;
	
	@FindBy(xpath="//input[@id='customer.address.zipCode']")
	WebElement zipcode;
	
	@FindBy(xpath="//input[@id='customer.phoneNumber']")
	WebElement phonenumber;
	
	@FindBy(xpath="//input[@id='customer.ssn']")
	WebElement ssn;
	
	@FindBy(xpath="//input[@id='customer.username']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='customer.password']")
	WebElement pasword;
	
	@FindBy(xpath="//input[@id='repeatedPassword']")
	WebElement confirmPwd;
	
	@FindBy(css="[value=\"Register\"]")
	WebElement registerBtn;
	
	//Actions
	public void feedRegistrationForm(RegistrationDataPOJO data) {
		
		firstname.sendKeys(data.getFirstname());
		lastname.sendKeys(data.getLastname());
		address.sendKeys(data.getAddress());
		city.sendKeys(data.getCity());
		state.sendKeys(data.getState());
		zipcode.sendKeys(data.getZipcode());
		phonenumber.sendKeys(data.getPhonenumber());
		ssn.sendKeys(data.getSsn());
		username.sendKeys(data.getUsername());
		pasword.sendKeys(data.getPasword());
		confirmPwd.sendKeys(data.getConfirmPwd());
	}
	public void clickRegister() {
		registerBtn.click();
	}
	
	public void registerUser(RegistrationDataPOJO data) {
		feedRegistrationForm(data);
		clickRegister();
	}
	
}
