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

	static JSONParser parser = new JSONParser();

	/**
	 * This method returns the base Currency from the JSON response
	 * 
	 * @param response
	 * @return
	 * @throws CustomException
	 */
	public static String getBaseCurrency(String response) throws CustomException {
		try {

			JSONObject jsonObjects = (JSONObject) parser.parse(response);
			return jsonObjects.get("base").toString();
		} catch (NullPointerException e) {
			throw new CustomException("Null value passed in response",e);
			
		} catch (Exception e) {
			throw new CustomException("Unable to parse JSON Response object", e);
		}

	}

	/**
	 * This method validates a member from the response rates for a specific value
	 * 
	 * @param response
	 * @param member
	 * @param value
	 * @return
	 * @throws CustomException
	 */
	public static Object checkRateForMemberValue(String response, String member, String value) throws CustomException {
		try {

			JSONObject jsonObjects = (JSONObject) parser.parse(response);

			JSONObject allRates = (JSONObject) parser.parse(jsonObjects.get("rates").toString());

			String valueResp = allRates.get(member).toString();

			if (valueResp.equals(value))
				return true;
			else
				return false;

		} catch (NullPointerException e) {
			throw new CustomException("Null value passed in response",e);
			
		} catch (Exception e) {
			
			throw new CustomException("Unable to parse JSON Response object", e);
		}
	}

	/**
	 * This method validates whether rates are present
	 * 
	 * @param responseBody
	 * @return
	 * @throws CustomException
	 */
	public static boolean validateRatesPresent(String responseBody) throws CustomException {

		JSONObject jsonObjects;
		try {
			jsonObjects = (JSONObject) parser.parse(responseBody);
			JSONObject allRates = (JSONObject) parser.parse(jsonObjects.get("rates").toString());

			return (!Objects.isNull(allRates));
		} catch (NullPointerException e) {
			throw new CustomException("Null value passed in response",e);
			
		} catch (ParseException e) {
			throw new CustomException("Unable to parse JSON response");
		}

	}

	/**
	 * This method validates the date present in response to the request sent
	 * 
	 * @param responseBody
	 * @param dateReq
	 * @return
	 * @throws CustomException
	 */
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

		JSONObject jsonObjects;
		try {
			jsonObjects = (JSONObject) parser.parse(responseBody);

			String dateResp = jsonObjects.get("date").toString();

			return dateResp.equals(date);
		} catch (NullPointerException e) {
			throw new CustomException("Null value passed",e);
			
		} catch (ParseException e) {
			throw new CustomException("Unable to parse JSON response");
		}
	}

	/**
	 * This method validates if date is present or not
	 * 
	 * @param responseBody
	 * @return
	 * @throws CustomException
	 */
	public static boolean validateDate(String responseBody) throws CustomException {

		JSONObject jsonObjects;
		try {
			jsonObjects = (JSONObject) parser.parse(responseBody);

			String dateResp = jsonObjects.get("date").toString();

			return (!Objects.isNull(dateResp));
		} catch (NullPointerException e) {
			throw new CustomException("Null value passed in response",e);
			
		} catch (ParseException e) {
			throw new CustomException("Unable to parse JSON response");
		}
	}

	/**
	 * This method validates and returns true if rates are not present in the
	 * response body
	 * 
	 * @param responseBody
	 * @return
	 * @throws CustomException
	 */
	public static boolean validateRatesNotPresent(String responseBody) throws CustomException {

		try {

			JSONObject jsonObjects = (JSONObject) parser.parse(responseBody);
			jsonObjects.get("base").toString();
			return false;
		} catch (NullPointerException e) {
			return true;
		} catch (ParseException e) {

			throw new CustomException("Unable to parse JSON Response");
		}

	}

	/**
	 * Validates whether error message is displayed with a specifc substring element
	 * 
	 * @param responseBody
	 * @param member
	 * @return
	 * @throws CustomException
	 */
	public static boolean validateErrorMessageDisplayed(String responseBody, String element) throws CustomException {
		try {

			JSONObject jsonObjects = (JSONObject) parser.parse(responseBody);
			String value = jsonObjects.get("error").toString();
			return value.contains(element);

		} catch (NullPointerException e) {
			throw new CustomException("Null value passed in response",e);
			
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	/**
	 * Checks if member value is not present in the rates of response body
	 * 
	 * @param responseBody
	 * @param member
	 * @return
	 */
	public static Object checkRateForMemberValueNotPresent(String responseBody, String member) {
		try {

			JSONObject jsonObjects = (JSONObject) parser.parse(responseBody);

			JSONObject allRates = (JSONObject) parser.parse(jsonObjects.get("rates").toString());

			String valueResp = allRates.get(member).toString();

			if (Objects.isNull(valueResp))
				return true;
			else
				return false;

		} catch (Exception e) {
			return true;
		}
	}

}
