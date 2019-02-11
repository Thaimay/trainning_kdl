package com.tierline.mybatis.activemodel.model;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ActiveModelStringUtils;
import com.tierline.mybatis.activemodel.Repository;

public class BlogRepository extends Repository<Blog, BlogRepositoryMapper> {

	@Override
	protected BlogRepositoryMapper repositoryMapper(SqlSession session) {
		return session.getMapper(BlogRepositoryMapper.class);
	}

	@Override
	public String tableName() {
		return ActiveModelStringUtils.toTableName(Blog.class);
	}

}
