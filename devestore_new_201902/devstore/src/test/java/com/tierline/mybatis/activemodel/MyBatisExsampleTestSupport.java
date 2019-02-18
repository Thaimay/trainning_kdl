package com.tierline.mybatis.activemodel;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MyBatisExsampleTestSupport {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("mybatis", "activemodel");
		System.setProperty("mode", "h2");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	protected final Logger logger = LoggerFactory.getLogger(MyBatisExsampleTestSupport.class);

	protected static String dropBlogSQL = "DROP TABLE IF EXISTS blog";

	protected static String createBlogSQL = "CREATE TABLE blog "
			+ "(id serial primary key, title TEXT NOT NULL, user_name Varchar(16), content TEXT, create_date Date, update_date_time TIMESTAMP);";

	protected static String showTable = "show columns from blog;";

	protected static String mode = "";

	public void init() throws Exception {
		SqlSession session = DefaultSessionFactory.getInstance().openSession();
		DBToolMapper db = session.getMapper(DBToolMapper.class);
		db.query(dropBlogSQL);
		db.query(createBlogSQL);
		// db.query(showTable);
		session.close();
	}

}