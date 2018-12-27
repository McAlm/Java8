package com.mkyong.json;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2Example {

	public static void main(String[] args) {
		Jackson2Example obj = new Jackson2Example();
		obj.run();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object
//			Staff staff = mapper.readValue(new File("C:\\staff.json"), Staff.class);
//			System.out.println(staff);

			// Convert JSON string to Object
			String jsonInString = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
			Staff staff1 = mapper.readValue(jsonInString, Staff.class);
			System.out.println(staff1);

			//Pretty print
			String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff1);
			System.out.println(prettyStaff1);
			
			
			
			Map<String, Map<Integer, Double>> rules = mapper.readValue(new File("I:\\MA\\wiese\\rules.json"), Map.class);
			Set<Entry<String, Map<Integer, Double>>> entrySet = rules.entrySet();

			for (Entry<String, Map<Integer, Double>> entry : entrySet) {
				System.out.println(entry.getKey());
				Map<Integer, Double> value = entry.getValue();
				Set<Entry<Integer, Double>> entrySet2 = value.entrySet();
				for (Entry<Integer, Double> entry2 : entrySet2) {
					System.out.println(entry2.getKey() + " " + entry2.getValue());
				}
				
			}
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}