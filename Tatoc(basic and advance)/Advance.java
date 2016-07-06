package mypackage;

import java.sql.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Advance {
	Connection con;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	WebDriver driver = new FirefoxDriver();
	String baseUrl = "http://10.0.1.86/tatoc/advanced/query/gate";
	Actions actions = new Actions(driver);

	@Test
	public void JdbcConnection() throws ClassNotFoundException, SQLException, InterruptedException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://10.0.1.86:3306/tatoc", "tatocuser", "tatoc01");
		stmt = con.createStatement();
		driver.get(baseUrl);
		String m = (driver.findElement(By.cssSelector("#symboldisplay")).getText()).toLowerCase();

		ResultSet rs = stmt.executeQuery(
				"SELECT name,passkey,symbol FROM credentials INNER JOIN identity on credentials.id=identity.id where symbol='"
						+ m + "'");
		rsmd = rs.getMetaData();
		rs.next();
		System.out.println(rsmd.getColumnName(1) + "  " + rsmd.getColumnName(2) + "  " + rsmd.getColumnName(3));
		System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));

		driver.findElement(By.cssSelector("#name")).sendKeys(rs.getString(1));
		driver.findElement(By.cssSelector("#passkey")).sendKeys(rs.getString(2));
		driver.findElement(By.cssSelector("#submit")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.javaScriptThrowsNoExceptions("player.play()"));
		js.executeScript("player.play()");
		Thread.sleep(25000);
		js.executeScript(" document.location=\"/tatoc/advanced/rest\"");

	}

}
