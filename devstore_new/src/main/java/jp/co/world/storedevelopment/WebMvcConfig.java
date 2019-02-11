package jp.co.world.storedevelopment;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
    @Primary 
    @Bean 
    public ObjectMapper mapper(){ 
    	ObjectMapper om = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(String.class, new ZenkakuSerializer());
        om.registerModule(module);
        om.registerModule(new JavaTimeModule());
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return om;
    } 
    
	@Override
	public Validator getValidator() {
		return validator();
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	
	private MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// プロパティファイルの名前やディレクトリも変更可能
		messageSource.setBasename("classpath:/ValidationMessages");
		// UTF-8に設定
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}