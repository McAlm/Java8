package de.mcalm.Java8;

import java.util.function.Consumer;
import java.util.logging.Logger;

import org.junit.Test;

public class ConsumerTest {

	@Test
	public void test() {
		
		Consumer<String> logToSysOut =  x -> System.out.println(x);
		Consumer<String> logToConsole = x -> Logger.getGlobal().info(x);
		
		executeConsumer(logToSysOut, "Hallo");
		executeConsumer(logToConsole, "Palim");
		
	}

	
	private void executeConsumer(Consumer<String> consumer, String s) {
		consumer.accept(s);
	}
}
