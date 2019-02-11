package jp.co.world.storedevelopment;

import org.apache.commons.lang.StringUtils;

import com.tierline.mybatis.activemodel.SessionFactory;
import com.tierline.mybatis.activemodel.SessionFactorySupoort;

public class WoldAuthSessionFactory extends SessionFactorySupoort implements SessionFactory {

	private static class SingletonHolder {
		private static final WoldAuthSessionFactory INSTANCE = new WoldAuthSessionFactory();
	}

	public static WoldAuthSessionFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private WoldAuthSessionFactory() {
		// singleton pattern
		super();
	}

	@Override
	protected String getRunningMode() {
		String mode = super.getRunningMode();
		if (StringUtils.isEmpty(mode)) {
			return mode;
		}
		switch (mode) {
		case "production":
			return "world_auth_production";
		case "local_test":
			return "world_auth_test";
		default:
			return mode;
		}
	}
}
