package Keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import PageObjectRepository.DashBoard;
import PageObjectRepository.OnClickNewRepository;

public class DashBoardMethods extends DashBoard{

	
	WebDriver driver;
	public DashBoardMethods(WebDriver driver)
	{
		this.driver=driver;
	}
	public void clickOnRepository(String repoName) throws InterruptedException
	{
		
			Thread.sleep(2000);
			driver.findElements(By.xpath(".//*[@id='your_repos']/div[2]/div[2]/a")).get(0).click();
			System.out.println("hello ..");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='"+"hello"+"']")).click();
		
	}
	
	public void CreateRepository(String repoName) throws InterruptedException
	{
		newRepository.click();
		OnClickNewRepository obj=PageFactory.initElements(driver, OnClickNewRepository.class);
		obj.repositoryName.sendKeys(repoName);
		obj.createRepositoryButton.click();
		
	}
	
	
}
