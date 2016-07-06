package PageObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage {

	
	@FindBy(css=".btn.site-header-actions-btn.mr-2")
	protected
	WebElement sighin;

	
	
}
