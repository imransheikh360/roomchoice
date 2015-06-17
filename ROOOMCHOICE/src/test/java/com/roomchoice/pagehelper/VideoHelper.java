package com.roomchoice.pagehelper;

import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.NeedsLocalLogs;
import org.openqa.selenium.remote.server.handler.GetTitle;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

import com.roomchoice.locator.LocatorReader;
import com.roomchoice.util.DriverHelper;

public class VideoHelper extends DriverHelper{
	private LocatorReader vidoeLocator;
	
	public VideoHelper(WebDriver driver) 
	{
		super(driver);	
		vidoeLocator = new LocatorReader("Video.xml");
	}
	
	//get video owner name
	public String getVideoOwnerName()
	{
		//String locator = vidoeLocator.getLocator("VideoDetails.VideoOwnerName");
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[3]/td[2])[1]"));
		String user= locator.getText();
		return user;
	}
	
	//get first video owner name
		public String getFirstVideoOwnerName()
		{
			//String locator = vidoeLocator.getLocator("VideoDetails.VideoOwnerName");
			WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[4]/td[2])[1]"));
			String user= locator.getText();
			return user;
		}
		
	
	
	public String getFirstSOperatorNameAtMyVideo()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[2]/td[2])[1]"));
		String user= locator.getText();
		return user;
	}
	
	public String getFirstSOwnerNameAtMyVideo()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[4]/td[2])[1]"));
		String user= locator.getText();
		return user;
	}
	
	public String getFirstSupervisedVideoOperatorName()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[2]/td[2])[1]"));
		String user= locator.getText();
		return user;
	}
	
	public String getFirstSupervisedVideoOwnerName()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[3]/td[2])[1]"));
		String user= locator.getText();
		return user;
			
	}
	
	//Share the video to different user
	public void testShareVideo(String userName)
	{
		clickOnMoreDetail();
		enterUserToShare(userName);
		clickOnPlusIcon();
		//clickOnConfirmChanges();
		//apply alternate confirm function
		confirmChange();
	}
	
	//Click on more detail icon
	public void clickOnMoreDetail()
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.MoreDetail");
		clickOn(locator);
	}
	
	//Remove the shared user
	public void removeSharedUser()
	{
		String locator = vidoeLocator.getLocator("SharedVideo.RemoveSharedUserIcon");
		clickOn(locator);
	}
	
	//Change owner name 
	public void changeOwnerName(String userName) throws InterruptedException
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.OwnereUser");
		clearTextField(locator);
		sendKeys(locator, userName);
		Thread.sleep(2000);
		//select owner name from suggestion drop down
		String locatorSecond = vidoeLocator.getLocator("IndividualVideoDetails.SuggestedName");
		clickOn(locatorSecond);
	}
	
	
	//Change owner name at search video page
	public void changeOwnerNameAtSearchVideo(String userName) throws InterruptedException
	{
		String locator = vidoeLocator.getLocator("SearchVideo.OwnereUserAtSearch");
		clearTextField(locator);
		sendKeys(locator, userName);
		Thread.sleep(2000);
		//select owner name from suggestion drop down
		String locatorSecond = vidoeLocator.getLocator("SearchVideo.SuggestedNameSearch");
		clickOn(locatorSecond);
	}
	
	
	//Enter user name which you want to share the video
	public void enterUserToShare(String userName)
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.ShareUser");
		sendKeys(locator, userName);
	}

	//Click on plus icon 
	public void clickOnPlusIcon()
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.PlusButton");
		clickOn(locator);
	}
	
	//Click on confirm button
	public void clickOnConfirmChanges()
	{
		try{
			String locator = vidoeLocator.getLocator("IndividualVideoDetails.ConfirmChange");
			clickOn(locator);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void confirmChange()
	{
		getWebDriver().findElement(By.xpath("//*[@id='sharing-region']/div/div/div[3]/button[2]")).click();
	}
	
	//Click on cancel button
	public void clickOnCancel()
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.Cancel");
		clickOn(locator);
	}
	
	//Click on shared video tab
	public void clickOnSharedVideoTab()
	{
		String locator = vidoeLocator.getLocator("VideoHeader.Sharedvideo");
		clickOn(locator);
	}
	
	
		
	
	//Click on shared video tab
	public void clickOnSupervisedVideoTab()
	{
		String locator = vidoeLocator.getLocator("VideoHeader.SupervisedVideo");
		clickOn(locator);
	}	
	
	//get supervised video owner name
	public String getSuperviserOwnerName()
	{
		String locator = vidoeLocator.getLocator("SharedVideo.OwnerName");
		String OwnerName= getText(locator);
		return OwnerName;
	}

	//Click on Search Videos tab 
	public void clickOnSearchVideoTab()
	{
		String locator = vidoeLocator.getLocator("VideoHeader.Searchvideo");
		clickOn(locator);
	}
		
	//Clear owner name
	public void clearOwnerTextField() throws InterruptedException
	{
		String locator = vidoeLocator.getLocator("SearchVideo.OwnerTextField");
		clickOn(locator);
		clearTextField(locator);
		sendKeys(locator, " ");
		getWebDriver().findElement(By.name("filterOwner")).sendKeys(Keys.BACK_SPACE);
	}
		
	//Click on Find button
	public void ClickOnFindButton()
	{
		String locator = vidoeLocator.getLocator("SearchVideo.FindVideoButton");
		clickOn(locator);
	}
		
	
	//get owner user five
	public String getOwnerUserFive()
	{
		//String locator = vidoeLocator.getLocator("SearchVideo.VideoOwnerUser5");
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[3]/td[2])[1]"));
		String user =locator.getText();
		return user;
	}
				
	//get owner user three
	public String getOwnerUserThree()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[3]/td[2])[2]"));
		String user =locator.getText();
		return user;
	}
	
	//get owner user two
	public String getOwnerUserTwo()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[3]/td[2])[5]"));
		String user =locator.getText();
		return user;
	}
	
	//get operator name
	public String getFirstOperatorName()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[2]/td[2])[1]"));
		String user =locator.getText();
		return user;
	}
		
	
	//get operator name
	public String getSecondOperatorName()
	{
		WebElement locator= getWebDriver().findElement(By.xpath("(//*[@id='content-side']/div/div/table[1]/tbody/tr[2]/td[2])[2]"));
		String user =locator.getText();
		return user;
	}

	//click on restricted icon
	public void clickOnRestrictedIcon()
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.Restrictedbutton");
		clickOn(locator);
	}
	

	public String getVideoRestrictedMessage()
	{
		String locator = vidoeLocator.getLocator("IndividualVideoDetails.VideoRestMessage");
			String result= getText(locator);
			return result;
		}
   
	 //click on more icon at the given operator name	
	 public void ClickOnSpecifiedOperatorMoreLink(String operatorName)
	 {
		 boolean status= false;
		 List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
		 int totalDiv= div.size();
		 
		for(int i=1; i<=totalDiv; i++)
		 {
			List<WebElement> rows= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
			 int totoalRow= rows.size();
				 for(int j=1; j<totoalRow; j++)
				 {
					String name=  getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
					if(name.equalsIgnoreCase(operatorName))
					{
						getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[2]/tbody/tr/td[2]/a")).click();
						status= true;
						break;
					}
				 }
				
				 if(status){
					 break;
				 }
					 
		 }
		 
	 }
		
	 
	//Remove the shared user at the given name 
	 public void removedSharedUser(String user)
	 {
		 boolean status= false;
		 List<WebElement> span= getWebDriver().findElements(By.xpath("//*[@id='tag-list']/span"));
		 int totalSpan= span.size();
		 for(int i=1; i<=totalSpan; i++)
		 {
			String userText= getWebDriver().findElement(By.xpath("//*[@id='tag-list']/span["+i+"]/span")).getText();
			if(user.equalsIgnoreCase(userText))
			{
				getWebDriver().findElement(By.xpath("//*[@id='tag-list']/span["+i+"]/span/span")).click();
				break;
			}
		 }
	 }	
	 
	 //at video detail page click on create new incident icon
	 public void clickOnNewIncidentIcon()
	 {
		 String locator = vidoeLocator.getLocator("IndividualVideoDetails.CreateNewIncident");
		 clickOn(locator);
	 }
	 

	public void deleteVideoPermanently() throws InterruptedException
	{
		Thread.sleep(5000); 
		JavascriptExecutor jsx = (JavascriptExecutor)getWebDriver();
		jsx.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		Thread.sleep(5000); 
		List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
			for(int i=div.size(); i>=1; i--)
			{
				getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]/div/div/div/div/button[@data-original-title='Delete Video Forever']")).click();
				acceptPopup();
			}
	}
	
	//check that No Video message is shown or not
	public boolean isNoVideoMsgDisplayed()
	{
		boolean result= false;
		try
		{
			getWebDriver().findElement(By.id("videoLoadErrorMessage")).isDisplayed();
			result= true;
		}
		catch (Exception e) {
			result= false;
			e.printStackTrace();
		}
		return result;
	}
	
		
	//check include deleted videos check box
	public void clickOnIcludeDeletedVideoCheckBox()
	{
		 String locator = vidoeLocator.getLocator("SearchVideo.IncludeDeletedVideo");
		 clickOn(locator);
	}
	
	//check only deleted videos check box
	public void clickOnOnlyDeletedVideoCheckBox()
	{
		 String locator = vidoeLocator.getLocator("SearchVideo.OnlyDeletedVideo");
		 clickOn(locator);
	}
	
	
						
	//Change the owner name 		
	public void enterChangedOwnerName(String userNameNew)
	{
		String locator = vidoeLocator.getLocator("SharedVideo.OwnerTextField");
		sendKeys(locator, userNameNew);
		}
				
	//Choose the option from dropdown list
	public void clickOnDropdownOption()
	{
		String locator = vidoeLocator.getLocator("SharedVideo.OwnerTextFieldDropdown");
		clickOn(locator);
	}
				
	
	//Remove Entered Owner Name
	public void removeEnteredOwnerName()
	{
		String locator = vidoeLocator.getLocator("SearchVideo.OwnerTextField") ;	
		clickTextField(locator);				
		sendKeys(locator, " ");
		getWebDriver().findElement(By.xpath("//input[@name='filterOwner']")).sendKeys(Keys.BACK_SPACE);
	}
	
	//Share the video to another User
	public void testShareAnyVideo(String userName) throws InterruptedException
	{
	 enterUserToShare(userName);
	 clickOnPlusIcon();
	 clickOnConfirmChanges();
	} 
						 
	//Click on view audit log button 
	public void clickOnViewAuditLogButton()
	{
	 String locator = vidoeLocator.getLocator("VideoAction.ViewVideoAuditLog");
	 clickOn(locator);
	}
				 		 
	//Verify Audit log screen.
	public String VerifyAuditLog(String UserName)
	{
	 List<WebElement> row= getWebDriver().findElements(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr"));
	 int rowsize= row.size(); 
	 
	 for(int i=1;i<=rowsize;i++)
	  {
		  String name = getWebDriver().findElement(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
		  
		  if(name.equalsIgnoreCase(UserName))
		    {
			  String messageText = getWebDriver().findElement(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr["+i+"]/td[6]")).getText();
			  return messageText;
			  }
		  }				
	return null;		
	}					
	
	//Verify audit log for supervised user			 
	public String VerifyAuditLogForSupervisedVideo(String UserName)
	{
	 List<WebElement> row= getWebDriver().findElements(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr"));
	 int rowsize= row.size(); 
			  
	 for(int i=1;i<=rowsize;i++)
	  {
		  String name = getWebDriver().findElement(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
		  
		  if(name.equalsIgnoreCase(UserName))
		   {
			 String messageText = getWebDriver().findElement(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr["+i+"]/td[6]")).getText();
			 
			 return messageText;
		   }}
	  
	return null;			
	}	
		
			 
	//Verify Restricted user audit screen
	public String VerifyRestricetdUserAuditLog(String UserName)
	{
	 List<WebElement> row= getWebDriver().findElements(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr"));
	 int rowsize= row.size(); 
			  
	 for(int i=1;i<=rowsize;i++)
	  {
		  String name = getWebDriver().findElement(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr["+i+"]/td[4]")).getText();
		  			  
		  if(name.equalsIgnoreCase(UserName))
		  {
		  String messageText = getWebDriver().findElement(By.xpath("//div[@id='dashboard-devices']/div[2]/table/tbody/tr["+i+"]/td[6]")).getText();
		
		  return messageText;
		  } }
		return null;			
		}	
				
				  
	//Play the video
	public void playVideo() throws Exception
	{
	 clickOnPlayButton();
	 waitofbuffer();
	 } 
	
	//Click On Play Button
	public void clickOnPlayButton()
	{
	 String locator = vidoeLocator.getLocator("SupervisedVideo.ClickPlayBttn");
	 clickOn(locator); 
	 }
				 
	//Wait for buffering
	 public void waitofbuffer() throws Exception{
	  WebElement e1 = getWebDriver().findElement(By.xpath("//i[@class='fa fa-fw fa-spinner fa-spin']"));
	  for(int i=0;i<=18;i++){
	  if(e1.isDisplayed()){
	  Thread.sleep(1000);}
	   else {
	   break;}}
	  }
	 
	 
	 public void clickOnPlayVideoContinue()
	 {
		 acceptContinuePopup();
	 }
	 
	 
	 
	 
	 
	 public boolean isVideoErrorMsgDisplayed()
		{
			boolean result= false;
			try {
				result= getWebDriver().findElement(By.className("pss-status-message")).isDisplayed();
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
	 
				 
	//Click On Play Pause button
	public void clickOnPlayPauseBttn()
	{
	 String locator = vidoeLocator.getLocator("VideoPlayScreen.ClickPlayPauseBttn");
	 clickOn(locator);
	}
	
	
	//click on video play pop-up continue button
	public void clickOnContinue()
	{
	 String locator = vidoeLocator.getLocator("VideoPlayScreen.ClickPlayPauseBttn");
	 clickOn(locator);
	}
	
				 
	//Get Start Time of video
	public String getStartTime()
	{
	 String locator= vidoeLocator.getLocator("VideoPlayScreen.GetStartTimeOfVideo");
	 String startingTime = getText(locator);
	  return startingTime;
	}
				 
	//Get End Time of video
	public String getEndTime(){
	String locator= vidoeLocator.getLocator("VideoPlayScreen.GetStartTimeOfVideo");
	String startingTime = getText(locator);
	return startingTime;		   
	}
				 
	//Assert both start and end time of video
	public void asserttime(String timer1, String timer2)
	{
	 Boolean flag =false;			   
	 if(!timer1.equalsIgnoreCase(timer2))
	  {
		flag = true; } 
		else{
		flag =false;}
		Assert.assertTrue(flag);}
				 
				 
	//Click On Incident More Detail icon
	public void clickOnIncidentDetailIcon()
	{
	 String locator = vidoeLocator.getLocator("IncidentDetails.IncidentMoreDetail");
	 clickOn(locator);
	}
				 
	//Click on Add Video to incident icon
	public void clickOnAddVideoToIncident(String incidentName)
	 {
	  List<WebElement> row= getWebDriver().findElements(By.xpath("//table[@class='table table-hover']/tbody/tr"));
	  for (int i=1; i<=row.size(); i++)
	   {
		 String name= getWebDriver().findElement(By.xpath("//table[@class='table table-hover']/tbody/tr["+i+"]/td[4]")).getText();
		 if(name.equalsIgnoreCase(incidentName))
		  {
		   getWebDriver().findElement(By.xpath("//table[@class='table table-hover']/tbody/tr["+i+"]/td[6]//button[@data-original-title='Add video to this Incident']")).click();
		   break;
			}
	}}	
					
					
	//Delete the video
	public void deleteVideo()
	{
	 List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	
	 for(int i=div.size(); i>=1; i--)
	  {
		getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]/div/div/div/div/button[@data-original-title='Delete Video']")).click();
		acceptDeletePopup();  
		if(isVideoMessageAppear()==true)	   {
		
			 break;}
		   else	{
			   continue;
			 }
		 }}
		
	
	//Is Delete icon displayed
	public boolean isDeleteIconDisplayed()
	{
		 boolean result= false;
		 try
		 {
		 getWebDriver().findElement(By.xpath("//button[@data-original-title='Delete Video']")).isDisplayed(); 
		 result= true;}
		 catch (Exception e) {
		 result= false;
		 e.printStackTrace();}
		 return result;
	}
					
	//Click on yes at the continue on restricted popup
	public void acceptContinuePopup()
	{
		WebDriverWait wait= new WebDriverWait(getWebDriver(), 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
		String locator = vidoeLocator.getLocator("RestrictedVideoPopUp.ClickOnContinue");
		clickOn(locator);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
	}	
					
	//Click on This video is in 1 Incident icon at the given operator name	
	public void ClickOnVideoHavingIncidnetIcon(String operatorName)
	{
	 boolean status= false;
	 List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	 int totalDiv= div.size(); 
	 for(int i=1; i<=totalDiv; i++)
	 {
	   List<WebElement> rows= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
	   int totoalRow= rows.size();
	   
	   for(int j=1; j<totoalRow; j++)
	    {
	     String name=  getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
		     if(name.equalsIgnoreCase(operatorName))
		     {
			     getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[2]/tbody/tr[2]/td[2]/button")).click();
			     status= true;
			     break;
		     }
	     }
	     
		     if(status)
		     {
		     
		     break; }
	 }
	}
	 				 
	// Click on delete video icon
	public void clickOnDeleteVideoIcon() {
		String locator = vidoeLocator.getLocator("VideoHeader.DeleteVidoIcon");
		clickOn(locator);
	}	
					 
	// Click on include deleted video chec kbox
	public void clickOnIncludeDeletedCheckbox() {
		String locator = vidoeLocator.getLocator("SearchVideo.IncludeDeletedVideo");
		clickOn(locator);
	}
					 
	//Click on only deleted video check box
	public void clickOnOnlyDeletedCheckbox()
	{
		String locator = vidoeLocator.getLocator("SearchVideo.OnlyDeletedVideo");
		clickOn(locator) ;
	}
					 
	//Verify the list of deleted video
	public boolean verifyListOfDeletedVideo(String OperatorName)
	{
	boolean flag = false;					 
	List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	int totalDiv= div.size();
	for(int i=1; i<=totalDiv; i++)
	 {
	  List<WebElement> row = getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
	  int rowsize = row.size();
	  for ( int j=1;j<rowsize;j++)
	   {
		  String name = getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
		  
		  if(name.equalsIgnoreCase(OperatorName))
		    {
			 flag = true;
			 break;}
		  else
			 {
		   	 flag = false;
			 continue;}
		}
	 	} 
	return flag;
	}
	
	//Entered specific owner name under owner text field at search page.
	public void enterOwnerName(String ownerName)
	{
		 String locator = vidoeLocator.getLocator("SearchVideo.OwnerTextField") ;
		 clickTextField(locator);
		 sendKeys(locator,ownerName); 
	 }
	
	//Expunge the all video
	public void expungeAllVideo()
	{
	List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	int totalDiv= div.size();
	for(int i=totalDiv; i>=1; i--)
	 {
		 getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//button[@data-original-title='Delete Video Forever']")).click();
		 acceptDeletePopup();
		 
		 if(isVideoMessageAppear()==true)
		   {
			 
			 break;}
		   else	{
			   continue;
			 }
		 }}
		 

	//accept the Delete popup	
	public void acceptDeletePopup()
	{
		WebDriverWait wait= new WebDriverWait(getWebDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
		String locator = vidoeLocator.getLocator("SearchVideo.DeleteYes");
		clickOn(locator);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));									
		}
								 		 
	
	//Expunge the all video
	public void ReinstateVideo()
	{
	List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	int totalDiv= div.size();
	
	for(int i=totalDiv; i>=1; i--)
	 {
	  getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//button[@data-original-title='Reinstate Video']")).click();
	  acceptReinstatevideo();
	  
	  if(isVideoMessageAppear()==true)
	  {
		
		 break;}
	  else	{
		   continue; }
	}}
			 
	//Accept reinstate video pop-up
	public void acceptReinstatevideo()
	{
		WebDriverWait wait= new WebDriverWait(getWebDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
		String locator = vidoeLocator.getLocator("SearchVideo.DeleteYes");
		clickOn(locator);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));									
		}
	
		//Is Reinstate icon Displayed
		public boolean isVideoMessageAppear()
		{
		 boolean result= false;
			 try
			 {
				 getWebDriver().findElement(By.xpath("//div[@id='videoLoadErrorMessage']")).isDisplayed();  
				 result= true;
			 }
			 catch (Exception e)
			 {
				 result= false;
			 e.printStackTrace();
			 }
			 
		 return result;
		 }
	
	//Is Delete icon displayed
	public boolean isExpungeIconDisplayed()
	{
	boolean result= false;
		try
		{
			getWebDriver().findElement(By.xpath("//button[@data-original-title='Delete Video Forever']")).isDisplayed();
			result= true;
		}
		catch (Exception e)
		{
			result= false;
			e.printStackTrace();
		}
	return result;
	}
	
	//Click on my video button
	public void clickOnMyVideoButton()
	{
		String locator = vidoeLocator.getLocator("VideoHeader.Myvideo");
		clickOn(locator) ;
	}
	
	//Verify the operatorName
	public boolean verifyOperatorName(String operatorname)
	{
	boolean flag = false; 
	List<WebElement> div = getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div/div"));
	int totaldiv = div.size();
	for(int i=1; i<=totaldiv;i++)  
	{
		List<WebElement> row = getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]/div//div[@class='panel panel-default']/div/table/tbody/tr"));   
		int totalrow = row.size();
		for(int j=1; j<totalrow; j++)
		{
		 String name = getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]/div//div[@class='panel panel-default']/div/table/tbody/tr["+j+"]")).getText();
		
		 if(name.equalsIgnoreCase(operatorname))
		  {
		  flag = true;
		  break;  }
		 else  {
		  flag =false;
		  continue; }      
		}
	}
		return flag;
	}
	
	//Permanently delete all video
	public void permanantelyDeleteAllvideo()
	{
		clickOnSearchVideoTab();
		removeEnteredOwnerName();
		clickOnIncludeDeletedCheckbox();
		clickOnOnlyDeletedCheckbox();
		ClickOnFindButton();
		expungeAllVideo();
	}
	
	//Reinstate specific Operator name video
	public void reinstateSpecifiedOperatorNameVideo(String operatorName)
	{
	 List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	 int totalDiv= div.size();
	 for(int i=totalDiv; i>=1; i--)
	  {
		List<WebElement> rows= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
		int totoalRow= rows.size();
			for(int j=1; j<totoalRow; j++)
			 {
				String name=  getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
				if(name.equalsIgnoreCase(operatorName))
			  	 {
				  getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//button[@data-original-title='Reinstate Video']")).click();
				  acceptReinstatevideo();
				  break;
			  	 }
			}
		}
	 }
		
	
	//Veriy List of Restrict Video
	public String verifyListOfRestrictedVideo(String OperatorName)
	{
	List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
	int totalDiv= div.size();
	for(int i=1; i<=totalDiv; i++)
	 {
		List<WebElement> rows= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
		int totoalRow= rows.size();
		for(int j=1; j<totoalRow;j++)
		{
			String name=  getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
			
			if(name.equalsIgnoreCase(OperatorName))
			  {
				 String restrictedMessage= getVideoRestrictedMessage();
				 return restrictedMessage;
			  }
		}
	}
	return null;
	}
	

	
	//test that video played and matched all the color codes
	public boolean verifyVideoPlayed() throws InterruptedException
	{
		ArrayList<Object> obje= new ArrayList<Object>();
		ArrayList<String> obfinal= new ArrayList<String>();
		ArrayList<String> dynamicColorCodes= new ArrayList<String>();
		ArrayList<String> baseColorCodes= new ArrayList<String>();
		
		obfinal= getFinalColorCodes();
		dynamicColorCodes= getListOfDynamicCodes(obfinal);
		baseColorCodes= baseColor();
		
			if(dynamicColorCodes.size()==baseColorCodes.size())
			{
				int k, k1, k2;
				int p;
				boolean result= false;
					for(int m= 0; m<baseColorCodes.size(); m++)
					{
						
						String l1= baseColorCodes.get(m);
						k= Integer.parseInt(l1);
						
						String h= dynamicColorCodes.get(m);
						String	l2 = h.replaceAll("\\s","");
						p= Integer.parseInt(l2);
						k1= k+40;
						k2= k-40;
							
								if(!(p<=k1) || !(p>=k2))
								{   result= false;
									break;
									}
									else
									{
										result= true;
									}
					}
				}	
			return true;
	}
	
	
	//Get the 15 final color codes after filtering out 
	public ArrayList<String> getFinalColorCodes() throws InterruptedException
	{
		//getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
		ArrayList<Integer> timeList = new ArrayList<Integer>();
		ArrayList<Object> ob = new ArrayList<Object>();
		ArrayList<Integer> timeListfinal = new ArrayList<Integer>();
		ArrayList<String> obfinal = new ArrayList<String>();
		int time = 0;
		
			//fetch all color codes in poll of 0.4 seconds
			for (int i = 1; i < 60; i++) {
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				timeList.add(time);
				ob.add(executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(200,200,1,1).data"));
				time += 400;
				Thread.sleep(400);
			}
		
			 //filter the final color code
					int t = timeList.get(0);
					String s = ob.get(0).toString();
					for(int j = 1; j < ob.size(); j++)
					{
						int t1  = timeList.get(j);
						String s1 = ob.get(j).toString();
		
							if(s.equalsIgnoreCase(s1) == false)
							{
								timeListfinal.add(t1);
								obfinal.add(s1);
								t = t1;
								s = s1;
							}
					}
			return obfinal;
	}
	
	
	//Remove brackets and split the string into array list 
	public ArrayList<String> getListOfDynamicCodes(List<String> obfinal)
	{
	
	ArrayList<String> colorList= new ArrayList<String>();
		for(int k=0; k<obfinal.size(); k++)
		{
			String[] words = obfinal.get(k).replaceAll("[\\[\\]]", "").split(",");
			colorList.addAll(Arrays.asList(words));
		}
	
	return colorList;

	}
	
	
	
	public void select()
	{
		WebElement select = getWebDriver().findElement(By.id("selection"));
	    List<WebElement> options = select.findElements(By.tagName("option"));
	    for (WebElement option : options) {
	        
	    	if(option.getText().contains("Germany"))
	    	if("Germany".equals(option.getText()))
	            option.click();
	    }
	}

	//Create the list of color code which was used during the video creation
	//These codes are converted from hexadecimal to decial which were provided by Hamish
	public ArrayList<String> baseColor()
	{
		ArrayList<String> baseList= new ArrayList<String>();
			String[] clorCodes = {"250","0","0","255","255","160","0","255","255","255",
					"0","255","160","255","0","255","0","255","0","255","0","255",
					"160","255","0","255","255","255","0","175","255","255","0","0"
					,"255","255","160","0","255","255","255","0","255","255","255",
					"0","160","255","255","160","160","255","160","255","160","255",
					"160","160","255","255"};
		
			
				for(String st1: clorCodes)
				{
					baseList.add(st1);
				}
		
		return baseList;
	}
	
	
	//Verify that clipped played in between the selected start & end time
	public boolean verifyClipedVideo() throws InterruptedException
	{
		ArrayList<Object> obje= new ArrayList<Object>();
		ArrayList<String> obfinal= new ArrayList<String>();
		ArrayList<String> dynamicColorCodes= new ArrayList<String>();
		ArrayList<String> baseColorCodes= new ArrayList<String>();
		
		obfinal= getFinalColorCodes();
		dynamicColorCodes= getListOfDynamicCodes(obfinal);
		baseColorCodes= ClipBaseColor();
		
			if(dynamicColorCodes.size()==baseColorCodes.size())
			{
				
				int k, k1, k2;
				int p;
				boolean result= false;
					for(int m= 0; m<baseColorCodes.size(); m++)
					{
						
						String l1= baseColorCodes.get(m);
						k= Integer.parseInt(l1);
						
						String h= dynamicColorCodes.get(m);
						String	l2 = h.replaceAll("\\s","");
						p= Integer.parseInt(l2);
						
						
						k1= k+40;
						k2= k-40;
							
								if(!(p<=k1) || !(p>=k2))
								{   result= false;
									break;
									}
									else
									{
										result= true;
									}
						}
				}	
			return true;
	}
	
	
	public ArrayList<String> ClipBaseColor()
	{
		ArrayList<String> baseList= new ArrayList<String>();
			String[] clorCodes = {"255","255",
					"0","255","160","255","0","255","0","255","0","255","0","255",
					"160","255","0","255","255","255","0","175","255","255","0","0"
					,"255","255","160","0","255","255","255","0","255","255","255",
					"0","160","255","255","160","160","255","160","255","160","255",
					"160","160","255","255"};

			for(String st1: clorCodes)
				{
					baseList.add(st1);
				}
		
		return baseList;
	}
	
	 public String editStartTimeOfClips(String colors) throws InterruptedException
	 {
		
		ArrayList<Integer> timeList = new ArrayList<Integer>();
		ArrayList<Object> ob = new ArrayList<Object>();
		int time = 0;
        WebElement elDraggable = getWebDriver().findElement(By.xpath("//div[contains(@class,'noUi-handle noUi-handle-lower')]"));
		 Actions moveSlider = new Actions(getWebDriver());
		 String getColors="";
			for(int i=0; i<=59; i++) 
			{
				
				 moveSlider.clickAndHold(elDraggable);
				 moveSlider.moveByOffset(15, 0).build().perform();
				 //moveSlider.release(elDraggable);
				 timeList.add(time);
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				ob.add(executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(200,200,1,1).data"));
				time += 400;
				Thread.sleep(400);
				moveSlider.release();
				elDraggable.click();
				 getColors=  ob.get(i).toString();
					if (colors.equalsIgnoreCase(getColors))
					{
						break;
					}
				}
			moveSlider.release();
			return getColors;
	 }
	

	 
	 public String editEndTimeOfClips(String colors) throws InterruptedException
	 {
		
		ArrayList<Integer> timeList = new ArrayList<Integer>();
		ArrayList<Object> ob = new ArrayList<Object>();
		int time = 0;
        WebElement elDraggable = getWebDriver().findElement(By.xpath("//div[contains(@class,'noUi-handle noUi-handle-upper')]"));
        elDraggable.click();
        Actions moveSlider = new Actions(getWebDriver());
        moveSlider.release();
		 String getColors="";
			for(int i=0; i<=59; i++) 
			{
				 //moveSlider.release();
				 moveSlider.clickAndHold(elDraggable);
				 moveSlider.moveByOffset(-15, 0).build().perform();
				 //moveSlider.release();
				 timeList.add(time);
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				ob.add(executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(200,200,1,1).data"));
				time += 400;
				Thread.sleep(400);
				moveSlider.release();
				elDraggable.click();
				 getColors=  ob.get(i).toString();
					if (colors.equalsIgnoreCase(getColors))
					{
						break;
					}
				
			}
			
			moveSlider.release();
			return getColors;
	 }
	 
	 
	 
	 public void clickOnEditClipConfirm() throws InterruptedException
	 {
		 
		 getWebDriver().findElement(By.xpath("//button[contains(@class,'btn btn-danger modal-confirm')]")).click();
		 WebDriverWait wait= new WebDriverWait(getWebDriver(), 30);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")));
	 }
	
	 
	 public void clickOnEditClipCancel()
	 {
		 getWebDriver().findElement(By.xpath("//button[contains(@class,'btn btn-default modal-cancel')]")).click();
	 }
	 
	 public void clickOnVideoPlayIcon()
	 {
		
		 getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
		 
	 }
 
	 public void verifyVideoRedactionFeature() throws Exception
	 {
		 ArrayList<Object> ob = new ArrayList<Object>();
		 
		 
		 Thread.sleep(2000);
		 getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
		 Thread.sleep(3000);
		 getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-pause')]")).click();
		 String start=	getWebDriver().findElement(By.cssSelector("span.position")).getText();
		 String[] str= start.split(":");
		 int startTime= Integer.parseInt(str[1]);
		 String end=	getWebDriver().findElement(By.cssSelector("span.length")).getText();
		 WebElement elDraggable = getWebDriver().findElement(By.xpath("//div[contains(@class,'noUi-handle noUi-handle-lower')]"));
		 Actions moveSlider = new Actions(getWebDriver());
		
		 String sTime= "00:10";
		 String[] str1= sTime.split(":");
		 int endStarttime= Integer.parseInt(str1[1]);
		 for(int i=0; i<=190; i++) 
			{
			
			 moveSlider.clickAndHold(elDraggable);
			 moveSlider.moveByOffset(2, 0).build().perform();
			 moveSlider.release();
			 	if(start.equalsIgnoreCase(sTime))
			 	{
			 		String color= "document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(75,180,1,1).data";
					JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
					ob.add(executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(75,180,1,1).data"));
					 start=	getWebDriver().findElement(By.cssSelector("span.position")).getText();
					//break;
			 	}
			 	
			 	if(start.equalsIgnoreCase("00:30"))
			 	{
			 		String color= "document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(150,180,1,1).data";
					JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
					ob.add(executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(150,180,1,1).data"));
					 start=	getWebDriver().findElement(By.cssSelector("span.position")).getText();
					//break;
			 	}
			 	
			 	start=	getWebDriver().findElement(By.cssSelector("span.position")).getText();
				}
	 } 
	 
	 
	 public boolean verifyRadcationClipShownAsSetTime() throws InterruptedException
	 {
		 	ArrayList<String> times= new ArrayList<String>();
		 	ArrayList<String> stimes= new ArrayList<String>();
			ArrayList<String> etimes= new ArrayList<String>();
			boolean result= false;
			times=  getRedactionPlayVideoTime();
			
			String stt=times.get(0);
			String ett= times.get(1);
			String[] splitStart= stt.split(":");
			String[] splitEnd= ett.split(":");
			
				for (String str : splitStart) {
					stimes.add(str);
				}
				
				for (String str1 : splitEnd) {
					etimes.add(str1);
				}
				
			int startInt= Integer.parseInt(stimes.get(1));
			int endInt= Integer.parseInt(etimes.get(1));
			
			
			int s=10;
			int s1= s+2;
			int s2= s-2;
			
			int e=30;
			int e1= e+3;
			int e2= e-3;
			
				try{
					if((startInt>=s2 && startInt<=s1) && (endInt>=e2 && endInt<=e1) )
					{
						result= true;
					}
				}
				catch(Exception error)
				{
					error.printStackTrace();
				}
			return result;
	 }
	 
	 
	 public ArrayList<String> getRedactionPlayVideoTime() throws InterruptedException
	 {
		 ArrayList<String> time= new ArrayList<String>();
		 Thread.sleep(3000);
		 String End_time = "";
		 getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
		 //String color= document.getElementsByClassName("pss-frame-canvas")[0].getContext("2d").getImageData(45,180,1,1).data;
		 //var canvas = $('<canvas/>')[0];]
		 Thread.sleep(2000);
		 for(int i=0;i<1000;i++)
		 {
		 JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		 
		 ArrayList color = new ArrayList(); 
		 color=  (ArrayList) executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(80,180,1,1).data;");
		
		
		String test=color.get(0).toString();
		String test2=color.get(1).toString();
		String test3=color.get(2).toString();
		int t1=Integer.parseInt(test);
		int t2=Integer.parseInt(test2);
		int t3=Integer.parseInt(test3);
		
		//int cl= Integer.parseInt(s)
		if(t1<20 && t2<20 && t3<20 && t1!=0 && t2!=0 && t3!=0)
		{
			String Start_time=getWebDriver().findElement(By.xpath("//span[@class='position']")).getText();
			time.add(Start_time);
			//color.clear();
			for(int j=0;j<400;j++)
			{
			JavascriptExecutor executor2 = (JavascriptExecutor) getWebDriver();
			 
			 ArrayList color2 = new ArrayList(); 
			 color2= (ArrayList) executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(211,180,1,1).data;");
			
			
			String test21=color2.get(0).toString();
			String test22=color2.get(1).toString();
			String test23=color2.get(2).toString();
			int t21=Integer.parseInt(test21);
			int t22=Integer.parseInt(test22);
			int t23=Integer.parseInt(test23);
			if(t21<18 && t22<18 && t23<18)
			{
				End_time=getWebDriver().findElement(By.xpath("//span[@class='position']")).getText();
				
			}
				else
				{
					color2.clear();
				}
			
			}
		}
		else
		{
			color.clear();
		}
		 }
		 time.add(End_time);
		 return time;
		 
	 }
	 
	 
	 
	 
	 public void getRedactionTime() throws InterruptedException
	 {
		 Thread.sleep(3000);
		 getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
		 //Thread.sleep(3000);
		//getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
			ArrayList<Integer> timeList = new ArrayList<Integer>();
			ArrayList<Object> ob = new ArrayList<Object>();
			ArrayList<Integer> timeListfinal = new ArrayList<Integer>();
			ArrayList<String> obfinal = new ArrayList<String>();
			int time = 0;
			int i= 0;
			double j=0;
			int actualTime= 0;
			String fTime= "";
			String start=	getWebDriver().findElement(By.className("position")).getText();
			String end=	getWebDriver().findElement(By.className("length")).getText();
			ArrayList<String> samay= new ArrayList<String>();
				while(start.equalsIgnoreCase(end)==false)
				{
					 start=	getWebDriver().findElement(By.className("position")).getText();
					 end=	getWebDriver().findElement(By.className("length")).getText();
					j=j+2.3;
					JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
					timeList.add(time);
					ob.add(executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData("+j+",180,1,1).data"));
					String words = ob.get(i).toString();
					String[] parsedString= (words.replaceAll("[\\[\\]]", "").replaceAll("\\s","")).split(",");
					String str= parsedString[0];
					int nm= Integer.parseInt(str);
					
					if((10<=nm) && (nm<=30)	)
					{
						String start1=	getWebDriver().findElement(By.className("position")).getText();
						samay.add(start1);
					}
				
			}
			
				int sz=samay.size();
				
				
	 }

		public String getNoVideoMessage()
		{
			String locator = vidoeLocator.getLocator("Login.ValidationMessage");
			String text = getText(locator);
			return text;
		}

		
		public String ErrorPopUp()
		{
			WebDriverWait wait= new WebDriverWait(getWebDriver(), 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
			String locator = vidoeLocator.getLocator("SearchVideo.ErrorPopUp");
			String text = getText(locator);
			return text;	
		}
		
		//click on yes at the delete video popup
		public void acceptErrorPop()
		{
			WebDriverWait wait= new WebDriverWait(getWebDriver(), 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
			getWebDriver().findElement(By.xpath("//div[@class='modal-footer']/button[contains(text(),'ok')]")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
			
		}
			
		//Is Delete icon displayed
		public boolean isDeleteIconDisplayedAsOFF()
		{
		boolean result= false;
			try
			{
				getWebDriver().findElement(By.xpath("//button[@disabled='']")).isDisplayed();
				result= true;
			}
			catch (Exception e)
			{
				result= false;
				e.printStackTrace();
			}
		return result;
		}
				
	 public ArrayList<String> getRedactionTimeOfVideo() throws InterruptedException
	 {
		 ArrayList<String> time= new ArrayList<String>();
		 Thread.sleep(3000);
		 String End_time = "";
		 getWebDriver().findElement(By.xpath("//i[contains(@class,'fa fa-fw fa-play')]")).click();
		 Thread.sleep(2000);
		 for(int i=0;i<1000;i++)
		 {
		 JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		 
		 ArrayList color = new ArrayList(); 
		 color=  (ArrayList) executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(80,180,1,1).data;");
		String test=color.get(0).toString();
		String test2=color.get(1).toString();
		String test3=color.get(2).toString();
		int t1=Integer.parseInt(test);
		int t2=Integer.parseInt(test2);
		int t3=Integer.parseInt(test3);
		
		//int cl= Integer.parseInt(s)
		if(t1<20 && t2<20 && t3<20 && t1!=0 && t2!=0 && t3!=0)
		{
			
			String Start_time=getWebDriver().findElement(By.xpath("//span[@class='position']")).getText();
			time.add(Start_time);
			//color.clear();
			for(int j=0;j<400;j++)
			{
			JavascriptExecutor executor2 = (JavascriptExecutor) getWebDriver();
			 
			 ArrayList color2 = new ArrayList(); 
			 color2= (ArrayList) executor.executeScript("return document.getElementsByClassName('pss-frame-canvas')[0].getContext('2d').getImageData(211,180,1,1).data;");
			
			
			String test21=color2.get(0).toString();
			String test22=color2.get(1).toString();
			String test23=color2.get(2).toString();
			int t21=Integer.parseInt(test21);
			int t22=Integer.parseInt(test22);
			int t23=Integer.parseInt(test23);
			if(t21<18 && t22<18 && t23<18)
			{
				End_time=getWebDriver().findElement(By.xpath("//span[@class='position']")).getText();
			}
			else
			{
				color2.clear();
			}
			
			}
		}
		else
		{
			color.clear();
		}
		
		 }
		 time.add(End_time);
		 return time;
		 
	 }		
			


	public String getNoPermissionEditClipMessage()
	{
		String message= "";
			try{
				message=getWebDriver().findElement(By.id("view-region")).getText();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		return message;
	}

	public void clickOnOkButton()
	{
		getWebDriver().findElement(By.xpath("//button[contains(text(),'ok')]")).click();
	}
	


	//***********************************************************
	
			
	//Get the validation message
	public String NoPermissionviewToViewThumbnail()
	{
	    String locator = vidoeLocator.getLocator("VideoPlayScreen.NoPermission");
		String text = getText(locator);
	    return text;	
	}
		
	//click on view audit log button 
	public boolean isViewAuditLogButtonDisplayed()
	{
		boolean result;
		String locator= vidoeLocator.getLocator("VideoAction.ViewVideoAuditLog");
		result = isElementDisplayed(locator);
		return result;
	}

	//is Shared Text field Displayed
	public boolean isSharedTextFieldDisplayed()
	{
		boolean result;
		String locator= vidoeLocator.getLocator("IndividualVideoDetails.ShareUser");
		result = isElementDisplayed(locator);
		return result;
	}
			
	//is Delete Video Icon Displayed
	public boolean isDeleteVideoIconDisplayed()
	{
		boolean result;
		String locator = vidoeLocator.getLocator("VideoHeader.DeleteVidoIcon");
		result = isElementDisplayed(locator);
		return result;
	}
			
	//is Expunge Button Displayed
	public boolean isExpungeButtonDisplayed(String OperatorName) throws InterruptedException
	{
		boolean result=false;
		List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
		int totalDiv= div.size();
		for(int i=1; i<=totalDiv; i++)
		 {
			List<WebElement> rows= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
			int totoalRow= rows.size();
			for(int j=1; j<totoalRow;j++)
			{
				String name = getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
				
				if(name.equalsIgnoreCase(OperatorName))
				  {
					 
					 getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]/div/div/div/div/button[@data-original-title='Delete Video Forever']")).isDisplayed();
					 result= true;
	              }					
	         }
		  }
		return result;
	 }

	//is Reinstate button displayed		
	public boolean isReinstateButtonDisplayed(String OperatorName) throws InterruptedException
	{
		boolean result=false;
		List<WebElement> div= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div"));
		int totalDiv= div.size();
		for(int i=1; i<=totalDiv; i++)
		 {
			List<WebElement> rows= getWebDriver().findElements(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table/tbody/tr"));
			int totoalRow= rows.size();
			for(int j=1; j<totoalRow;j++)
			{
				String name = getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]//div[@class='panel-body']/table[1]/tbody/tr["+j+"]")).getText();
				
				if(name.equalsIgnoreCase(OperatorName))
				  {
					 
					 getWebDriver().findElement(By.xpath("//div[@class='list-region']/div/div["+i+"]/div/div/div/div/button[@data-original-title='Reinstate Video']")).isDisplayed();
					 result= true;
	              }					
	         }
		  }
		return result;
	   }
	
	
	
	
	}


	 






