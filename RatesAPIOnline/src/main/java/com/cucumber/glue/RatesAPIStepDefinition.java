package com.cucumber.glue;

import java.io.IOException;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

import com.exception.CustomException;
import com.logic.ValidateResponse;

import io.cucumber.core.exception.CucumberException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RatesAPIStepDefinition {
	private static HttpClient client = null;
	private static HttpGet query = null;
	private static HttpResponse response = null;
	private String responseBody;
	private final String RATES_API_LATEST_QUERY_PREFIX = "https://api.ratesapi.io/api/latest";

	/**
	 * Create a request without any filters
	 */
	@Given("a request is created for latest rates without filters")
	public void a_request_is_created_for_latest_rates_without_filters() {
		initializeConnection();
		query = new HttpGet(RATES_API_LATEST_QUERY_PREFIX);
	}

	/**
	 * Create a request with only symbols parameter
	 */
	@Given("a request is created for latest rates with symbols for {string}")
	public void a_request_is_created_for_latest_rates_with_symbols_for(String members) {

		initializeConnection();
		query = new HttpGet(RATES_API_LATEST_QUERY_PREFIX + "?symbols=" + members);
	}

	/**
	 * Create a request with base parameter
	 * @param base
	 */
	@Given("a request is created for latest rates with base for {string}")
	public void a_request_is_created_for_latest_rates_with_base_for(String base) {

		initializeConnection();
		query = new HttpGet(RATES_API_LATEST_QUERY_PREFIX + "?base=" + base);
	}

	/**
	 * Create a Request with base and symbols
	 * @param base
	 * @param members
	 */
	@Given("a request is created for latest rates with base {string} and symbols for {string}")
	public void a_request_is_created_for_latest_rates_with_base_and_symbols_for(String base, String members) {
		initializeConnection();
		query = new HttpGet(RATES_API_LATEST_QUERY_PREFIX + "?base=" + base + "&symbols=" + members);
	}

	/**
	 * Create a request for fetching previous date rates
	 * @param date
	 */
	@Given("a request is created for previous rates with date {string}")
	public void a_request_is_created_for_previous_rates_with_date(String date) {
		initializeConnection();
		query = new HttpGet("https://api.ratesapi.io/api/" + date);
	}

	/**
	 * Create a request for fetching previous date rates with symbols parameter
	 * @param date
	 * @param member
	 */
	@Given("a request is created for previous rates with date {string} and symbol {string}")
	public void a_request_is_created_for_previous_rates_with_date_and_symbol(String date, String member) {
		initializeConnection();
		query = new HttpGet("https://api.ratesapi.io/api/" + date + "?symbols=" + member);
	}

	/**
	 * Create a request for previous rates with dates and base parameter
	 * @param date
	 * @param base
	 */
	@Given("a request is created for previous rates with date {string} and base {string}")
	public void a_request_is_created_for_previous_rates_with_date_and_base(String date, String base) {
		initializeConnection();
		query = new HttpGet("https://api.ratesapi.io/api/" + date + "?base=" + base);
	}

	/**
	 * Create a request for previous rates with date,base and symbols
	 * @param date
	 * @param base
	 * @param members
	 */
	@Given("a request is created for previous rates with date {string} and base {string} and symbol {string}")
	public void a_request_is_created_for_previous_rates_with_date_and_base_and_symbol(String date, String base,
			String members) {
		initializeConnection();
		query = new HttpGet("https://api.ratesapi.io/api/" + date + "?base=" + base + "&symbols=" + members);
	}

	/**
	 * Code for execution. Generates an execute query and captures the response
	 */
	@When("I execute the request")
	public void i_execute_the_request() {
		if (Objects.isNull(client) || Objects.isNull(query))
			throw new CucumberException("HTTP Request not initialized");
		else
			try {
				response = client.execute(query);
				responseBody = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				throw new CucumberException("HTTP Request failed");
			}
	}

	/**
	 * To check if response is present
	 */
	@Then("response is present")
	public void response_is_present() {
		Assert.assertNotNull(response);
	}

	/**
	 * To check that the rates are present and populated
	 */
	@Then("the rates are present")
	public void the_rates_are_present() {
		try {
			Assert.assertTrue(ValidateResponse.validateRatesPresent(responseBody));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}

	/**
	 * TO check that date is displayed in a response
	 */
	@Then("the date is displayed")
	public void the_date_is_displayed() {

		try {
			Assert.assertTrue(ValidateResponse.validateDate(responseBody));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}

	/**
	 * TO check if the updated date is today's date as per EU zone
	 */
	@Then("the date is displayed as today")
	public void the_date_is_displayed_as_today() {

		try {
			Assert.assertTrue(ValidateResponse.validateDate(responseBody, "today"));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}

	/**
	 * TO check a specific date value in the response
	 * @param date
	 */
	@Then("the date is displayed as {string}")
	public void the_date_is_displayed_as(String date) {

		try {
			Assert.assertTrue(ValidateResponse.validateDate(responseBody, date));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}

	/**
	 * To check that the base value is present with a specific member
	 * @param base
	 */
	@Then("the base value in the response is {string}")
	public void the_base_value_in_the_response_is(String base) {
		try {
			Assert.assertEquals(base, ValidateResponse.getBaseCurrency(responseBody));
		} catch (CustomException e) {
			e.printStackTrace();
			throw new CucumberException(e);
		}
	}
	
	/**
	 * TO check a specific member rate for the rate value in response
	 * @param member
	 * @param value
	 */
	@Then("the member {string}, has a value of {string}")
	public void the_member_has_a_value_of(String member, String value) {
		try {

			Assert.assertEquals(true, ValidateResponse.checkRateForMemberValue(responseBody, member, value));
		} catch (CustomException e) {
			System.out.println(member + "  " + value);
			e.printStackTrace();
			throw new CucumberException(e);
		}

	}
	
	/**
	 * TO check a specific member rate not present
	 * @param member
	 * @param value
	 */
	@Then("the member {string}, is not present")
	public void the_member_rate_not_present(String member, String value) {
		try {

			Assert.assertEquals(true, ValidateResponse.checkRateForMemberValueNotPresent(responseBody, member));
		} catch (Exception e) {
			System.out.println(member + "  " + value);
			e.printStackTrace();
			throw new CucumberException(e);
		}

	}

	/**
	 * To check that rates are not populated
	 */
	@Then("the rates are not present")
	public void the_rates_are_not_present() {
		try {
			Assert.assertTrue(ValidateResponse.validateRatesNotPresent(responseBody));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}

	/**
	 * TO check if message is getting displayed
	 * @param member
	 */
	@Then("error message is displayed for {string}")
	public void error_message_is_displayed(String member) {
		try {
			Assert.assertTrue(ValidateResponse.validateErrorMessageDisplayed(responseBody, member));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}
	
	private void initializeConnection()
	{
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
	}

}
