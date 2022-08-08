package com.qa.bookstoreapi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import com.qa.util.ConfigReader;
import com.qa.util.Constants;

import io.restassured.filter.log.LogDetail;

public class PUTBookStoreAPI {
	
	Properties prop = ConfigReader.readProperties();
	
	String bookISBN = "9781593277574";
	
	String putRequestPayload = "";
	
	/**
	 * First Method to validate PUT Response
	 * Send PUT request by sending JSON Array as List
	 */
	@Test
	public void validatePutResponse() {
		
		given().
				baseUri(Constants.baseURL).
				body(putRequestPayload).
				log().all().
		when().
				put(Constants.listOfAvailableBooksPathURI + "/bookISBN").
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				body("books.isbn", matchesPattern("[0-9]{13}")).
				body("books.publisher", equalTo("No Starch Press Publication")).
				body("books.subTitle", equalTo("The Definitive Guide for JavaScript Developers and Coders"));
	}
	
	/**
	 * Second Method to validate PUT Response by parameterizing the field value
	 */
	@Test
	public void statusPutResponseBody() {
		
		given().
				baseUri(Constants.baseURL).
				pathParam("isbn", bookISBN).
				body(putRequestPayload).
				log().all().
		when().
				put(Constants.listOfAvailableBooksPathURI + "/{isbn}").
		then().
				log().ifValidationFails(LogDetail.ALL).
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				body("books.isbn", matchesPattern("[0-9]{13}")).
				body("books.title", equalTo("Understanding ECMAScript 6 Fundamentals")).
				body("books.pages", equalTo("350"));
	}

}
