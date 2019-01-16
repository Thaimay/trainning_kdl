package com.example.springbootthymeleaf.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springbootthymeleaf.Model.Product;

@Controller
public class ProductController {

	private static List<Product> products = new ArrayList<Product>();
	
	static {
		products.add(new Product("BMV", "EU", 1000000f));
		products.add(new Product("KIA", "VN", 10000f));
		products.add(new Product("LEXUS", "USA", 323000f));
		products.add(new Product("HONDA", "JP", 545000f));
		products.add(new Product("MAZDA", "US", 154600f));
	}
	
	@Value("${welcome.message}")
	private String message;
	
	
	
	@RequestMapping(value= {"/", "/index"}, method=RequestMethod.GET)
	public String homePage(Model model) {
		model.addAttribute("message", message);
		
		return "index";
	}
	
	
	@RequestMapping(value="/listProduct", method = RequestMethod.GET)
	public String showList(Model model) {
		model.addAttribute("productList", products);
		
		return "listProduct";
	}
	
	
	
}
