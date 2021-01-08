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

public class StepDefinition {
	private static HttpClient client = null;
	private static HttpGet query = null;
	private static HttpResponse response = null;
	private String responseBody;

	@Given("a request is created for latest rates without base value")
	public void a_request_is_created_for_latest_rates_without_base_value() {
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/latest");
	}

	@Given("a request is created for latest rates with symbols for {string}")
	public void a_request_is_created_for_latest_rates_with_symbols_for(String members) {

		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/latest?symbols=" + members);
	}

	@Given("a request is created for latest rates with base for {string}")
	public void a_request_is_created_for_latest_rates_with_base_for(String base) {

		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/latest?base=" + base);
	}

	@Given("a request is created for latest rates with base {string} and symbols for {string}")
	public void a_request_is_created_for_latest_rates_with_base_and_symbols_for(String base, String members) {
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/latest?base="+base+"&symbols=" + members);
	}
	
	@Given("a request is created for previous rates with date {string}")
	public void a_request_is_created_for_previous_rates_with_date(String date) {
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/" + date);
	}
	
	@Given("a request is created for previous rates with date {string} and symbol {string}")
	public void a_request_is_created_for_previous_rates_with_date_and_symbol(String date, String member) {
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/"+date+"?symbols=" + member);
	}
	
	@Given("a request is created for previous rates with date {string} and base {string}")
	public void a_request_is_created_for_previous_rates_with_date_and_base(String date, String base) {
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/"+date+"?base=" + base);
	}
	
	@Given("a request is created for previous rates with date {string} and base {string} and symbol {string}")
	public void a_request_is_created_for_previous_rates_with_date_and_base_and_symbol(String date, String base, String members) {
		if (Objects.isNull(client))
			client = HttpClients.createDefault();
		query = new HttpGet("https://api.ratesapi.io/api/"+date+"?base="+base+"&symbols="+members);
	}

	@When("I execute the request")
	public void i_execute_the_request() {
		if (Objects.isNull(client) || Objects.isNull(query))
			throw new CucumberException("HTTP Request not initialized");
		else
			try {
				response = client.execute(query);
				responseBody = EntityUtils.toString(response.getEntity());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new CucumberException("HTTP Request failed");
			}
	}

	@Then("response is present")
	public void response_is_present() {
		Assert.assertNotNull(response);
	}

	@Then("the rates are present")
	public void the_rates_are_present() {
		try {
			Assert.assertTrue(ValidateResponse.validateRatesPresent(responseBody));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			throw new CucumberException(e);
		}
	}
	
	@Then("the date is displayed")
	public void the_date_is_displayed() {

		try {
			Assert.assertTrue(ValidateResponse.validateDate(responseBody));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			throw new CucumberException(e);
		}
	}

	@Then("the date is displayed as today")
	public void the_date_is_displayed_as_today() {

		try {
			Assert.assertTrue(ValidateResponse.validateDate(responseBody,"today"));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			throw new CucumberException(e);
		}
	}
	@Then("the date is displayed as {string}")
	public void the_date_is_displayed_as(String date) {

		try {
			Assert.assertTrue(ValidateResponse.validateDate(responseBody,date));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			throw new CucumberException(e);
		}
	}

	@Then("the base value in the response is {string}")
	public void the_base_value_in_the_response_is(String base) {
		try {
			Assert.assertEquals(base, ValidateResponse.getBaseCurrency(responseBody));
		} catch (CustomException e) {
			e.printStackTrace();
			throw new CucumberException(e);
		}
	}

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
	
	@Then("the rates are not present")
	public void the_rates_are_not_present() {
		try {
	    Assert.assertTrue(ValidateResponse.validateRatesNotPresent(responseBody));
		}catch(CustomException e)
	    {
	    	throw new CucumberException(e);
	    }
	}

	@Then("error message is displayed for {string}")
	public void error_message_is_displayed(String member) {
	    try {
			Assert.assertTrue(ValidateResponse.validateErrorMessageDisplayed(responseBody,member));
		} catch (CustomException e) {
			throw new CucumberException(e);
		}
	}

}
