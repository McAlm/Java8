package de.mcalm.Java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	List<Integer> result = new ArrayList<>();

    	for (int i = 1; i <= 10; i++) {
    	    if (10 % i == 0) {
    	        result.add(i);
    	        if (i != 5) {
    	            result.add(10 / i);
    	        }
    	    }
    	}
        //result.stream().forEach(System.out::println);
    	
    	
        System.out.println( "with lambdas" );
        
        
        List<Integer> result2 =
        	    IntStream.rangeClosed(1, 10)
        	    		 .peek(s -> System.out.println("erstes peek: " + s))
        	             .filter(i -> 10 % i == 0)
        	    		 .peek(s -> System.out.println("zweites peek: " + s))
        	             .flatMap(i -> i == 5 ? IntStream.of(i) : IntStream.of(i, 10 / i))
        	             .boxed()
        	             .collect(Collectors.toList());
        
       // result2.stream().forEach(System.out::println);
    }
    

}
