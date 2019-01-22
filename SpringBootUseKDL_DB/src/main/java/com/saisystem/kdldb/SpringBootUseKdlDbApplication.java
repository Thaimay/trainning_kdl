package com.saisystem.kdldb;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saisystem.kdldb.Model.ProjectInfo;

@MappedTypes(ProjectInfo.class)
@MapperScan("com.saisystem.kdldb.Mapper")
@SpringBootApplication
public class SpringBootUseKdlDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUseKdlDbApplication.class, args);
	}

}

