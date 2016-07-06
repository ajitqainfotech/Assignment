package PageObjectRepository;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	
	@FindBy(xpath=".//*[@id='login_field']")
	protected
	WebElement EmailText;
	
	@FindBy(xpath=".//*[@id='password']")
	protected
	WebElement PasswordText;
	
	@FindBy(xpath=".//*[@id='login']/form/div[4]/input[3]")
	protected
	WebElement Login;
		
	
}
