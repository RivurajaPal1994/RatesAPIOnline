#Author: Rivuraja Pal
#TestingScope
#Latest Rates with 4 combinations
#Past Rates with 4 combination
#-------------------------------------------------------------------------
#OutOfScope
#Special Characters parsing -->  / \b ^%$ etc...
#--------------------------------------------------------------------------
@RatesApiFull
Feature: To Test the RatesAPI online RESTFUL Service

  @Sample @CheckServer
  Scenario: To test RatesAPI server is responding to request
    Given a request is created for latest rates without base value
    When I execute the request
    Then response is present

  #------------------------------------------------------------------------
  #------Latest Rates Scenarios--------------------------------------------
  #------------------------------------------------------------------------
  @checkLatestMessage
  Scenario: To validate that latest message without filters has a response with base,rate and date
    Given a request is created for latest rates without filters
    When I execute the request
    Then response is present
    And the base value in the response is "EUR"
    And the rates are present
    And the date is displayed

  @checkAllLatestRates
  Scenario Outline: To validate the rate is displayed for <member>
    Given a request is created for latest rates without base value
    When I execute the request
    Then response is present
    And the rates are present
    And the member "<member>", has a value of "<value>"

    Examples: 
      | member | value    |
      | CHF    |   1.0827 |
      | HRK    |    7.569 |
      | MXN    |  24.4718 |
      | ZAR    |  18.7212 |
      | INR    |  89.7975 |
      | THB    |   36.848 |
      | CNY    |   7.9184 |
      | AUD    |   1.5758 |
      | ILS    |   3.8981 |
      | KRW    |   1337.9 |
      | JPY    |   127.26 |
      | PLN    |   4.5113 |
      | GBP    |  0.90128 |
      | IDR    | 17247.33 |
      | HUF    |   359.62 |
      | PHP    |   58.947 |
      | TRY    |   9.0146 |
      | RUB    |     90.8 |
      | HKD    |   9.4982 |
      | ISK    |    155.5 |
      | DKK    |   7.4369 |
      | MYR    |   4.9359 |
      | CAD    |   1.5543 |
      | USD    |    1.225 |
      | BGN    |   1.9558 |
      | NOK    |  10.2863 |
      | RON    |   4.8708 |
      | SGD    |   1.6228 |
      | CZK    |   26.163 |
      | SEK    |   10.051 |
      | NZD    |   1.6883 |
      | BRL    |   6.5748 |

  @checkLatestRatesBasedOnSymbol
  Scenario Outline: To validate the rate is displayed for one member <member1>
    Given a request is created for latest rates with symbols for "<member1>"
    When I execute the request
    Then response is present
    And the rates are present
    And the member "<member1>", has a value of "<value1>"

    Examples: 
      | member1 | value1 |
      | CHF     | 1.0827 |

  @checkLatestRatesBasedOnSymbols
  Scenario Outline: To validate the rate is displayed for two members <member1> and <member2>
    Given a request is created for latest rates with symbols for "<member1>,<member2>"
    When I execute the request
    Then response is present
    And the rates are present
    And the member "<member1>", has a value of "<value1>"
    And the member "<member2>", has a value of "<value2>"

    Examples: 
      | member1 | value1 | member2 | value2 |
      | CHF     | 1.0827 | JPY     | 127.26 |

  @checkLatestRatesBasedOnBase
  Scenario Outline: To validate the rate is displayed for base "<base>"
    Given a request is created for latest rates with base for "<base>"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "<base>"

    Examples: 
      | base |
      | USD  |
      | CHF  |
      | HRK  |
      | MXN  |
      | ZAR  |
      | INR  |
      | THB  |
      | CNY  |
      | AUD  |
      | ILS  |
      | KRW  |
      | JPY  |
      | PLN  |
      | GBP  |
      | IDR  |
      | HUF  |
      | PHP  |
      | TRY  |
      | RUB  |
      | HKD  |
      | ISK  |
      | EUR  |
      | DKK  |
      | MYR  |
      | CAD  |
      | USD  |
      | BGN  |
      | NOK  |
      | RON  |
      | SGD  |
      | CZK  |
      | SEK  |
      | NZD  |
      | BRL  |

  @checkLatestRatesBasedOnSymbolsAndBase
  Scenario Outline: To validate the rate is displayed for two members <member1> and <member2> and base <base>
    Given a request is created for latest rates with base "<base>" and symbols for "<member1>,<member2>"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "<base>"
    And the member "<member1>", has a value of "<value1>"
    And the member "<member2>", has a value of "<value2>"

    Examples: 
      | base | member1 | value1       | member2 | value2       |
      | INR  | CHF     | 0.0120571285 | JPY     | 1.4171886745 |

  #------------------------------------------------------------------------
  #------Latest Rates Negative Scenarios-----------------------------------
  #------------------------------------------------------------------------
  @checkRatesWithBaseEmpty
  Scenario: To validate the rate is displayed for base search with no values passed
    Given a request is created for latest rates with base for ""
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "EUR"

  @checkRatesWithBaseInvalid
  Scenario Outline: To validate the error message displayed for base search with invalid <function> values
    Given a request is created for latest rates with base for "<member>"
    When I execute the request
    Then response is present
    And the rates are not present
    And error message is displayed for "<member>"

    Examples: 
      | function  | member |
      | character | XYZ    |
      | number    |    123 |

  #-------------------------------------------------------------------------
  #------------Tests for past conversion Rates------------------------------
  #-------------------------------------------------------------------------
  @checkPastRates
  Scenario: To validate that past rates are displayed for specific date
    Given a request is created for previous rates with date "2019-01-12"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "EUR"

  @checkPastRatesWithSymbols
  Scenario Outline: To validate that past rates are displayed for specific date and single symbol
    Given a request is created for previous rates with date "<date>" and symbol "<member>"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "EUR"
    And the member "<member>", has a value of "<value>"
    And the date is displayed as "<dateUpdate>"

    Examples: 
      | date       | member | value   | dateUpdate |
      | 2019-01-12 | USD    |  1.1533 | 2019-01-11 |
      | 2019-01-12 | GBP    | 0.90015 | 2019-01-11 |

  @checkPastRatesWithBase
  Scenario Outline: To validate that past rates are displayed for specific base value
    Given a request is created for previous rates with date "<date>" and base "<base>"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "<base>"
    And the date is displayed as "<dateUpdated>"

    Examples: 
      | date       | base | dateUpdated |
      | 2019-01-12 | USD  | 2019-01-11  |
      | 2019-01-12 | CHF  | 2019-01-11  |
      | 2019-01-12 | HRK  | 2019-01-11  |
      | 2019-01-12 | MXN  | 2019-01-11  |
      | 2019-01-12 | ZAR  | 2019-01-11  |
      | 2019-01-12 | INR  | 2019-01-11  |
      | 2019-01-12 | THB  | 2019-01-11  |
      | 2019-01-12 | CNY  | 2019-01-11  |
      | 2019-01-12 | AUD  | 2019-01-11  |
      | 2019-01-12 | ILS  | 2019-01-11  |
      | 2019-01-12 | KRW  | 2019-01-11  |
      | 2019-01-12 | JPY  | 2019-01-11  |
      | 2019-01-12 | PLN  | 2019-01-11  |
      | 2019-01-12 | GBP  | 2019-01-11  |
      | 2019-01-12 | IDR  | 2019-01-11  |
      | 2019-01-12 | HUF  | 2019-01-11  |
      | 2019-01-12 | PHP  | 2019-01-11  |
      | 2019-01-12 | TRY  | 2019-01-11  |
      | 2019-01-12 | RUB  | 2019-01-11  |
      | 2019-01-12 | HKD  | 2019-01-11  |
      | 2019-01-12 | ISK  | 2019-01-11  |
      | 2019-01-12 | EUR  | 2019-01-11  |
      | 2019-01-12 | DKK  | 2019-01-11  |
      | 2019-01-12 | MYR  | 2019-01-11  |
      | 2019-01-12 | CAD  | 2019-01-11  |
      | 2019-01-12 | BGN  | 2019-01-11  |
      | 2019-01-12 | NOK  | 2019-01-11  |
      | 2019-01-12 | RON  | 2019-01-11  |
      | 2019-01-12 | SGD  | 2019-01-11  |
      | 2019-01-12 | CZK  | 2019-01-11  |
      | 2019-01-12 | SEK  | 2019-01-11  |
      | 2019-01-12 | NZD  | 2019-01-11  |
      | 2019-01-12 | BRL  | 2019-01-11  |

  @checkPastRatesWithBaseAndSymbol
  Scenario Outline: To validate that past rates are displayed for base <base> and single symbol <member>
    Given a request is created for previous rates with date "<date>" and base "<base>" and symbol "<member>"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "<base>"
    And the member "<member>", has a value of "<value>"
    And the date is displayed as "<dateUpdate>"

    Examples: 
      | date       | base | member | value        | dateUpdate |
      | 2010-01-16 | USD  | GBP    | 0.6129469876 | 2010-01-15 |

  @checkPastRatesWithBaseAndSymbol
  Scenario Outline: To validate that past rates are displayed for base <base> and multiple symbols <member1>,<member2>
    Given a request is created for previous rates with date "<date>" and base "<base>" and symbol "<member1>,<member2>"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "<base>"
    And the member "<member1>", has a value of "<value1>"
    And the member "<member2>", has a value of "<value2>"
    And the date is displayed as "<dateUpdate>"

    Examples: 
      | date       | base | member1 | value1       | member2 | value2        | dateUpdate |
      | 2010-01-16 | USD  | GBP     | 0.6129469876 | INR     | 45.7075274802 | 2010-01-15 |

  #----------------------------------------------------------------------------
  #----------------Past Rates Negative cases-----------------------------------
  #----------------------------------------------------------------------------
  @checkPastRatesBefore1999
  Scenario: To validate that past rates are displayed for specific date
    Given a request is created for previous rates with date "1990-01-12"
    When I execute the request
    Then response is present
    And the rates are not present
    And error message is displayed for "no data for dates older then 1999-01-04"

  @checkPastRatesEmptyDate
  Scenario: To validate that error is displayed for empty date
    Given a request is created for previous rates with date ""
    When I execute the request
    Then response is present
    And the rates are not present
    And error message is displayed for "does not match format '%Y-%m-%d'"

  @checkPastRatesInvalidDate
  Scenario Outline: To validate that error is displayed for invalid date <date>
    Given a request is created for previous rates with date "<date>"
    When I execute the request
    Then response is present
    And the rates are not present
    And error message is displayed for "does not match format '%Y-%m-%d'"

    Examples: 
      | date          |
      | ABCD-01-12    |
      | 2019-JUL-10   |
      | 10Jun2020     |
      | 20-Mar-2020   |
      | 25-March-2020 |
      | 2020-15-12    |
      | 2020-15-16    |

  @checkPastRatesFutureDate
  Scenario: To validate that latest rates are displayed for future date
    Given a request is created for previous rates with date "9999-01-12"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "EUR"
    And the date is displayed

  @checkPastRatesNotPresentByDate
  Scenario Outline: To validate that rate for <member> is not present on <date>
    Given a request is created for previous rates with date "9999-01-12"
    When I execute the request
    Then response is present
    And the rates are present
    And the base value in the response is "EUR"
    And the date is displayed
    And the member <member>, is not present

    Examples: 
      | member | date |
