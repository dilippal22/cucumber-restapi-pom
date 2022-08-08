package com.qa.bookstoreapi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.util.*;

import io.restassured.config.LogConfig;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * @author Dilip Pal
 *
 */
public class GETBookStoreAPI {
	
	Properties prop = ConfigReader.readProperties();
	Map<String, String> queryParameter = new HashMap<String, String>();

	/**
	 * Method to validate status code
	 */
	@Test
	public void validateStatusCode() {
		given().
				baseUri(Constants.baseURL).
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK")));
	}
	
	/**
	 * Method to validate response body
	 */
	@Test
	public void validateResponseBody(){
		with().
				baseUri(Constants.baseURL).
				log().headers().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifValidationFails().
				assertThat().
				body("books.title",
						hasItems("Git Pocket Guide", "Learning JavaScript Design Patterns",
								"Designing Evolvable Web APIs with ASP.NET", "An In-Depth Guide for Programmers",
								"You Don't Know JS", "Programming JavaScript Applications",
								"Eloquent JavaScript, Second Edition", "Understanding ECMAScript 6")).
				body("books.size()", equalTo(8));
	}
	
	/**
	 * Method to extract response from received response. You can extract response, header, cookies etc. but
	 * won't print status code and header. You have to print it explicitly. 
	 */
	@Test
	public void extractResponse(){
		Response res = given().
				baseUri(Constants.baseURL).
				config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				extract().
				response();
		
		System.out.println("Response receiver is: " + res.asString());
	}
	
	@Test
	public void extractSingleValueFromResponse(){
		Response res = given().
				baseUri(Constants.baseURL).
				log().body().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().cookies().
				extract().
				response();
		
		// Printing single value extracted from response body using Groovy's JPath expression
		System.out.println("Book Title is: " + res.path("books[0].title"));
		
		// Printing single value extracted from response body using Java JSON Path expression
		JsonPath jsonPath = new JsonPath(res.asString());
		System.out.println("Book Title is: " + jsonPath.getString("books[0].title"));
		
		// Another way of printing extracted single value on console
		String response = res.asString();
		System.out.println(JsonPath.from(response).getString("books[0].title"));
	}
	
	@Test
	public void validatedExtractedValueFromResponse(){
		Response res = given().
				baseUri(Constants.baseURL).
				config(config.logConfig(LogConfig.logConfig().blacklistHeader("Accept"))).
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().all().
				extract().
				response();
		
		// Using Hamcrest assertion
		String title = res.path("books[0].title");
		assertThat(title, equalTo("Git Pocket Guide"));
		
		// Using TestNG assertion
		Assert.assertEquals(title, equalTo("Git Pocket Guide"));
	}
	
	// Validate the test using all the methods of the Hamcrest collection matchers class
	@Test
	public void validateUsingHamcrestCollectionMatchers(){
		
		Set<String> blacklistHeaders = new HashSet<String>();
		blacklistHeaders.add("Accept");
		
		given().
				baseUri(Constants.baseURL).
				config(config.logConfig(LogConfig.logConfig().blacklistHeaders(blacklistHeaders))).
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().all().
				assertThat().
				// Using hasItems method. It check if one or more string is present or not
				body("books.title",
						hasItems("Git Pocket Guide", "Learning JavaScript Design Patterns",
								"Designing Evolvable Web APIs with ASP.NET", "An In-Depth Guide for Programmers",
								"You Don't Know JS")).
		
				// Using contains method. It check if all the string is present or not and in the same order.
				// If even single string is missing or order is different then will fail the test.
				body("books.title",
						contains("Git Pocket Guide", "Learning JavaScript Design Patterns",
								"Designing Evolvable Web APIs with ASP.NET", "An In-Depth Guide for Programmers",
								"You Don't Know JS", "Programming JavaScript Applications",
								"Eloquent JavaScript, Second Edition", "Understanding ECMAScript 6")).
				
				// Using contains in any order method. It check if all the string is present or not and in any order.
				// If even single string is missing then will fail the TC
				body("books.title",
						containsInAnyOrder("Git Pocket Guide", "Learning JavaScript Design Patterns",
								"Eloquent JavaScript, Second Edition", "Understanding ECMAScript 6",
								"You Don't Know JS", "Programming JavaScript Applications",
								"Designing Evolvable Web APIs with ASP.NET", "An In-Depth Guide for Programmers")).
				
				
				// Using empty method. It will check if the collection is empty or not and will fail TC accordingly.
				body("books.title", empty()).
				
				//Using not empty error method. It will check if array is not empty and will fail TC accordingly
				body("books.title", not(emptyArray())).
				
				// Using hasSize() method to check size of the collection
				body("books.title", hasSize(8)).
				
				// Using every item starts with
				body("books.title", everyItem(startsWith("Git"))).
				
				// Using hasKey() method
				body("books[0]", hasKey("isbn")).
				
				// Using hasValue() method
				body("books[0]", hasValue("9781449325862")).
				
				// Using key-value pair method
				body("books[0]", hasEntry("isbn", "9781449325862")).
				
				// Check if collection is empty or not
				body("books[0]", not(equalTo(Collections.EMPTY_MAP)));
				
				
	}
	
	// Validating response header
	@Test
	public void validateResponseHeader() {

		given().
				baseUri(Constants.baseURL).
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				headers("Content-Type", "application/json; charset=utf-8", "Connection", "keep-alive", "X-Powered-By",
						"Express");
	}
	
	// Extract response header
	@Test
	public void extractResponseHeader() {

		Headers extractedHeaders = given().
				baseUri(Constants.baseURL).
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				extract().
				headers();
		
		System.out.println("Header Name is: " + extractedHeaders.get("Content-Type").getName());
		System.out.println("Header Value is: " + extractedHeaders.get("application/json; charset=utf-8").getValue());
	}
	
	/**
	 * Method to validate status code using query Parameter
	 */
	@Test
	public void validateStatusCodeUsingSingleQueryParameter() {
		given().
				baseUri(Constants.baseURL).
				queryParam("isbn", "9781449325862").
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK")));
	}
	
	/**
	 * Method to validate status code by parameterizing the multiple query Parameter 
	 */
	@Test
	public void validateStatusCodeUsingMultipleQueryParameter() {
		queryParameter.put("isbn", "9781449331818");
		queryParameter.put("isbn", "9781449337711");
		
		given().
				baseUri(Constants.baseURL).
				queryParams(queryParameter).
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK")));
	}
	
	/**
	 * Method to validate status code using multi value query Parameter.
	 * Multi Values can be separated by comma(,) or semi-colon(;) also 
	 */
	@Test
	public void validateStatusCodeUsingMultValueyParameter() {
		given().
				baseUri(Constants.baseURL).
				queryParams("isbn", "9781449331818","9781449337711").
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK")));
	}
	
	/**
	 * Method to validate JSON Schema
	 */
	@Test
	public void validateBookStoreSchema() {
		given().
				baseUri(Constants.baseURL).
				log().all().
		when().
				get(Constants.listOfAvailableBooksPathURI).
		then().
				log().ifError().
				assertThat().
				statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_200_OK"))).
				body(matchesJsonSchemaInClasspath(Constants.getBookStoreSchema));
	}
	
	
}
