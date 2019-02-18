package jp.co.world.storedevelopment.model.value;

import static org.junit.Assert.assertNotEquals;

import java.util.stream.IntStream;

import org.junit.Test;

public class RandomStringTest {

	@Test
	public void generateNest() {
		RandomString random = new RandomString();

		IntStream.range(0, 10).forEach(i -> {
			String first = random.nextString();
			String next = random.nextString();
			System.out.println(next);
			assertNotEquals(first, next);
		});

	}
}
