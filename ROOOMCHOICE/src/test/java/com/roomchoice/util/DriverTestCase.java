package com.roomchoice.util;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//import com.appcentral.web.pages.BasePage;
import org.testng.Assert;

import com.roomchoice.util.PropertyReader;
import com.opera.core.systems.internal.input.KeyEvent;
import com.opera.core.systems.scope.protos.ExecProtos.ActionList.Action;
import com.roomchoice.locator.LocatorReader;
import com.roomchoice.pagehelper.LoginHelper;
import com.roomchoice.pagehelper.VideoHelper;




import java.io.*;
import java.net.URISyntaxException;

import javax.ws.rs.WebApplicationException;


public abstract class DriverTestCase {
	
	public LoginHelper loginHelper;
	public VideoHelper videoHelper;
	public PropertyReader propertyReader;

	private WebDriver driver;
	private String storeRan;
	
	enum DriverType {
		Firefox, IE, Chrome
	}

	
	@BeforeSuite
	public void setUp() {		
		
		 propertyReader = new PropertyReader();
		
		String driverType = propertyReader.readApplicationFile("BROWSER");		
							
		if (DriverType.Firefox.toString().equals(driverType)) {							
				driver = new FirefoxDriver();				
			
		} else if (DriverType.IE.toString().equals(driverType)) {
			driver = new InternetExplorerDriver();
			
		} else if (DriverType.Chrome.toString().equals(driverType)) {
			driver = new ChromeDriver();
			
		} else {
			driver = new FirefoxDriver();			
		}			
		
		//maxmize window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//open url
		openURL();
		//driver.manage().deleteAllCookies();

		loginHelper = new LoginHelper(driver);
        videoHelper= new VideoHelper(driver);
		
		 
	}
	
	
	@AfterSuite
	public void afterMainMethod() {	
		 
		 loginHelper = null;
		 videoHelper= null;
		 driver.quit();
	}
	
	public WebDriver getWebDriver(){
		
		return driver;
	}
	
	
	
	public PropertyReader getProperty()
	{
		return propertyReader;
	}
	
	
	public String switchPreviewWindow()
	{
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();		
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}
	
	public void openURL(){
		//open admin control panel application URL 
		PropertyReader propertyReader = new PropertyReader();	
		String applicationUrl = propertyReader.readApplicationFile("WebURL");
		//getWebDriver().get(applicationUrl+"/en/ControlPanel.html");
		
		System.out.println("my room shoice base URL:" +applicationUrl);
		//getWebDriver().get(applicationUrl + "/app/login");
		getWebDriver().get(applicationUrl);
		//getWebDriver().get("javascript:localStorage.clear()");
		//getWebDriver().get("javascript:localStorage.clear();");
		//JavascriptExecutor jsx = (JavascriptExecutor)getWebDriver();
		//jsx.executeScript("window.localStorage.clear();");
		//getWebDriver().get(applicationUrl);
		
		
	}
	
	
	
	public int getRandomInteger(int aStart, int aEnd, Random aRandom){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	}
	

	public String randomString( int len ) 
	{
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

	public String getPath()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	}
	
	
	public void importVideo() throws InterruptedException
	{
		driver.findElement(By.linkText("Advanced")).click();
		driver.findElement(By.linkText("Import")).click();
		driver.findElement(By.linkText("Import")).click();
		
		String filePath = System.getProperty("user.dir") + File.separator+ "videos" + File.separator + "testVideo.mp4";
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        uploadFile.sendKeys(filePath);
		driver.findElement(By.xpath("//button[contains(text(),'Start Import')]")).click();
		WebDriverWait ajaWait= new WebDriverWait(driver, 30);
    	ajaWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[contains(text(),'Start Import')]")));
	}
	
	public void importVideoFile(int len) throws IOException
	{
	   //clear the folder
		clearfolder();
	
		//copy file from source the destination
		String path =  getPath();
		File source = new File(path + "//videos//testVideo.mp4");
		File dest= new File(path+"//Data//360logica.mp4");
		copyFileUsingApacheCommonsIO(source, dest);
		
		//change the file name randomly
        String ran= randomString(len);
		File destNew= new File(path+"//Data//"+ran+".mp4");
		dest.renameTo(destNew);
		
		String filePath = System.getProperty("user.dir") + File.separator+ "Data" + File.separator +ran+".mp4";
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        uploadFile.sendKeys(filePath);
	
		driver.findElement(By.xpath("//button[contains(text(),'Start Import')]")).click();
	}

//Delete the files and sub folder at the specified folder location
	public void clearfolder()
	{
		String path =  getPath();
	    File directory= new File(path+"//Data");
	  	for(File f: directory.listFiles()) 
	  	  f.delete(); 
	}

	public void clearSpecialVideo()
	{
		String path =  getPath();
	    File directory= new File(path+"//SpecialVideos");
	  	for(File f: directory.listFiles()) 
	  	  f.delete(); 
	}

	
	
	private static void copyFileUsingApacheCommonsIO(File source, File dest)
			throws IOException {

		FileUtils.copyFile(source, dest);

	}
	
	
	
	public void uploadColorVideo(int len) throws IOException, InterruptedException
	{
		
		//clear the folder
		clearSpecialVideo();
	
		//copy file from source the destination
		String path =  getPath();
		File source = new File(path + "//videos//colourchange.mp4");
		File dest= new File(path+"//SpecialVideos//360logica.mp4");
		copyFileUsingApacheCommonsIO(source, dest);
		
		//change the file name randomly
        String ran= randomString(len);
		File destNew= new File(path+"//SpecialVideos//"+ran+".mp4");
		dest.renameTo(destNew);
		
		String filePath = System.getProperty("user.dir") + File.separator+ "SpecialVideos" + File.separator +ran+".mp4";
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        uploadFile.sendKeys(filePath);
	
		driver.findElement(By.xpath("//button[contains(text(),'Start Import')]")).click();
		
		
		
	}
	
	
	public void uploadColorMeshVideo(int len) throws IOException, InterruptedException
	{
		//clear the folder
				clearSpecialVideo();
			
				//copy file from source the destination
				String path =  getPath();
				File source = new File(path + "//videos//colourmesh.mp4");
				File dest= new File(path+"//SpecialVideos//360logica.mp4");
				copyFileUsingApacheCommonsIO(source, dest);
				
				//change the file name randomly
		        String ran= randomString(len);
				File destNew= new File(path+"//SpecialVideos//"+ran+".mp4");
				dest.renameTo(destNew);
				
				String filePath = System.getProperty("user.dir") + File.separator+ "SpecialVideos" + File.separator +ran+".mp4";
				WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		        uploadFile.sendKeys(filePath);
			
				driver.findElement(By.xpath("//button[contains(text(),'Start Import')]")).click();
		
	}
	
	
	
	

}
