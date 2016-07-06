import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testselenium {

	void selenium_code() throws IOException, InterruptedException {
		WebDriver webDriver = new FirefoxDriver();
		webDriver.get("https://www.bing.com/translator/");
		WebElement sourcetext = webDriver.findElement(By.id("srcText"));
		List<WebElement> button = webDriver.findElements(By.className("LS_Header"));
		List<WebElement> language = webDriver.findElements(By.className("LanguageList"));
		List<WebElement> selectsource = language.get(0).findElements(By.className("LS_Item"));
		URL url1 = new URL("https://datamarket.accesscontrol.windows.net/v2/OAuth2-13");
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		List<WebElement> selectdestination = language.get(1).findElements(By.className("LS_Item"));

		Object[][] arr = BingTranslatorUsingApi.inputFileData();
		for (int j = 0; j < arr.length - 1; j++) {
			String str[] = arr[j][0].toString().split(",");
			String from = str[0];
			String to = str[1];
			String data = str[2];

			button.get(0).click();
			for (int i = 0; i < 54; i++) {
				String str1 = selectsource.get(i).getText();
				if (str1.equals(from)) {

					selectsource.get(i).click();
					break;
				}
			}

			button.get(1).click();
			for (int i = 0; i < 53; i++) {
				String str1 = selectdestination.get(i).getText();
				if (str1.equals(to)) {

					selectdestination.get(i).click();
					break;
				}
			}
			sourcetext.sendKeys(data);
			webDriver.findElement(By.id("TranslateButton")).click();
			Thread.sleep(3000);

			String str23 = webDriver.findElement(By.cssSelector("#destText"))
					.getText();
			

			sourcetext.clear();

			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("file2.txt",
							true)));

			out.println(str23);
			out.close();
		

		}
	}
}
