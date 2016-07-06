package GitAutomate.GitAutomate1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Keywords.DashBoardMethods;
import Keywords.LoginPageMethods;
import Keywords.StartPageMethods;
import PageObjectRepository.DashBoard;

public class AppTest {

	private final static Logger logger = Logger.getLogger(AppTest.class.getName());
		FileHandler fh;  
		Date date = new Date();
		WebDriver driver;
		String username="-----";
		String password="-----";
		String createRepoName="----"+date.getTime();
		String repoName="----";
		//where you want perform all action about creating file or more..
		String path="-----------------------";
		//value which you want to put in readme file
		String value="----";
	
		@BeforeTest
		void beforeTestMethod() throws SecurityException, IOException
		{
		fh = new FileHandler("MyLogFile.log");  
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
		fh.setFormatter(formatter);  
		System.setProperty("webdriver.chrome.driver", "/home/shivamtiwari1/chromedriver");
		driver=new ChromeDriver();
		logger.info("starting page"); 
		driver.get("https://github.com/");
		}
	
	@Test
	public void verifyLoginPage()
	{
		logger.info("Creating object of start page Methods"); 

		StartPageMethods obj=PageFactory.initElements(driver, StartPageMethods.class);
		logger.info("Click on  sigh in Button");
		obj.clickOnSighIn();
		String str=driver.findElement(By.cssSelector(".auth-form-header>h1")).getText().toString();
		String str2="Sign in to GitHub";
		logger.warning("Both string should be case sensitive !!");
		Assert.assertEquals(str,str2);
	}
		
	
	@Test(dependsOnMethods ="verifyLoginPage")
	public void verifyLogin() throws InterruptedException 
	{
		logger.info("Creating object of Login page Methods");
		LoginPageMethods obj2=PageFactory.initElements(driver, LoginPageMethods.class);
		logger.info("Click on login button");
		obj2.clickOnLogin(username,password);	
		DashBoard obj=PageFactory.initElements(driver, DashBoard.class);
		obj.scrollBar.click();
		String str=obj.sighinDescription.getText();
		Assert.assertTrue(str.contains(username));
		Thread.sleep(2000);
		driver.get(driver.getCurrentUrl());
	
	}
	
	
	@Test(dependsOnMethods ="verifyLogin")
	public void verifyNewRepositoryButtonIsClickable()
	{
		DashBoard obj=PageFactory.initElements(driver, DashBoard.class);
		logger.info("click on new Repository Button");
		obj.newRepository.click();
		Assert.assertEquals(driver.getTitle(), "Create a New Repository");
	}
	
	
	@Test(dependsOnMethods="verifyNewRepositoryButtonIsClickable")
	public void verifyCreateRepository() throws InterruptedException
	{
		DashBoardMethods obj=PageFactory.initElements(driver, DashBoardMethods.class);
		obj.CreateRepository(createRepoName);	
		Thread.sleep(3000);
		String str=driver.getCurrentUrl();
		Assert.assertTrue(str.contains(createRepoName));
		
	}
	
	
	@Test(dependsOnMethods="verifyCreateRepository")
	public void verifyCloning() throws IOException, InterruptedException
	{
		File mFile = new File(path);
		int num=mFile.list().length;
        String result="cd "+path+"\n";
        result=result+"git clone https://github.com/"+username+"/"+repoName+".git";
		FileOutputStream fos = new FileOutputStream(mFile+"/cl.sh");
		fos.write(result.getBytes());
		fos.flush();
		fos.close();
		ProcessBuilder processBuilder = new ProcessBuilder("./cl.sh");
	    processBuilder.directory(mFile);
	    Process process=Runtime.getRuntime().exec("chmod +rwx  "+path+"/cl.sh");
	    process=processBuilder.start();  
	    process.waitFor();
	    Thread.sleep(3000);
	    logger.info("this matches your number of files to updated files");
	    Assert.assertEquals(num, mFile.list().length);		
	}
	
	
	@Test(dependsOnMethods="verifyCloning")
	public void verifyPullData() throws IOException, InterruptedException
	{
		File mFile = new File(path);
		String result="cd "+path+repoName+"\n";
		result+="git init\n";
		result+="git add .\n";
		result+="git commit -m\"initialhCommit\"\n";
		result+="git remote add origin https://github.com/"+username+"/"+repoName+".git\n";
		result+="git pull origin master\n";
		result+="sleep 4\n";
		FileOutputStream fos = new FileOutputStream(mFile+"/script.sh");
		fos.write(result.getBytes());
		fos.flush();
		fos.close();
		ProcessBuilder processBuilder = new ProcessBuilder("./script.sh");
	    processBuilder.directory(mFile);
	    Process process=Runtime.getRuntime().exec("chmod +rwx  "+path+"/script.sh");
	    process=processBuilder.start();  
	    process.waitFor();
	}
	
	
	@Test(dependsOnMethods="verifyPullData")
	public void verifyClonedNumberOfFiles() throws InterruptedException
	{	    

		driver.get("https://github.com/"+username+"/"+repoName);
		List<WebElement> ls  = driver.findElements(By.className("js-navigation-open"));
		File mFile = new File(path+"/"+repoName);
		Assert.assertEquals(mFile.list().length, ls.size());
	}
	

	@Test(dependsOnMethods="verifyClonedNumberOfFiles")
	public void verifyUpdateREADMEFile() throws InterruptedException, IOException
	{
		
		driver.get("https://github.com/"+username+"/"+repoName+"/edit/master/README.md");
		driver.findElement(By.xpath(".//*[@id='new_blob']/div[3]/div[2]/div/textarea")).sendKeys(value);
	    driver.findElement(By.xpath(".//*[@id='submit-file']")).click();
	    
	    File file=new File(path+"/"+repoName+"/README.md");
	    StringBuilder ss=new StringBuilder();
	    Scanner scann=new Scanner(file);
	    while(scann.hasNextLine())
	       {
	    	   ss=ss.append(scann.nextLine());
	       }
	    String s1=ss.toString();
	    File mFile = new File(path);
		String result="cd "+path+"/"+repoName+"\n";
		result+="git init\n";
		result+="git add .\n";
		result+="git commit -m\"initialhCommit\"\n";
		result+="git remote add origin https://github.com/"+username+"/"+repoName+".git\n";
		result+="git pull origin master\n";
		result+="sleep 4\n";
		FileOutputStream fos = new FileOutputStream(mFile+"/script.sh");
		fos.write(result.getBytes());
		fos.flush();
		fos.close();
		ProcessBuilder processBuilder = new ProcessBuilder("./script.sh");
	    processBuilder.directory(mFile);
	    Process process=Runtime.getRuntime().exec("chmod +rwx  "+path+"/script.sh");
	    process=processBuilder.start();  
	    process.waitFor();
	    StringBuilder ss2=new StringBuilder();
	    Scanner scann2=new Scanner(file);
	     while(scann2.hasNextLine())
	      {
	       ss2=ss2.append(scann2.nextLine());
	      }
	    scann.close();
	    scann2.close();
	    String s2=ss2.toString();
	    logger.warning("there given string should be case sensitive");
	    Assert.assertEquals(s2.substring(0,s2.length()-s1.length()), value);
	    		
	}
	
	
	
	
	
	
	
	
}
