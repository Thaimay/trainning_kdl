package com.saisystem.springboot.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saisystem.springboot.Mapper.ProductMapper;
import com.saisystem.springboot.Model.Product;

@Controller
public class ProductController {
	
	@Value("${header.message}")
	private String header;
	
	@Value("${welcome.message}")
	private String welcome;
	
	@Value("${error.message}")
	private String error;

	private ProductMapper proMapper;
	
	public ProductController(ProductMapper productMapper) {
		this.proMapper = productMapper;
	}
	
	@RequestMapping(value= {"/","/index"}, method = RequestMethod.GET)
	public String getHome(Model model) {
		List<Product> productList = this.proMapper.fillAll();
		
		model.addAttribute("header", header);
		model.addAttribute("welcome", welcome);
		model.addAttribute("productList", productList);
	
		return "index";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String editPage(Model model, @RequestParam int id) {
		model.addAttribute("header", header);
		model.addAttribute("welcome", welcome);
		Product getProduct = new Product();
		getProduct = this.proMapper.getProductById(id);
		
		model.addAttribute("product", getProduct);
		
		return "editPage";
	}
	
	
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String updateProduct(Model model,
			@ModelAttribute("product") Product product) {
		
		int id = product.getId();
		String name = product.getFullname();
		float price = product.getPrice();
		String producer = product.getProducer();
		
		if(name != "" && price < 0 && producer != "") {
			Product upProduct = new Product(id, name, price, producer);
			proMapper.updateProduct(upProduct);
		
			return "redirect:/index";
		}
		
		model.addAttribute("error", error);
		return "editPage";
	}
	
	@RequestMapping(value="/insertProduct", method = RequestMethod.GET)
	public String inserPage(Model model) {
		Product productForm = new Product();
		model.addAttribute("header", header);
		model.addAttribute("welcome", welcome);
		model.addAttribute("productInsert", productForm);
		return "insertProduct";
	}
	
	
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public String insertData(Model model,
			@ModelAttribute("productInsert") Product productInsert) {
		
		String name = productInsert.getFullname();
		float price = productInsert.getPrice();
		String producer = productInsert.getProducer();
		
		if(name != "" && price < 0 && producer != "") {
			Product newProduct = new Product(name, price, producer);
			proMapper.insertProduct(newProduct);
		
			return "redirect:/";
		}
		
		model.addAttribute("error", error);
		return "insertProduct";
	}
}
