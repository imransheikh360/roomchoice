package com.roomchoice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public abstract class DriverHelper {
	
	private WebDriver driver;
	

	public DriverHelper(WebDriver webdriver) {
		driver = webdriver;		
	}
	
	public WebDriver getWebDriver(){
		return driver;
	}
	
	
	public void Log(String logMsg){
		System.out.println(logMsg);
	}

	public By ByLocator(String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.name(locator.replace("#", ""));
			
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		}
			
		  else if(locator.startsWith("class=")) {
				result=By.className(locator.replace("class=", ""));
			}
		  else if(locator.startsWith("name=")) {
				result=By.name(locator.replace("name=", ""));
			}
		  
		  else if(locator.startsWith("id=")) {
			  result=By.id(locator.replace("id=", ""));
		  }
		  else if(locator.startsWith("(")) {
			  result = By.xpath(locator);
		  }
		else {
			result = By.id(locator);
		}

		return result;
	}


	public Boolean isElementPresent(String locator) {
		Boolean result = false;
		try {
			getWebDriver().findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) {

		}

		return result;
	}
	

	public Boolean isElementDisplayed(String locator) {

		Boolean result = false;
		try {
			getWebDriver().findElement(ByLocator(locator)).isDisplayed();
			result = true;
		} catch (Exception ex) {

		}
		return result;
	}
	

	public void WaitForElementPresent(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotPresent(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (!isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void WaitForElementEnabled(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotEnabled(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator)).isEnabled()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void WaitForElementVisible(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (getWebDriver().findElement(ByLocator(locator)).isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void WaitForElementNotVisible(String locator, int timeout) {

		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				if (!getWebDriver().findElement(ByLocator(locator)).isDisplayed()) {
					break;
				}
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void mouseOver(String locator){		
		this.WaitForElementPresent(locator, 50);		
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		
		Actions builder = new Actions(getWebDriver());    
		builder.moveToElement(el).build().perform();		
	}
	
	public void mouseDoubleClick(String locator){
		this.WaitForElementPresent(locator, 50);		
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		Actions builder = new Actions(getWebDriver());    
		builder.doubleClick(el).perform();
	}

	public void dragAndDrop(String draggable, String to)
	{
		WebElement elDraggable = getWebDriver().findElement(ByLocator(draggable));
		WebElement todrag = getWebDriver().findElement(ByLocator(to));
		Actions builder = new Actions(getWebDriver());   
		builder.dragAndDrop(elDraggable, todrag).perform();		
	}
	
	public void clickOn(String locator)
	{		
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));			
		el.click();
	}
	
	public void clearTextField(String locator)
	{		
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));			
		el.clear();
	}
	
	public void sendKeys(String locator, String userName){		
		this.WaitForElementPresent(locator, 30);		
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(userName);
	}
	
	
	
	public void selectDropDown(String locator, String targetValue){ 
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		this.WaitForElementPresent(locator, 20);
		new Select(getWebDriver().findElement(ByLocator(locator))).selectByVisibleText(targetValue);
		
    }
	
	public boolean isTextPresent(String locator, String str){
		Assert.assertTrue(isElementPresent(locator) ,"Element Locator :"+locator+" Not found");
		String message = getWebDriver().findElement(ByLocator(locator)).getText();		
		if(message.contains(str)){return true;}
		else {	return false; }
	}
	
	public String getText(String locator){
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getText();	
		return text;
	}
	
	public boolean isChecked(String locator){
		boolean checkStatus = false;
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));	    
	    checkStatus = el.isSelected();	    
	    return checkStatus;
	}
	
	public String getAttribute(String locator, String attribute){
		WaitForElementPresent(locator, 20);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		String text = getWebDriver().findElement(ByLocator(locator)).getAttribute(attribute);	
		return text;
	}
	public Integer getInt(String strString){
        	Pattern intsOnly = Pattern.compile("\\d+");
        	String input = strString;
            Matcher makeMatch = intsOnly.matcher(input);
            makeMatch.find();
            String digitStr  = makeMatch.group();
            Integer digit = Integer.parseInt(digitStr);            
            return digit;
	}
	
	public void javaScriptExecute(String javascrpt){
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
	    js.executeScript(javascrpt);
	}
	
	public void rightClick(String locator){
		WebElement el = getWebDriver().findElement(ByLocator(locator));
		
		//build and perform the mouseOver with Advanced User Interactions API
		Actions builder = new Actions(getWebDriver());    
		builder.contextClick(el).build().perform();
		//builder.moveToElement(el).build().perform();	
	}
	

	//click on yes at the delete video popup
	public void acceptPopup()
	{
		WebDriverWait wait= new WebDriverWait(getWebDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
		getWebDriver().findElement(By.xpath("//div[@class='modal-footer']/button[contains(text(),'yes')]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
		
	}
	
	public void acceptPopupContinueButton()
	{
		WebDriverWait wait= new WebDriverWait(getWebDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
		getWebDriver().findElement(By.xpath("//div[@class='modal-footer']/button[contains(text(),'continue')]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
		
	}
	

	public void clickTextField(String locator)
	{		
		this.WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator),"Element Locator :"+locator+" Not found");
		WebElement el = getWebDriver().findElement(ByLocator(locator));			
		el.clear();
	}
	
	
	
}
