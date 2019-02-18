package jp.co.world.storedevelopment.logging.db;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.DefaultSessionFactory;

import ch.qos.logback.core.db.DataSourceConnectionSource;

public class AccessDBConnectionSource extends DataSourceConnectionSource {
	@Override
	public void start() {
		try (SqlSession session = DefaultSessionFactory.getInstance().openSession()) {
			if(session != null) {
				setDataSource(session.getConfiguration().getEnvironment().getDataSource());
				super.start();
			}
		}
	}
}
