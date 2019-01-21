package com.saisystem.springboot.controller;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saisystem.springboot.mapper.ProductMapper;
import com.saisystem.springboot.model.Product;

@RestController
@RequestMapping("/")
public class ProductController {
	
	private ProductMapper productMapper;
	
	public ProductController(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
	@GetMapping("/all")
	public List<Product> getAll(){
		
		return productMapper.fillAll();
	}
}
