package com.qa.bookstoreapi;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import com.qa.util.ConfigReader;
import com.qa.util.Constants;

import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;

public class POSTBookStoreAPI {
	
	File file;
	Properties prop = ConfigReader.readProperties();
	
	Map<String, String> postRequestHeaders = new HashMap<String, String>();
	Map<String, Object> mainJsonObject = new HashMap<String, Object>();
	Map<String, String> nestedJsonObject = new HashMap<String, String>();
	
		/**
		 * Method to validate POST Request using File Class method
		 * Also, telling REST not to set default content type
		 */
		@Test
		public void validateStatusCode() {
			
			postRequestHeaders.put("ContentType", "application/json");
			
			file = new File(Constants.createBookStoreJsonISBNPayload);
			
			given().
					baseUri(Constants.baseURL).headers(postRequestHeaders).
					config(config().encoderConfig(
							EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
					body(file).log().all().
			when().
					post(Constants.listOfAvailableBooksPathURI).
			then().
					log().ifValidationFails(LogDetail.ALL).
					assertThat().
					statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_201_CREATED"))).
					body("book.isbn", matchesPattern("[0-9]{13}"));
		}
		
		/**
		 * Method to Send Nested JSON Object as a Map
		 */
		@Test
		public void validateNestedJsonObjectAsMap(){
			
		nestedJsonObject.put("isbn", "9788179921623");
		nestedJsonObject.put("title", "The Monk Who Sold His Ferrari");
		nestedJsonObject.put("subTitle", "A Fable About Fulfilling Your Dreams");
		nestedJsonObject.put("author", "Robin Sharma");
		nestedJsonObject.put("publish_date", "1996-12-22T00:00:00.000Z");
		nestedJsonObject.put("publisher", "Harper Collins Publishers");
		nestedJsonObject.put("pages", "198");
		nestedJsonObject.put("description",
				"The Monk Who Sold His Ferrari is a self-help book by Robin Sharma, a writer and motivational speaker. "
						+ "The book is a business fable derived from Sharma's personal experiences after leaving his career as a litigation "
						+ "lawyer at the age of 25.");
		nestedJsonObject.put("website", "https://www.robinsharma.com/book/the-monk-who-sold-his-ferrari");

		mainJsonObject.put("collectionOfIsbns", nestedJsonObject);
			
			given().
					baseUri(Constants.baseURL).
					headers(postRequestHeaders).
					body(mainJsonObject).
					log().all().
			when().
					post(Constants.listOfAvailableBooksPathURI).
			then().
					log().ifValidationFails(LogDetail.ALL).
					assertThat().
					statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_201_CREATED"))).
					body("book.isbn", matchesPattern("[0-9]{13}")).
					body("book.title", equalTo("The Monk Who Sold His Ferrari")).
					body("book.author", equalTo("Robin Sharma"));
		}
		
		/*
		 * Method to send text across the network to the server using form data or multipart form data
		 */
		@Test
		public void sendTextUsingFormData(){
			given().
					baseUri(Constants.baseURL).
					multiPart("textKey", "value").
					log().all().
			when().
					post(Constants.listOfAvailableBooksPathURI).
			then().
					log().all().
					assertThat().
					statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_201_CREATED")));
		}
		
		/*
		 * Method to send file across the network to the server using form data or multipart form data
		 * Also, sending JSON objects as value
		 */
		@Test
		public void sendFileUsingFormData(){
			
			String attributesJSON = "JSONBody";
			
			given().
					baseUri(Constants.baseURL).
					multiPart("file", new File(Constants.uploadFile)).
					multiPart("attributes", attributesJSON, "application/json").
					log().all().
			when().
					post(Constants.listOfAvailableBooksPathURI).
			then().
					log().all().
					assertThat().
					statusCode(Integer.parseInt(prop.getProperty("RESPONSE_STATUS_201_CREATED")));
		}
		
		

}
