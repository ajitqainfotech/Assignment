

import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
  
@BeforeTest
public void before_test() throws IOException, InterruptedException, JSONException
{
	 testselenium obj= new testselenium();
	 obj.selenium_code();
	 
	 
	 (new BingTranslatorUsingApi()).apiCode();

}
	
	@Test
      public void fileComparison() throws IOException {
		
		FileInputStream f1=new FileInputStream("C:/Users/ajitsingh/workspace/file.txt");
		FileInputStream f2=new FileInputStream("C:/Users/ajitsingh/workspace/file2.txt");
		  
		  
		  while((f1.read()!=-1)&&(f2.read()!=-1))
		  {
			  Assert.assertTrue(f1.read()==f2.read());  
			  
		  }
		  
		  f1.close();
		  f2.close();
		
		
  }
}
