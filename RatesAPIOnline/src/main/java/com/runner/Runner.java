package com.runner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Runner {

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClients.createDefault();

		HttpGet getStubMethod = new HttpGet("https://api.ratesapi.io/api/latest?base=XYZ");
		HttpResponse getStubResponse = client.execute(getStubMethod);
		String responseBody = EntityUtils.toString(getStubResponse.getEntity());

		//System.out.println(responseBody);
		System.out.println(Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher("2020-01-12").matches());
		
	}

}
