package com.qa.bookstoreapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnotherSampleComplexJSONPayload {

	public static void main(String[] args) {

		List<Integer> rgbaArrayObject1 = new ArrayList<Integer>();
		Map<String, Object> codeHashMapObject1 = new HashMap<String, Object>();
		Map<String, Object> colorsHashMapObject1 = new HashMap<String, Object>();

		List<Integer> rgbaArrayObject2 = new ArrayList<Integer>();
		Map<String, Object> codeHashMapObject2 = new HashMap<String, Object>();
		Map<String, Object> colorsHashMapObject2 = new HashMap<String, Object>();

		for (int i = 0; i < 3; i++) {
			rgbaArrayObject1.add(255);
		}

		rgbaArrayObject1.add(1);

		codeHashMapObject1.put("rgba", rgbaArrayObject1);
		codeHashMapObject1.put("hex", "#000");

		colorsHashMapObject1.put("color", "black");
		colorsHashMapObject1.put("category", "hue");
		colorsHashMapObject1.put("type", "primary");
		colorsHashMapObject1.put("code", codeHashMapObject1);

		for (int i = 0; i < 3; i++) {
			rgbaArrayObject2.add(0);
		}

		rgbaArrayObject1.add(1);

		codeHashMapObject2.put("rgba", rgbaArrayObject2);
		codeHashMapObject2.put("hex", "#FFF");

		colorsHashMapObject2.put("color", "white");
		colorsHashMapObject2.put("category", "value");
		colorsHashMapObject2.put("code", codeHashMapObject2);

		List<Map<String, Object>> colorsArray = new ArrayList<Map<String, Object>>();
		colorsArray.add(colorsHashMapObject1);
		colorsArray.add(colorsHashMapObject2);

		Map<String, List<Map<String, Object>>> finalJSONHashMap = new HashMap<String, List<Map<String, Object>>>();
		finalJSONHashMap.put("colors", colorsArray);
		
		System.out.println(finalJSONHashMap);

	}

}
