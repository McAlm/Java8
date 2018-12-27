package de.mcalm.Java8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaAndMethodReferences {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("Paul", "Anna", "Max", "Nina", "Christoph");

		names.sort(new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});

		names.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		names.sort(Comparator.comparing(String::length));

		Supplier<String> s = () -> {
			String timeOfDay = "";
			int hour = LocalDateTime.now().getHour();
			if (hour >= 0 && hour < 6) {
				timeOfDay = "night";
			} else if (hour >= 6 && hour < 12) {
				timeOfDay = "morning";
			} else if (hour >= 12 && hour < 14) {
				timeOfDay = "noon";

			} else if (hour >= 14 && hour < 18) {
				timeOfDay = "afternoon";
			} else
				timeOfDay = "evening";
			return timeOfDay;

		};

		LambdaAndMethodReferences lmr = new LambdaAndMethodReferences();
		lmr.display(lmr::getTimeOfDay);

		System.out.println(s.get());

		// shipments
		List<Shipment> shipments = new ArrayList<>();
		lmr.calculateOnShipments(shipments, new Function<Shipment, Double>() {

			@Override
			public Double apply(Shipment sh) {
				return sh.calculateWeight();
			}
		});

		lmr.calculateOnShipments(shipments, (Shipment sh) -> {
			return sh.calculateWeight();
		});

		lmr.calculateOnShipments(shipments, Shipment::calculateWeight);
		// End Of shipments

		// DoSum

		TriFunction<Sum, String, String, Integer> anon = new TriFunction<Sum, String, String, Integer>() {
			@Override
			public Integer apply(Sum sum, String s1, String s2) {
				return sum.doSum(s1, s2);
			}
		};

		System.out.println(anon.apply(new Sum(), "1", "2"));
		
		TriFunction<Sum, String, String, Integer> lambda = (Sum sum, String s1, String s2) -> {
			return sum.doSum(s1, s2);
		};
		System.out.println(lambda.apply(new Sum(), "3", "4"));

		

		
		TriFunction<String, Sum, String, Integer> lambda2 = (String s1, Sum sum, String s2) -> {
			return sum.doSum(s1, s2);
		};
		System.out.println(lambda2.apply( "3", new Sum(),"4"));
		
		

		TriFunction<Sum, String, String, Integer> mRef = Sum::doSum;
		TriFunction<Product, String, String, Integer> mRefm = Product::multiply;
		
		
		BiFunction<Sum, String, Integer> biAdd10 = new BiFunction<Sum, String, Integer>(){
			@Override
			public Integer apply(Sum t, String u) {
				return t.add10(u);
			}
		};
		
		BiFunction<Sum, String, Integer> lambdaAdd10 = (sum, str) -> {
			return sum.add10(str);
		};

		BiFunction<Sum, String, Integer> add10 = Sum::add10;
	}

	private void display(Supplier<String> s) {
		System.out.println(s.get());
	}

	public String getTimeOfDay() {
		String timeOfDay = "";
		int hour = LocalDateTime.now().getHour();
		if (hour >= 0 && hour < 6) {
			timeOfDay = "night";
		} else if (hour >= 6 && hour < 12) {
			timeOfDay = "morning";
		} else if (hour >= 12 && hour < 14) {
			timeOfDay = "noon";
		} else if (hour >= 14 && hour < 18) {
			timeOfDay = "afternoon";
		} else
			timeOfDay = "evening";
		return timeOfDay;

	}

	public List<Double> calculateOnShipments(List<Shipment> l, Function<Shipment, Double> f) {
		List<Double> results = new ArrayList<>();
		for (Shipment s : l) {
			results.add(f.apply(s));
		}
		return results;
	}
}
