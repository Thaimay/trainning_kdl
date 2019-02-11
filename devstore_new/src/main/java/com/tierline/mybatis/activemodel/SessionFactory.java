package com.tierline.mybatis.activemodel;

import org.apache.ibatis.session.SqlSession;

public interface SessionFactory {

	SqlSession openSession();
}
