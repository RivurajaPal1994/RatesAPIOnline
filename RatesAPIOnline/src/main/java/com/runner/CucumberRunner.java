package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.cucumber.glue", features = "src/test/resources/features", strict = true, tags = "@checkPastRatesWithBaseAndSymbol", plugin = {
		"pretty", "html:target/cucumber-reports" }, monochrome = true)
public class CucumberRunner {

}
