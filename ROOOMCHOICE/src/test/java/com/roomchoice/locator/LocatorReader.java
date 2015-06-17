package com.roomchoice.locator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.roomchoice.util.PropertyReader;

public class LocatorReader {

	private Document doc;
	private  PropertyReader propObj;  
	
	public LocatorReader(String xmlName) {
		SAXReader reader = new SAXReader();
		try {
			//URL url = getClass().getResource(xmlName);
			//File file = new File(url.getFile());
			propObj = new PropertyReader();
			String localPath = propObj.getPath();
			//src//test//java
			//localPath = localPath+"//src//com//roomchoice//Locator//";	
			localPath = localPath+"//src//test//java//com//roomchoice//Locator//";	
			System.out.println("my locator path is: " + localPath);
			doc = reader.read(localPath+xmlName);
			System.out.println("my doc path is: " + localPath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLocator(String locator){
		System.out.println("my real doc is: " +  doc.selectSingleNode("//" + locator.replace('.', '/')).getText());
		return doc.selectSingleNode("//" + locator.replace('.', '/')).getText();
		
	}
	
	
}
