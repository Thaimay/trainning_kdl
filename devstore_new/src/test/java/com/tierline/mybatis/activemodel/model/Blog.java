package com.tierline.mybatis.activemodel.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;

import com.tierline.mybatis.activemodel.ModelMapper;

public class Blog extends jp.co.world.storedevelopment.model.ActiveModel<Blog> {

	private String title;

	private String content;

	private String userName;

	private LocalDate createDate;

	private LocalDateTime updateDateTime;

	@Override
	protected ModelMapper<Blog> modelMapper(SqlSession session) {
		return session.getMapper(BlogModelMapper.class);
	}

	public Blog() {
	}

	public Blog(Long id, String userName, String title, String content) {
		this.id = id;
		this.userName = userName;
		this.title = title;
		this.content = content;
		createDate = LocalDate.now();
		updateDateTime = LocalDateTime.now();
	}

	public Blog(String userName, String title, String content) {
		this(0L, userName, title, content);
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDate) {
		updateDateTime = updateDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
