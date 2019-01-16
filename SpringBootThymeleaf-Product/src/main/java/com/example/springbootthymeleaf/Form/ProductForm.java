package com.example.springbootthymeleaf.Form;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductForm {
	private String name;
	private String made;
	private float price;
	private String date;
	
	public ProductForm() {

	}
	
	SimpleDateFormat spdate = new SimpleDateFormat("dd/MM/YYY");
	
	public ProductForm(String name, String made, float price, String date  ) {
		this.name = name;
		this.made = made;
		this.price = price;
		this.date = date;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMade() {
		return made;
	}
	public void setMade(String made) {
		this.made = made;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
