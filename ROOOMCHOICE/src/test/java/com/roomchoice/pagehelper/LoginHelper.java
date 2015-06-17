package com.roomchoice.pagehelper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.roomchoice.locator.LocatorReader;
import com.roomchoice.util.DriverHelper;


public class LoginHelper extends DriverHelper {
	
	private LocatorReader loginLocator;	
	
	public LoginHelper(WebDriver driver) {
		super(driver);	
		loginLocator = new LocatorReader("Login.xml");
	}
	
	//verify login page
	public void verifyLoginPage(){		
		String locator = loginLocator.getLocator("Login.UserNameTab");
		String userName = getText(locator);
		Assert.assertTrue(userName.contains("Username"));
		String locator1 = loginLocator.getLocator("Login.PasswordTab");
		String password = getText(locator1);		
		Assert.assertTrue(password.contains("Password"));
	}
	
	
	
	public void loginWithManager(String email, String password)
	{
		
	    getWebDriver().findElement(By.linkText("Login")).click();
		getWebDriver().findElement(By.name("email")).clear();
		getWebDriver().findElement(By.name("email")).sendKeys(email);
		getWebDriver().findElement(By.name("password")).clear();
		getWebDriver().findElement(By.name("password")).sendKeys(password);
		getWebDriver().findElement(By.cssSelector("button.button-small")).click();
		//getWebDriver().findElement(By.cssSelector("div.lease-table-wrapper.notr-hover")).click();
		//getWebDriver().findElement(By.xpath("(//a[contains(@href, '')])[3]")).click();
		//getWebDriver().findElement(By.linkText("Log Out")).click();
	}
	
	

	//enter user name
	public void enterUserName(String username)
	{
		String locator = loginLocator.getLocator("Login.UserName");
		sendKeys(locator, username);
	}
	
	//enter password
	public void enterPossword(String password)
	{
		String locator = loginLocator.getLocator("Login.Password");
		sendKeys(locator, password);
	}	
	
	//click on sign in
	public void clickOnSignIn()
	{
		String locator = loginLocator.getLocator("Login.SignIn");
		clickOn(locator);
		//clickOn(com.videomanager.locators.login_locators.clicksign);
		//javaScriptExecute("window.document.getElementsByClassName('btn btn-lg btn-primary btn-block')[0].click()");
		getWebDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
	}
	
	//click on login
	public void clickLogin()
	{
		String locator = loginLocator.getLocator("Login.SignIn");
		clickOn(locator);
	}
	
	//verify that validation message 
			public String verifyLoginValidationWebAPI(){
				String locator = loginLocator.getLocator("Login.ValidationMessage");
				String text = getText(locator);				
				return text;
			}
		
	
	//verify that validation message 
			//verify that validation message 
			public String verifyLoginValidation(){
				String locator = loginLocator.getLocator("Login.ValidationMessage");
				String text = getText(locator);				
				return text;
			}
		
		//get validation message when no permission
		public String getNoPermissionErrorMsg()
		{
			String locator = loginLocator.getLocator("Login.NoPermissionMsg");
			String text = getText(locator);				
			return text;
			
		}
			
		
	//get validation message when enabled button is OFF
		public String getEnabledButtonOffValidation()
		{
			String locator = loginLocator.getLocator("Login.EnabledOffValidation");
			String text = getText(locator);				
			return text;
			
		}
		
		
		//get validation message when enabled button is OFF and blocked user
		public String getValidationOfBlock()
		{
			String locator = loginLocator.getLocator("Login.BlockUserValidation");
			String text = getText(locator);				
			return text;
			
		}

		
		
		
		//Enter new password
		public void enterNewPassword(String newPassword)
		{
			String locator = loginLocator.getLocator("Login.NewPassword");
			clearTextField(locator);
			sendKeys(locator, newPassword);
			
		}
					
		
		//Enter new confirm password
		public void enterNewConfirmPassword(String newConfirmPassword)
		{
			String locator = loginLocator.getLocator("Login.NewPasswordConfirm");
			clearTextField(locator);
			sendKeys(locator, newConfirmPassword);
			
		}
	}
	
	
	
	
