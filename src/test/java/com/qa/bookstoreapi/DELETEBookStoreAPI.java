package com.qa.bookstoreapi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Properties;

import org.testng.annotations.Test;

import com.qa.util.ConfigReader;
import com.qa.util.Constants;

import io.restassured.filter.log.LogDetail;

public class DELETEBookStoreAPI {
	
	Properties prop = ConfigReader.readProperties();
	
	String bookISBN = "9781593277574";
	
	/**
	 * First Method to validate DELETE Response
	 */
	@Test
	public void validateDeleteResponse() {
		
		given().
				baseUri(Constants.baseURL).
				log().all().
		when().
				put(Constants.listOfAvailableBooksPathURI + "/bookISBN").
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				body("books.isbn", matchesPattern("[0-9]{13}")).
				body("books.isbn", equalTo(bookISBN));
	}
	
	/**
	 * Second Method to validate DELETE Response
	 */
	@Test
	public void statusDeleteResponseBody() {
		
		given().
				baseUri(Constants.baseURL).
				pathParam("isbn", "bookISBN").
				log().all().
		when().
				put(Constants.listOfAvailableBooksPathURI + "/{isbn}").
		then().
				log().ifValidationFails(LogDetail.ALL).
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				body("books.isbn", matchesPattern("[0-9]{13}")).
				body("books.isbn", equalTo(bookISBN));
	}

}
