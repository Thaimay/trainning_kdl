package jp.co.world.storedevelopment.logging.file;

import ch.qos.logback.classic.PatternLayout;

public class AccessFileLayout extends PatternLayout {
	static {
		PatternLayout.defaultConverterMap.put("info", AccessFileConverter.class.getName());
	}
}
