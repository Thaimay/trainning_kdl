package com.saisystem.springboot.model;

public class Product {
	private int id;
	private String fullname;
	private float price;
	private String producer;
	public Product() {
		
	}
	public Product(int id, String fullname, float price, String producer) {
		this.id = id;
		this.fullname = fullname;
		this.price = price;
		this.producer = producer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", fullname=" + fullname + ", price=" + price + ", producer=" + producer + "]";
	}
	
	
	
	

}
