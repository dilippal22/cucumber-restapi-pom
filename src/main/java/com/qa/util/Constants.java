package com.qa.util;

/**
 * @author Dilip Pal
 *
 */
public class Constants {

	public static final String baseURL = "https://bookstore.toolsqa.com";

	public static final String tokenBasePathURI = "/Account/v1/GenerateToken";

	public static final String listOfAvailableBooksPathURI = "/BookStore/v1/Books";

	public static final String configPropertiesFilePath = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\qa\\config\\Config.properties";

	public static final String createBookStoreJsonISBNPayload = System.getProperty("user.dir")
			+ "\\src\\test\\resource\\com\\qa\\BookStore\\Payload\\CreateBookStoreISBNPayload.json";

	public static final String createBookStorePayload = System.getProperty("user.dir")
			+ "\\src\\test\\resource\\com\\qa\\BookStore\\Payload\\CreateBookStorePayload.json";

	public static final String uploadFile = System.getProperty("user.dir") + "\\UploadFile.txt";
	
	public static final String getBookStoreSchema = System.getProperty("user.dir")
			+ "\\src\\test\\resource\\com\\qa\\BookStore\\Payload\\GetBookStoreSchema.json";

}
