package jTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonArraySum {
	public static void main(String[] args) throws IOException ,ParseException{
		String filename = System.getProperty("user.dir")+"\\sum.json";
		String c = new String(Files.readAllBytes(Paths.get(filename)));
		JSONParser parser = new JSONParser();
		JSONArray json =   (JSONArray) parser.parse(c);
		int cntInt = 0 , runningTotal = 0; 
		for(int i=0;i<json.size();i++)
		{
			JSONObject jsonobj =   (JSONObject) parser.parse(json.get(i).toString());
			int sum =0;
			JSONArray obj =   (JSONArray) parser.parse(jsonobj.get("numbers").toString());
			
			for(int k=0;k<obj.size();k++)
				sum = sum + Integer.parseInt(obj.get(k).toString());

			System.out.println(jsonobj.get("numbers") + " : " + sum);
			cntInt = cntInt + obj.size();
			runningTotal = runningTotal + sum;
			System.out.println("runningTotal "  + runningTotal);
		}
		System.out.println("Total integers that were summed " + cntInt);
	}
}

