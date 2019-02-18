package jp.co.world.storedevelopment;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;

import jp.co.world.storedevelopment.filter.AccessLogFilter;

@Configuration
public class CommonFilterConfig {

	@Bean
	public FilterRegistrationBean encodinfgFIlter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setForceEncoding(true);
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registrationBean;
	}

	// @Bean
	// public FilterRegistrationBean logFilter() {
	// FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	// CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
	// filter.setIncludeQueryString(true);
	// filter.setIncludePayload(true);
	// filter.setMaxPayloadLength(10000);
	// filter.setIncludeHeaders(true);
	// filter.setAfterMessagePrefix("REQUEST DATA : ");
	// registrationBean.setFilter(filter);
	// registrationBean.setOrder(FilterOrdered.logFilter);
	// return registrationBean;
	// }
	
	@Bean
	public FilterRegistrationBean accessFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		AccessLogFilter filter = new AccessLogFilter();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(FilterOrdered.logFilter);
		return registrationBean;
	}

}
