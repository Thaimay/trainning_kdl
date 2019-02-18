package jp.co.world.storedevelopment;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jp.co.world.storedevelopment.filter.ActiveURLFilter;

@Configuration
public class SPFilterConfig {

	// @Bean
	// public FilterRegistrationBean corsFilter() {
	// UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// CorsConfiguration config = new CorsConfiguration();
	// config.setAllowCredentials(true);
	// config.addAllowedOrigin(CorsConfiguration.ALL);
	// config.addAllowedHeader(CorsConfiguration.ALL);
	// config.addAllowedMethod(CorsConfiguration.ALL);
	// source.registerCorsConfiguration("/sp/**", config);
	//
	// FilterRegistrationBean bean = new FilterRegistrationBean(new
	// CorsFilter(source));
	// bean.setOrder(FilterOrdered.corsFilter);
	// return bean;
	// }

	@Bean
	public FilterRegistrationBean activeURLFilter() {
		ActiveURLFilter activeUrl = new ActiveURLFilter();
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(activeUrl);
		bean.setOrder(FilterOrdered.activeUrlFilter);
		return bean;
	}

	@Bean
	public FilterRegistrationBean corsFilter2() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin(CorsConfiguration.ALL);
		config.addAllowedHeader(CorsConfiguration.ALL);
		config.addAllowedMethod(CorsConfiguration.ALL);
		source.registerCorsConfiguration("/pc/**", config);

		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(FilterOrdered.corsFilter);
		return bean;
	}
}
