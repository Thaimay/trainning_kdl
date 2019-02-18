package jp.co.world.storedevelopment;

import org.springframework.core.Ordered;

public class FilterOrdered {

	private static final int BASE = Ordered.HIGHEST_PRECEDENCE;

	public static final int logFilter = BASE;

	public static final int spSecurityFilter = BASE - 1;

	public static final int corsFilter = BASE - 2;

	public static final int sanitizeFilter = BASE - 3;
	
	public static final int activeUrlFilter = BASE - 4;

	public static final int mockFilter = BASE + 3;

}
