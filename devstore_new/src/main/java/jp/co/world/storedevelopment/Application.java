/**
 *
 */
package jp.co.world.storedevelopment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import jp.co.world.storedevelopment.utils.PathUtils;


@SpringBootApplication
@ComponentScan("jp.co.world.storedevelopment")
public class Application extends SpringBootServletInitializer {

	public static int PAGING_SIZE = 10;

	public static int SUGGEST_LIMIT_SIZE = 20;

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(Application.class, args);
	}

	// @Value("${s3.directory}")
	// private String s3Path;

	public static String resourcePath() {
		String mode = mode();
		switch (mode) {
		case "development":
			return PathUtils.packagePath("static");
		case "staging":
			return productionFilePath();
		case "kdl_staging":
			return productionFilePath();
		case "production":
			return productionFilePath();
		default:
			return "./src/main/resources/static/";
		}
	}

	public static String mode() {
		return Optional.ofNullable(System.getProperty("mode")).orElse("");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("100Mb");
		return factory.createMultipartConfig();
	}

	public static String batchCsvDirPath() {
		String unicagePath = "/home/unicage/";
		String mode = mode();
		switch (mode) {
		case "development":
			return "./db/csv/";
		case "staging":
			return unicagePath;
		case "kdl_staging":
			return unicagePath;
		case "production":
			return unicagePath;
		default:
			return "./db/csv/";
		}
	}

	private static String productionFilePath() {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = classLoader.getResource("application.properties").openStream();
			Properties properties = new Properties();
			properties.load(is);
			return properties.getProperty("s3.directory");
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("application.propertiesが存在しません");
		}
	}
}
