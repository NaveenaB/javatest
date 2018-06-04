package jTest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class httpGetResponse {

	public static void main(String[] args) throws IOException, ParseException {
		HttpURLConnection conn;
	
		String proxyHost = "135.28.13.11";
		String proxyPost = "8080";
		String endpoint = "https://api.github.com/users"; 
			//	"https://finalstage.att.com/apis/personalization/goldeneye/uuidgenerator"; 
			//	"https://api.github.com/repositories";
			//	"https://www.att.com/apis/cart/v1/metadata/923dd775-9140-a202-3b1f-4739f45804d5";
			//	"https://api.github.com/users";
		URL url = new URL(endpoint);
	
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", proxyPost);
		conn.setRequestProperty("accept", "application/json");
		conn.connect();
		System.out.println(conn.getResponseCode());
		
		 //Receive the response from the server
        InputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

		System.out.println(result);
		String str = result.toString();
	
		Object obj = new JSONParser().parse(str); 
		if(obj instanceof  JSONObject)
		{
			JSONParser parser = new JSONParser();
			JSONObject json =   (JSONObject) parser.parse(str);

			 for (Object key : json.keySet()) {
			     String keyStr = (String)key;
			     Object keyvalue = json.get(keyStr);
		        System.out.println(keyStr + ":" + keyvalue);
			 }
		}
		else if (obj instanceof  JSONArray)
		{
			JSONParser parser = new JSONParser();
			JSONArray json =   (JSONArray) parser.parse(str);
	
			for(int i=0;i<json.size();i++)
				System.out.println(json.get(i).toString());
	}
}

}
