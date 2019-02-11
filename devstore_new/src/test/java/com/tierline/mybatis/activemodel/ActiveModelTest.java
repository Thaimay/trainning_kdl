package com.tierline.mybatis.activemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.tierline.mybatis.activemodel.model.Blog;
import com.tierline.mybatis.activemodel.model.BlogRepository;

public class ActiveModelTest extends MyBatisExsampleTestSupport {

	// @Test
	public void create() {
		Blog blog = new Blog("user name", "test title", "test content!!");
		blog.create();
		assertNotEquals(Long.valueOf(0), blog.getId());

		Optional<Blog> blogOption = new BlogRepository().findById(blog.getId());

		if (blogOption.isPresent()) {
			Blog createdBlog = blogOption.get();
			assertNotEquals(Long.valueOf(0), blog.getId());
			assertEquals(createdBlog.getUserName(), blog.getUserName());
			assertEquals(createdBlog.getTitle(), blog.getTitle());
			assertEquals(createdBlog.getContent(), blog.getContent());
			assertEquals(createdBlog.getCreateDate(), blog.getCreateDate());
			assertEquals(createdBlog.getUpdateDateTime(), blog.getUpdateDateTime());
		} else {
			fail();
		}
	}

	// @Test
	public void delete() {
		Blog blog = new Blog("user name", "test title", "test content!!");
		blog.create();

		Optional<Blog> blogOption = new BlogRepository().findById(blog.getId());

		if (blogOption.isPresent()) {
			Blog createdBlog = blogOption.get();
			assertEquals(createdBlog.getUserName(), blog.getUserName());
			assertEquals(createdBlog.getTitle(), blog.getTitle());
			assertEquals(createdBlog.getContent(), blog.getContent());
			assertEquals(createdBlog.getCreateDate(), blog.getCreateDate());
			assertEquals(createdBlog.getUpdateDateTime(), blog.getUpdateDateTime());
		} else {
			fail();
		}

		blog.delete();

		Optional<Blog> blogDeleteOption = new BlogRepository().findById(blog.getId());

		if (blogDeleteOption.isPresent()) {
			fail();
		} else {
			assertEquals(true, true);
		}
	}

	// @Test
	public void update() {

		Blog blog = new Blog("user name", "test title", "test content!!");
		blog.create();

		Optional<Blog> blogOption = new BlogRepository().findById(blog.getId());

		if (blogOption.isPresent()) {
			Blog createdBlog = blogOption.get();
			createdBlog.setUserName("up name");
			createdBlog.setTitle("update-title");
			createdBlog.setContent("update-content");
			LocalDate updateDate = LocalDate.now();
			createdBlog.setCreateDate(updateDate);
			LocalDateTime updateDateTime = LocalDateTime.now();
			createdBlog.setUpdateDateTime(updateDateTime);

			createdBlog.update();

			Optional<Blog> blogUpdateOption = new BlogRepository().findById(createdBlog.getId());

			if (blogUpdateOption.isPresent()) {
				Blog updateBlog = blogUpdateOption.get();

				assertEquals(updateBlog.getUserName(), "up name");
				assertEquals(updateBlog.getTitle(), "update-title");
				assertEquals(updateBlog.getContent(), "update-content");
				assertEquals(updateBlog.getCreateDate(), updateDate);
				assertEquals(updateBlog.getUpdateDateTime(), updateDateTime);
			} else {
				fail();
			}
		} else {
			fail();
		}
	}

	// @Test
	public void createThenUpdate() {
		Blog blog = new Blog("user name", "test title", "test content!!");
		blog.create();
		assertNotEquals(Long.valueOf(0), blog.getId());
		blog.setUserName("up name");
		blog.setTitle("update-title");
		blog.setContent("update-content");
		LocalDate updateDate = LocalDate.now();
		blog.setCreateDate(updateDate);
		LocalDateTime updateDateTime = LocalDateTime.now();
		blog.setUpdateDateTime(updateDateTime);

		blog.update();

		Optional<Blog> blogOption = new BlogRepository().findById(blog.getId());

		if (blogOption.isPresent()) {
			Blog updateBlog = blogOption.get();
			assertEquals(updateBlog.getUserName(), "up name");
			assertEquals(updateBlog.getTitle(), "update-title");
			assertEquals(updateBlog.getContent(), "update-content");
			assertEquals(updateBlog.getCreateDate(), updateDate);
			assertEquals(updateBlog.getUpdateDateTime(), updateDateTime);
		} else {
			fail();
		}
	}

}
