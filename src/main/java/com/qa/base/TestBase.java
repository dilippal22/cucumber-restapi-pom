package com.qa.base;

import static io.restassured.RestAssured.*;

import java.util.Properties;

import org.testng.annotations.*;

import com.qa.util.ConfigReader;
import com.qa.util.Constants;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Dilip Pal
 *
 */
public class TestBase {

	private static Properties prop = ConfigReader.readProperties();

	private static Response response; // Response
	private static String jsonString; // JsonPath
	private static String authorizationToken; // Token

	private static RequestSpecification request;

	//@BeforeClass (dependsOnMethods = "generateAuthorizedUserToken")
	public void beforeRequest(String basePath) {
		// with() method can also be used instead of given(). To use with() import static package
		with().
				baseUri(Constants.baseURL).
			
				// Using log().all() to log everything like request body, headers, cookies etc.
				log().all();
			
				// Using log().headers() to log request headers only
				//log().headers().
	}
	

	public static void generateAuthorizedUserToken() {
		
		String authorizationRequestPayloadBody = "{\n" +
				 "		\"userName\": \"TOOLSQA-Test\",\n" +
				 "		\"password\": \"Test@123\",\n" +
					  "}";
		
		/*String authorizationRequestPayloadBody = "{\n" + 
				"		\"userName\": \"" + prop.getProperty("AdminUsername") + 
				"		\"password\": \"" + prop.getProperty("AdminPassword") +
					  "}";*/
		
		response = given().
				baseUri(Constants.baseURL).
				contentType(ContentType.JSON).
				body(authorizationRequestPayloadBody).
				log().all().
		when().
				get(Constants.tokenBasePathURI).
		then().
				log().all().
				extract().
				response();

		jsonString = response.asString();
		authorizationToken = JsonPath.from(jsonString).get("token");
		
		System.out.println(jsonString);
	}

	//@AfterClass
	public void afterTest() {
		// Reset Values
		RestAssuredUtil.resetBaseURI();
		RestAssuredUtil.resetBasePath();
	}

}
