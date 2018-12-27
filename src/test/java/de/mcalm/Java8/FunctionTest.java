package de.mcalm.Java8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class FunctionTest {

	@Test
	public void test() {
		
		
		Function<String, String> toUpper = s -> s.toUpperCase();

		List<String> names = new ArrayList<String>();
		names.add("Peter");
		names.add("Paul");
		names.add("Mary");
		//names.forEach(s -> System.out.println(s));
		Stream<String> mappedStream = names.stream().map(toUpper);
		names.add("Lizzy");
		mappedStream.forEach(s-> assertTrue(s.matches("[A-Z]*")));
//		mappedStream..forEach(System.out::println);

		Function<Integer, Double> c2k = c -> new Double(c * 9.0 / 5 + 32);

		UnitConverter<Integer, Double> f2k = (Integer f) -> new Double(f + 273.15);
		
		assertEquals(294.15, f2k.convert(21));
		assertEquals(50.0, c2k.apply(10));
		
		UnitConverter<Integer, Integer> kmtr2mtr = (Integer mtr) -> new Integer(mtr * 1000);

		IntStream.range(0, 10).boxed().parallel().map(c2k).forEach(System.out::println);
		
		ConvertedUnitPrinter <Integer> f2kPrinter = new ConvertedUnitPrinter<Integer>();
		f2kPrinter.print(36,  f2k);
		f2kPrinter.print(5, kmtr2mtr);
		
	}
	
	@FunctionalInterface
	private static interface UnitConverter <I, R>{
		
		public R convert(I i);
	}
	
	
	private class ConvertedUnitPrinter <I>  {
		
		public <T extends UnitConverter<I, ?>> void print(I i,  T t ) {
			System.out.println(i + " converted is " + t.convert(i));
		}
	}
}
