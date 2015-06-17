package com.roomchoice.util;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader
{	
	String path =  getPath();
	public String readApplicationFile(String key){ 
    	String value = "";
        try{         	  
	          Properties prop = new Properties();
	         // File f = new File(path + "//src//com//roomchoice//config//application.properties");
	          File f = new File(path + "//src//test//java//com//roomchoice//config//application.properties");
	         // localPath = localPath+"//src//test//java//com//roomchoice//Locator//";	
	          if(f.exists()){
		          prop.load(new FileInputStream(f));
		          value = prop.getProperty(key); 		                 
          	}
	   }
        catch(Exception e){  
           System.out.println("Failed to read from application.properties file.");  
        }
        return value;
     } 
    
	public String getPath()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	}
 
}