import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class BingTranslatorUsingApi {

	

	static Object[][] inputFileData() throws IOException
    {
	      String line = null;
	      File file = new File("C:/Users/ajitsingh/workspace//fff.csv");
		  BufferedReader bbr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		  List<String> lines = new ArrayList<String>();
	
		  try{
		  while( (line = bbr.readLine())!= null )
          {
			System.out.println(line);
			  lines.add(line);
          }
		  }
		  catch(Exception e){	}
		  
	      Object[][] data = new Object[lines.size()][1];
	      for(int i=0;i<lines.size();i++){
	    	  data[i][0]= lines.get(i);
	      }
	      bbr.close();
		return data;
    }
	
	 void apiCode() throws IOException, JSONException {


		URL url1 = new URL("https://datamarket.accesscontrol.windows.net/v2/OAuth2-13");
		HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String id=URLEncoder.encode("ajit_8", "UTF-8");
		String pass=URLEncoder.encode("68HYUx9h+aIDK5la0+sDnuyUtmo2ekn7Vrz5PlH3tPU=", "UTF-8");	
				
		String urlParameters = "grant_type=client_credentials&client_id="+id+"&client_secret="+pass+"&scope=http://api.microsofttranslator.com";
		conn.setDoOutput(true);
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(urlParameters);
		dos.flush();
		dos.close();
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String ssss=new String(response);
		
		JSONObject obj=new JSONObject(ssss);
		String access_token=(String) obj.get("access_token");
		
		File file_code = new File("C:/Users/ajitsingh/workspace/code.csv");
	      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file_code)));
	      String line_code = null;
	      Map map=new HashMap();
	      while( (line_code = br.readLine())!= null )
	        {
	        String[]ss = line_code.split(",");
	        map.put(ss[0], ss[1]);
	        }
	    
	    Object[][] arr=inputFileData();
	  	for(int i=0;i<arr.length-1;i++)
	  	{
	  		String str[]=arr[i][0].toString().split(",");
	  		String from=(String) map.get(str[0]);
	  		String to=(String) map.get(str[1]);
	  		String text=str[2];

		String uri = "http://api.microsofttranslator.com/v2/Http.svc/Translate?text=" + URLEncoder.encode(text, "UTF-8") + "&from=" + from + "&to=" + to;
		String authToken = "Bearer" + " " + access_token;

		
		
		URL urll=new URL(uri);	
		HttpURLConnection conn1 = (HttpURLConnection) urll.openConnection();
		conn1.setRequestMethod("GET");
		conn1.setRequestProperty("Authorization",authToken);
		
	
		
		BufferedReader in1 = new BufferedReader(
		        new InputStreamReader(conn1.getInputStream()));
		String inputLine1;
		StringBuffer response1 = new StringBuffer();

		while ((inputLine1 = in1.readLine()) != null) {
			response1.append(inputLine1);
		}
		in1.close();
		
		
		String sss=response1.toString();
		sss = sss.substring(sss.indexOf(">") + 1);
		sss = sss.substring(0, sss.indexOf("<"));		
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/ajitsingh/workspace/file.txt", true)));
        
        out.println(sss);
        out.close();	
        System.out.println(sss);
		
	  	}
	}

}
