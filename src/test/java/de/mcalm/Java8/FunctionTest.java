package de.mcalm.Java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

public class FunctionTest {

	@Test
	public void test() {
		Function<String, String> func = s -> s.toUpperCase();

		List<String> names = Arrays.asList("Peter", "Paul", "Mary");
		names.forEach(s -> System.out.println(s));
	}

}
