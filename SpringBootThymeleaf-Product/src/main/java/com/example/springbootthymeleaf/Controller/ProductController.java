package com.example.springbootthymeleaf.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springbootthymeleaf.Form.ProductForm;
import com.example.springbootthymeleaf.Model.Product;

@Controller
public class ProductController {

	private static List<Product> products = new ArrayList<Product>();


	static {
		products.add(new Product("BMV", "EU", 1000000f, "2015-01-01"));
		products.add(new Product("KIA", "VN", 10000f, "2016-06-18"));
		products.add(new Product("LEXUS", "USA", 323000f, "2018-09-13"));
		products.add(new Product("HONDA", "JP", 545000f, "2018-09-25"));
		products.add(new Product("MAZDA", "US", 154600f, "2019-01-01"));
	}
	
	@Value("${welcome.message}")
	private String message;
	
	@Value("${error.message}")
	private String error;
	
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
	
	@RequestMapping(value="/insertProduct", method=RequestMethod.GET)
	public String insertPage(Model model) {
		ProductForm productForm = new ProductForm();
		model.addAttribute("productForm", productForm);
		return "insertProduct";
	}
	
	@RequestMapping(value="/listProduct", method=RequestMethod.POST)
	public String submitProduct(Model model,
			@ModelAttribute("productForm") ProductForm productForm) {
		
		String name = productForm.getName();
		String made = productForm.getMade();
		float price = productForm.getPrice();
		String date = productForm.getDate();
		
		if(name != null && made != null && price > 0) {
			Product newProduct = new Product(name, made, price, date);
			products.add(newProduct);
			
			return "redirect:/listProduct";
		}
		
		model.addAttribute("error", error);
		return "insertProduct";
	}
	
}
