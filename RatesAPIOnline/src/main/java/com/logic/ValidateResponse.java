package com.logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.exception.CustomException;

public class ValidateResponse {

	/**
	 * This method returns the base Currency from the JSON response
	 * 
	 * @param response
	 * @return
	 * @throws CustomException
	 */
	public static String getBaseCurrency(String response) throws CustomException {
		try {

			JSONParser parser = new JSONParser();
			JSONObject jsonObjects = (JSONObject) parser.parse(response);
			return jsonObjects.get("base").toString();
		} catch (Exception e) {
			throw new CustomException("Unable to parse JSON Response object", e);
		}

	}

	public static Object checkRateForMemberValue(String response, String member, String value) throws CustomException {
		try {

			JSONParser parser = new JSONParser();

			JSONObject jsonObjects = (JSONObject) parser.parse(response);

			JSONObject allRates = (JSONObject) parser.parse(jsonObjects.get("rates").toString());

			String valueResp = allRates.get(member).toString();
			
			if (valueResp.equals(value))
				return true;
			else
				return false;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new CustomException("Unable to parse JSON Response object", e);
		}
	}

	public static boolean validateRatesPresent(String responseBody) throws CustomException {
		JSONParser parser = new JSONParser();

		JSONObject jsonObjects;
		try {
			jsonObjects = (JSONObject) parser.parse(responseBody);
			JSONObject allRates = (JSONObject) parser.parse(jsonObjects.get("rates").toString());
			
			return (!Objects.isNull(allRates));
		} catch (ParseException e) {
			throw new CustomException("Unable to parse JSON response");
		}

	}

	public static boolean validateDate(String responseBody, String dateReq) throws CustomException {
		String date = null;
		if ("today".equals(dateReq)) {
			LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Paris"));
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			date = localDate.format(format);
		} else {
			if (Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(dateReq).matches())
				date = dateReq;
			else
				throw new CustomException("Invalid Date value passed in Request");
		}
		JSONParser parser = new JSONParser();
		JSONObject jsonObjects;
		try {
			jsonObjects = (JSONObject) parser.parse(responseBody);

			String dateResp = jsonObjects.get("date").toString();

			return dateResp.equals(date);
		} catch (ParseException e) {
			throw new CustomException("Unable to parse JSON response");
		}
	}
	
	public static boolean validateDate(String responseBody) throws CustomException {
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObjects;
		try {
			jsonObjects = (JSONObject) parser.parse(responseBody);

			String dateResp = jsonObjects.get("date").toString();

			return (!Objects.isNull(dateResp));
		} catch (ParseException e) {
			throw new CustomException("Unable to parse JSON response");
		}
	}

	public static boolean validateRatesNotPresent(String responseBody) throws CustomException {

		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObjects = (JSONObject) parser.parse(responseBody);
			jsonObjects.get("base").toString();
			return false;
		} catch (NullPointerException e) {
			return true;
		} catch (ParseException e) {

			throw new CustomException("Unable to parse JSON Response");
		}

	}

	public static boolean validateErrorMessageDisplayed(String responseBody, String member) throws CustomException {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObjects = (JSONObject) parser.parse(responseBody);
			String value = jsonObjects.get("error").toString();
			return value.contains(member);

		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

}
