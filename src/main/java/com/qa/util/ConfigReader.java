package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Dilip.Pal This method is used to load the properties from
 *         config.properties file
 * @return it returns Properties prop object
 *
 */

public class ConfigReader {

	public static File file;
	public static Properties prop;
	public static FileInputStream inputStream;

	public static Properties readProperties() {

		file = new File(Constants.configPropertiesFilePath);

		prop = new Properties();

		try {
			inputStream = new FileInputStream(file);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return prop;
	}

}
