package com.qa.bookstoreapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleComplexJSONPOSTRequestExample {

	/*
	 * Send Complex JSON POST Request using Map and List (Refer
	 * SampleComplexJSONPayload.json from Payload package)
	 */

	public static void main(String[] args) {

		Map<String, String> batterArrayMap1 = new HashMap<String, String>();
		Map<String, String> batterArrayMap2 = new HashMap<String, String>();
		Map<String, String> batterArrayMap3 = new HashMap<String, String>();
		Map<String, String> batterArrayMap4 = new HashMap<String, String>();

		batterArrayMap1.put("id", "1001");
		batterArrayMap1.put("type", "Regular");

		batterArrayMap2.put("id", "1002");
		batterArrayMap2.put("type", "Chocolate");

		batterArrayMap3.put("id", "1003");
		batterArrayMap3.put("type", "Blueberry");

		batterArrayMap4.put("id", "1004");
		batterArrayMap4.put("type", "Devil's Food");

		Map<String, String> toppingArrayMap1 = new HashMap<String, String>();
		Map<String, String> toppingArrayMap2 = new HashMap<String, String>();
		Map<String, String> toppingArrayMap3 = new HashMap<String, String>();
		Map<String, String> toppingArrayMap4 = new HashMap<String, String>();
		Map<String, String> toppingArrayMap5 = new HashMap<String, String>();
		Map<String, String> toppingArrayMap6 = new HashMap<String, String>();
		Map<String, String> toppingArrayMap7 = new HashMap<String, String>();

		toppingArrayMap1.put("id", "5001");
		toppingArrayMap1.put("type", "None");

		toppingArrayMap2.put("id", "5001");
		toppingArrayMap2.put("type", "Glazed");

		toppingArrayMap3.put("id", "5001");
		toppingArrayMap3.put("type", "Sugar");

		toppingArrayMap4.put("id", "5001");
		toppingArrayMap4.put("type", "Powdered Sugar");

		toppingArrayMap5.put("id", "5001");
		toppingArrayMap5.put("type", "Chocolate with Sprinkles");

		toppingArrayMap6.put("id", "5001");
		toppingArrayMap6.put("type", "Chocolate");

		toppingArrayMap7.put("id", "5001");
		toppingArrayMap7.put("type", "Maple");

		List<Map<String, String>> batterArrayList = new ArrayList<Map<String, String>>();

		batterArrayList.add(batterArrayMap1);
		batterArrayList.add(batterArrayMap2);
		batterArrayList.add(batterArrayMap3);
		batterArrayList.add(batterArrayMap4);

		List<Map<String, String>> toppingArrayList = new ArrayList<Map<String, String>>();

		toppingArrayList.add(toppingArrayMap1);
		toppingArrayList.add(toppingArrayMap2);
		toppingArrayList.add(toppingArrayMap3);
		toppingArrayList.add(toppingArrayMap4);
		toppingArrayList.add(toppingArrayMap5);
		toppingArrayList.add(toppingArrayMap6);
		toppingArrayList.add(toppingArrayMap7);

		Map<String, Object> battersMap = new HashMap<String, Object>();

		battersMap.put("batters", batterArrayList);

		Map<String, Object> finalArrayJSONObject1 = new HashMap<String, Object>();

		finalArrayJSONObject1.put("id", "0001");
		finalArrayJSONObject1.put("type", "donut");
		finalArrayJSONObject1.put("name", "Cake");
		finalArrayJSONObject1.put("ppu", "0.55");
		finalArrayJSONObject1.put("batters", battersMap);
		finalArrayJSONObject1.put("topping", toppingArrayList);

		// NOTE: Likewise we can bind the complex JSON using Map and List for
		// other 2 JSON Object as well

		Map<String, Object> finalArrayJSONObject2 = new HashMap<String, Object>();

		Map<String, Object> finalArrayJSONObject3 = new HashMap<String, Object>();

		List<Map<String, Object>> finalJSONArray = new ArrayList<Map<String, Object>>();

		finalJSONArray.add(finalArrayJSONObject1);
		finalJSONArray.add(finalArrayJSONObject2);
		finalJSONArray.add(finalArrayJSONObject3);
		
		System.out.println(finalJSONArray);

	}

}
