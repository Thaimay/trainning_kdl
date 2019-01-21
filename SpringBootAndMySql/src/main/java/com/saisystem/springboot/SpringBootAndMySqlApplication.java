package com.saisystem.springboot;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saisystem.springboot.model.Product;


@MappedTypes(Product.class)
@MapperScan("com.saisystem.springboot.mapper")
@SpringBootApplication
public class SpringBootAndMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndMySqlApplication.class, args);
	}

}

