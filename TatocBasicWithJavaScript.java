package mypackage;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TatocBasicWithJavaScript {
	@Test
	public  void basicWithJavaSript() throws InterruptedException
	{
		WebDriver driver=new FirefoxDriver();
		String baseUrl="http://10.0.1.86/tatoc/basic/grid/gate";
		driver.get(baseUrl);
		 JavascriptExecutor jse = (JavascriptExecutor) driver;  
		 jse.executeScript("document.getElementsByClassName('greenbox')[0].click()");
		 jse.executeScript("window.frames[0]");
		 String color=(String)jse.executeScript(" return window.frames[0].document.getElementById('answer').getAttribute(\"class\")");
		 jse.executeScript("window.frames[0].frames[0]");
		 String color1=(String)jse.executeScript(" return window.frames[0].frames[0].document.getElementById('answer').getAttribute(\"class\")");
		
		 while(!(color.equals(color1)))
		 { 
			 jse.executeScript("window.frames[0]");
			 jse.executeScript("window.frames[0].document.getElementsByTagName('a')[0].click()");
			 jse.executeScript("window.frames[0].frames[0]");
			 Thread.sleep(5000);
			 color1=(String)jse.executeScript("return window.frames[0].frames[0].document.getElementById('answer').getAttribute('class')");
			 jse.executeScript("window.frames[0]");
          }
		 jse.executeScript("window.frames[0].document.getElementsByTagName('a')[1].click()");
		 WebElement element=(WebElement) jse.executeScript("return document.getElementsByClassName('ui-draggable')[0]");
		 WebElement target=(WebElement) jse.executeScript("return document.getElementsByTagName('div')[5]");
		 (new Actions(driver)).dragAndDrop(element, target).perform();
		 jse.executeScript("document.getElementsByTagName('a')[0].click()");
		 jse.executeScript("document.getElementsByTagName('a')[0].click()");
		 String MainWindow=driver.getWindowHandle(); 
		 Set<String> s1=driver.getWindowHandles();  
		 Iterator<String> i1=s1.iterator();  
		   
		 while(i1.hasNext())   
		 {  
		     String ChildWindow=i1.next();  
		       
		     if(!MainWindow.equalsIgnoreCase(ChildWindow))   
		     {   
		    	 driver.switchTo().window(ChildWindow); 
		    	 jse.executeScript("document.getElementById('name').value='ajit singh'");
		    	 jse.executeScript("document.getElementById('submit').click()");
		    	 
		     }
	      }
		 
		 driver.switchTo().window(MainWindow);
		 jse.executeScript("document.getElementsByTagName('a')[1].click()");
		 Thread.sleep(2000);
		 jse.executeScript("document.getElementsByTagName('a')[0].click()");
		String text=(String)jse.executeScript("return document.getElementById('token').innerText");
		String m=text.substring(7,text.length() );
		System.out.println(m);
		Cookie name = new Cookie("Token",m);
		System.out.println(name);
		driver.manage().addCookie(name);
		 jse.executeScript("document.getElementsByTagName('a')[1].click()");
 }
}