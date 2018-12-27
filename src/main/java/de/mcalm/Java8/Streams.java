package de.mcalm.Java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

	public static void main(String[] args) {

		// @formatter:off
		List<Customer> customers = Arrays.asList(
				new Customer("Paul", 320), 
				new Customer("Maxe", 430),
				new Customer("Heidi", 290),
				new Customer("Susi", 170),
				new Customer("Bernd", 150));
		// @formatter:on

		List<String> collect = customers.stream().filter(Streams::isWorthyCustomer).map(c -> c.getName().toUpperCase())
				.peek(System.out::println)
				.sorted()
				.peek(System.out::println)
				.sorted(Comparator.reverseOrder())
				.peek(System.out::println)
				.collect(Collectors.toList());

	}

	private static boolean isWorthyCustomer(Customer c) {
		return c.getOrderAmount() > 300;
	}
}
