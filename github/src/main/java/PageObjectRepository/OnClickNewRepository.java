package PageObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnClickNewRepository {

	
	@FindBy(css="#repository_name")
	public
	WebElement repositoryName;
	
	
	
	@FindBy(css=".btn.btn-primary.first-in-line")
	public
	WebElement createRepositoryButton;
	
}
