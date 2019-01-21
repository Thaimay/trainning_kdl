package com.saisystem.springboot;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saisystem.springboot.Model.Product;


@MappedTypes(Product.class)
@MapperScan("com.saisystem.springboot.Mapper")
@SpringBootApplication
public class SpringBootAndPostgreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAndPostgreApplication.class, args);
	}

}

