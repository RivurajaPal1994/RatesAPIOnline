$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/RatesAPIOnline.feature");
formatter.feature({
  "name": "To Test the RatesAPI online RESTFUL Service",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@RatesApiFull"
    }
  ]
});
formatter.scenarioOutline({
  "name": "To validate that past rates are displayed for base \u003cbase\u003e and single symbol \u003cmember\u003e",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@checkPastRatesWithBaseAndSymbol"
    }
  ]
});
formatter.step({
  "name": "a request is created for previous rates with date \"\u003cdate\u003e\" and base \"\u003cbase\u003e\" and symbol \"\u003cmember\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "I execute the request",
  "keyword": "When "
});
formatter.step({
  "name": "response is present",
  "keyword": "Then "
});
formatter.step({
  "name": "the rates are present",
  "keyword": "And "
});
formatter.step({
  "name": "the base value in the response is \"\u003cbase\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "the member \"\u003cmember\u003e\", has a value of \"\u003cvalue\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "the date is displayed as \"\u003cdateUpdate\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "date",
        "base",
        "member",
        "value",
        "dateUpdate"
      ]
    },
    {
      "cells": [
        "2010-01-16",
        "USD",
        "GBP",
        "0.6129469876",
        "2010-01-15"
      ]
    }
  ]
});
formatter.scenario({
  "name": "To validate that past rates are displayed for base USD and single symbol GBP",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RatesApiFull"
    },
    {
      "name": "@checkPastRatesWithBaseAndSymbol"
    }
  ]
});
formatter.step({
  "name": "a request is created for previous rates with date \"2010-01-16\" and base \"USD\" and symbol \"GBP\"",
  "keyword": "Given "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.a_request_is_created_for_previous_rates_with_date_and_base_and_symbol(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I execute the request",
  "keyword": "When "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.i_execute_the_request()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "response is present",
  "keyword": "Then "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.response_is_present()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the rates are present",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_rates_are_present()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the base value in the response is \"USD\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_base_value_in_the_response_is(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the member \"GBP\", has a value of \"0.6129469876\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_member_has_a_value_of(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the date is displayed as \"2010-01-15\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_date_is_displayed_as(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "To validate that past rates are displayed for base \u003cbase\u003e and multiple symbols \u003cmember1\u003e,\u003cmember2\u003e",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@checkPastRatesWithBaseAndSymbol"
    }
  ]
});
formatter.step({
  "name": "a request is created for previous rates with date \"\u003cdate\u003e\" and base \"\u003cbase\u003e\" and symbol \"\u003cmember1\u003e,\u003cmember2\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "I execute the request",
  "keyword": "When "
});
formatter.step({
  "name": "response is present",
  "keyword": "Then "
});
formatter.step({
  "name": "the rates are present",
  "keyword": "And "
});
formatter.step({
  "name": "the base value in the response is \"\u003cbase\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "the member \"\u003cmember1\u003e\", has a value of \"\u003cvalue1\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "the member \"\u003cmember2\u003e\", has a value of \"\u003cvalue2\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "the date is displayed as \"\u003cdateUpdate\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "date",
        "base",
        "member1",
        "value1",
        "member2",
        "value2",
        "dateUpdate"
      ]
    },
    {
      "cells": [
        "2010-01-16",
        "USD",
        "GBP",
        "0.6129469876",
        "INR",
        "45.7075274802",
        "2010-01-15"
      ]
    }
  ]
});
formatter.scenario({
  "name": "To validate that past rates are displayed for base USD and multiple symbols GBP,INR",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@RatesApiFull"
    },
    {
      "name": "@checkPastRatesWithBaseAndSymbol"
    }
  ]
});
formatter.step({
  "name": "a request is created for previous rates with date \"2010-01-16\" and base \"USD\" and symbol \"GBP,INR\"",
  "keyword": "Given "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.a_request_is_created_for_previous_rates_with_date_and_base_and_symbol(java.lang.String,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I execute the request",
  "keyword": "When "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.i_execute_the_request()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "response is present",
  "keyword": "Then "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.response_is_present()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the rates are present",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_rates_are_present()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the base value in the response is \"USD\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_base_value_in_the_response_is(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the member \"GBP\", has a value of \"0.6129469876\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_member_has_a_value_of(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the member \"INR\", has a value of \"45.7075274802\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_member_has_a_value_of(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the date is displayed as \"2010-01-15\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.cucumber.glue.RatesAPIStepDefinition.the_date_is_displayed_as(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
});