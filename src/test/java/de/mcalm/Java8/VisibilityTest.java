package de.mcalm.Java8;

import java.util.function.IntConsumer;

import org.junit.Test;

public class VisibilityTest {

	@Test
	public void test() {

		new ConsumerTest().testConsumer(21);
	}

	private class ConsumerTest {

		int y = 42;

		protected void testConsumer(int y) {
			
			int w = 43;
			
			IntConsumer meinConsumer = x -> {
				
				System.out.println(x);
				System.out.println(y);
				System.out.println(this.y);
				System.out.println(w);
			};
			
			meinConsumer.accept(10);
		}
	}
}
