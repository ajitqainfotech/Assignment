package Keywords;


import PageObjectRepository.LoginPage;

public class LoginPageMethods extends LoginPage {

	
	public void clickOnLogin(String username,String password)
	{
		EmailText.sendKeys(username);
		PasswordText.sendKeys(password);
		Login.click();
	}
	
	
	

}
