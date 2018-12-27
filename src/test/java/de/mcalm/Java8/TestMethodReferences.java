package de.mcalm.Java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TestMethodReferences {

	@Test
	public void testSortingWithMethodReferences() {

		List<String> names = Arrays.asList("Peter", "Paul", "Mary", "Franz", "August");

		names.sort((String s1, String s2) -> {
			return s1.length() - s2.length();
		});

		names.sort((String s1, String s2) -> Integer.compare(s1.length(), s2.length()));

		names.sort(Comparator.comparing(String::length));

	}

	@Test
	public void testMethodReferencesWithSupplier() {
		
		
	}

}
