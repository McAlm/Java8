package de.mcalm.Java8.orders;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMain {

	public static void main(String[] args) {

		try {

			URL resource = OrderMain.class.getClassLoader().getResource("orders.csv");
			Path path = Paths.get(resource.toURI());
			getProductsAsStream(path)
					.sorted(Comparator.comparing(Product::getPrice).reversed()).map(p -> p.getName()).distinct()
					.forEach(System.out::println);

			Long count = getProductsAsStream(path).distinct().collect(Collectors.counting());
			System.out.println("Anzahl Produkte: " + count);

			// @formatter:off
			getProductsAsStream(path)
					.distinct()
					.collect(Collectors.groupingBy(Product::getName))
					.entrySet()
					.stream()
					.flatMap(e -> e.getValue()
							.stream())
					.forEach(p -> System.out.println(p.getName() + " / " + p.getPrice()));
			// @formatter:on
			
			// @formatter:off
			DoubleSummaryStatistics statistics = getProductsAsStream(path)
					.collect(Collectors.summarizingDouble(p -> Double.parseDouble(p.getPrice())));
			System.out.println("Sum: " + statistics.getSum() + " | Min: " + statistics.getMin() + " | Max: " + statistics.getMax() + " | Count: " + statistics.getCount() + " | Average: " + statistics.getAverage());
			// @formatter:on
			
			// @formatter:off
			Double sumPrices = getProductsAsStream(path)
					.collect(Collectors.summingDouble(p -> Double.parseDouble(p.getPrice())));
			System.out.println("Sum: " + sumPrices);
			// @formatter:on
			
			// @formatter:off
			Integer reduce = getProductsAsStream(path)
					.reduce(0, (a,b) -> a + Integer.parseInt(b.getPrice()), (a,b)-> a+b);
			
			System.out.println("Summe aller Produkte aus reduce(): " + reduce);
			// @formatter:on
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Stream<Product> getProductsAsStream(Path path) throws IOException {
		return Files.lines(path)
				.skip(1)
				.filter(s -> s.trim().length() != 0)
				.flatMap(OrderMain::getProductFromLine);
	}

	private static Stream<Product> getProductFromLine(String line) {
		String[] split = Arrays.stream(line.split(";")).skip(2).toArray(String[]::new);

		Product p = null;

		List<Product> products = new ArrayList<>();

		Iterator<String> iterator = Arrays.asList(split).iterator();
		while (iterator.hasNext()) {
			p = new Product(iterator.next(), iterator.next());
			products.add(p);
		}
		return products.stream();
	}

	private static boolean isNumeric(String s) {
		// matches "100,00" and "100,00" 
		return !s.matches("-?\\d+([\\.|\\,]\\d{0,2})?");
	}

	static class Product {
		String name;
		String price;

		public Product(String name, String price) {
			super();
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public String getPrice() {
			return price;
		}

	}
}
