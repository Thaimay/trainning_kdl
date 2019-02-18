package jp.co.world.storedevelopment.logging.file;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;

public class AccessFileEncoder extends PatternLayoutEncoderBase<ILoggingEvent> {
	@Override
	public void start() {
		AccessFileLayout accessFileLayout = new AccessFileLayout();
		accessFileLayout.setContext(context);
		accessFileLayout.setPattern(getPattern());
		accessFileLayout.setOutputPatternAsHeader(outputPatternAsHeader);
		accessFileLayout.start();
		this.layout = accessFileLayout;
		super.start();
	}
}
