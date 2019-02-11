package jp.co.world.storedevelopment.logging.file;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class AccessFileConverter extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		return new AccessFileInfo(request).toString();
	}
}
