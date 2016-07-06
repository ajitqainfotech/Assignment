package mypackage;

import java.util.Iterator;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TatocBasic 
{
	@Test
	public static void main() throws InterruptedException{
WebDriver driver=new FirefoxDriver();
String baseUrl="http://10.0.1.86/tatoc/basic/grid/gate";
driver.get(baseUrl);
driver.findElement(By.xpath("//div[@class='greenbox']")).click();
driver.switchTo().frame(driver.findElement(By.id("main")));
Thread.sleep(3000);
String color=driver.findElement(By.cssSelector("#answer")).getAttribute("class");
System.out.println(color);
driver.switchTo().frame(driver.findElement(By.id("child")));
String color1=driver.findElement(By.cssSelector("#answer")).getAttribute("class");
System.out.println(color1);
while(!(color.equals(color1)))
{

	driver.switchTo().defaultContent();
	driver.switchTo().frame(driver.findElement(By.id("main")));
	driver.findElement(By.xpath("//a[contains(text(),'Repaint Box 2')]")).click();
	driver.switchTo().frame(driver.findElement(By.id("child")));
	 color1=driver.findElement(By.cssSelector("#answer")).getAttribute("class");
	 driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("main")));
	}
driver.findElement(By.xpath("//a[text()='Proceed']")).click();
WebElement element = driver.findElement(By.xpath("//div[@class='ui-draggable']"));
WebElement target = driver.findElement(By.xpath("//div[@id='dropbox']"));

(new Actions(driver)).dragAndDrop(element, target).perform();
driver.findElement(By.xpath("//a[text()='Proceed']")).click();
driver.findElement(By.xpath("//a[text()='Launch Popup Window']")).click();
String MainWindow=driver.getWindowHandle();  

// To handle all new opened window.    
    Set<String> s1=driver.getWindowHandles();  
Iterator<String> i1=s1.iterator();  
  
while(i1.hasNext())   
{  
    String ChildWindow=i1.next();  
      
    if(!MainWindow.equalsIgnoreCase(ChildWindow))   
    {      
         
            // Switching to Child window
            driver.switchTo().window(ChildWindow);  
                                                                                 
                                                                                     
            driver.findElement(By.cssSelector("#name"))
               .sendKeys("ajitsingh");   
                                   
               driver.findElement(By.cssSelector("#submit")).click();   
            
// Closing the Child Window.
              
    }  
}  
// Switching to Parent window i.e Main Window.
driver.switchTo().window(MainWindow);
driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();
driver.findElement(By.xpath("//a[contains(text(),'Generate Token')]")).click();  
String text=driver.findElement(By.xpath("//span[@id='token']")).getText(); 

String m=text.substring(7,text.length() );
System.out.println(m);
Cookie name = new Cookie("Token",m);
System.out.println(name);
driver.manage().addCookie(name);
driver.findElement(By.xpath("//a[contains(text(),'Proceed')]")).click();

}
}

