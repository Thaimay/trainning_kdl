package jp.co.world.storedevelopment.logging;

import java.io.InputStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import jp.co.world.storedevelopment.utils.PathUtils;

public class AccessLogListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	private boolean started = false;

	@Override
	public void start() {
		if (started)
			return;

		try {
			String myBatisConfigPath = getMyBatisConfigPath();
			if (myBatisConfigPath == null) {
				throw new IllegalStateException("Not find MyBatis config path : " + getMybatisEnviroment());
			}
			String mode = getRunningMode();
			InputStream inputStream = Resources.getResourceAsStream(myBatisConfigPath);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, mode);
			PooledDataSource source = (PooledDataSource) sqlSessionFactory.openSession().getConfiguration().getEnvironment().getDataSource();

			Context context = getContext();
			context.putProperty("DEV_HOME", logFilePath());
			context.putProperty("DB_DRIVER", source.getDriver());
			context.putProperty("DB_URL", source.getUrl());
			context.putProperty("DB_USERNAME", source.getUsername());
			context.putProperty("DB_PASSWORD", source.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}

		started = true;
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isStarted() {
		return started;
	}

	@Override
	public boolean isResetResistant() {
		return true;
	}

	@Override
	public void onStart(LoggerContext context) {
	}

	@Override
	public void onReset(LoggerContext context) {
	}

	@Override
	public void onStop(LoggerContext context) {
	}

	@Override
	public void onLevelChange(Logger logger, Level level) {
	}

	protected String logFilePath() {
		String mode = getRunningMode();
		if (mode == null) {
			mode = "";
		}

		switch (mode) {
		case "development":
			return PathUtils.packagePath("static");
		case "staging":
			return "/var/log/world/";
		case "kdl_staging":
			return "/var/log/world/";
		case "production":
			return "/var/log/world/";
		default:
			return PathUtils.packagePath("static");
		}
	}

	protected String getMyBatisConfigPath() {
		PropertiesConfiguration config = readConfig();
		String environment = getMybatisEnviroment();

		if (StringUtils.isNotEmpty(environment)) {
			return config.getString("mybatis." + environment + ".config");
		} else {
			return config.getString("mybatis.config");
		}
	}

	protected String getRunningMode() {
		return System.getProperty("mode");
	}

	protected String getMybatisEnviroment() {
		return System.getProperty("mybatis");
	}

	protected PropertiesConfiguration readConfig() {
		try {
			return new PropertiesConfiguration("application.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}
}
