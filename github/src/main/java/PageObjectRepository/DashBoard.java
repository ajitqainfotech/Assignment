package PageObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashBoard {

	String repoName="hello";
	
	@FindBy(css="[class*=btn-primary]")
	public WebElement newRepository;
	
	@FindBy(css=".header-nav-link.name.tooltipped.tooltipped-sw.js-menu-target")
	public WebElement scrollBar;
	
	@FindBy(css=".dropdown-header.header-nav-current-user.css-truncate")
	public WebElement sighinDescription;
	

}
