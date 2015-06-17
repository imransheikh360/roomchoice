package com.roomchoice.TestScripts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.concurrent.TimeUnit;
import mx4j.remote.HeartBeat;
import org.testng.annotations.Test;
import com.roomchoice.pagehelper.LoginHelper;
import com.roomchoice.util.DriverTestCase;
import com.roomchoice.util.PropertyReader;
import org.testng.*;
import org.testng.asserts.*;
import org.testng.annotations.*;
import static org.junit.Assert.*;



public class LoginWithManager extends DriverTestCase{

	@Test
	public void loginWithManager() throws InterruptedException{
		
		//Log into the application with valid credential
		String email = propertyReader.readApplicationFile("Email");	
		String password = propertyReader.readApplicationFile("MPassword");	
		//loginHelper.enterUserName(username);
		//loginHelper.enterPossword(password);
		//loginHelper.clickOnSignIn();
		System.out.println("my app pass is:" + password);
		loginHelper.loginWithManager(email, password);
				
		
		
	}
	
}
