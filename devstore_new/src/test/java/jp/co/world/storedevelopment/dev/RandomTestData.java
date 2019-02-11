package jp.co.world.storedevelopment.dev;

import java.util.Random;

public class RandomTestData {
	private static Random random = new Random();

	public static String getString(int length, boolean haveLetter, boolean haveNumber) {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "1234567890";
		String resource = "";

		if (haveLetter)
			resource += letters;
		if (haveNumber)
			resource += numbers;

		StringBuilder result = new StringBuilder();
		if (resource.length() > 0) {
			while (result.length() < length) {
				int index = (int) (random.nextFloat() * resource.length());
				result.append(resource.charAt(index));
			}
		}

		return result.toString();
	}

	public static int getInteger(int maxLength) {
		int bound = (int) Math.pow(10L, Long.valueOf(maxLength)) - 1;
		return Math.abs(random.nextInt(bound));
	}
	
	public static int getInteger(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
	public static Float getFloat() {
		return random.nextFloat() ;
	}
}
