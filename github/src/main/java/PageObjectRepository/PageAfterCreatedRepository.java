package PageObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageAfterCreatedRepository {

	@FindBy(xpath=".//*[@id='js-repo-pjax-container']/div[2]/div[1]/div[1]/div[1]/div/div[3]/div/span/input")
	public WebElement repoPath;
	
	
}
