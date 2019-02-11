package com.tierline.mybatis.activemodel;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import com.tierline.mybatis.activemodel.model.Blog;
import com.tierline.mybatis.activemodel.model.BlogRepository;

public class RepositoryTest extends MyBatisExsampleTestSupport {

	// @Test
	public void findBy() {
		Blog blog = new Blog("user name", "test title", "test content!!");
		blog.create();
		assertNotEquals(Long.valueOf(0), blog.getId());

		Optional<Blog> blogOption = new BlogRepository().findById(blog.getId());
		assertTrue(blogOption.isPresent());
	}

}
