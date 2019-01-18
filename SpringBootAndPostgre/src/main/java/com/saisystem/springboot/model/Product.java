package com.saisystem.springboot.model;

public class Product {
	private int id;
	private String name;
	private float price;
	private String producer;
	public Product() {
		
	}
	public Product(int id, String name, float price, String producer) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.producer = producer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "[ id = "+ this.getId()+ ", name = "+this.getName()+", price = "+this.getPrice() + ", producer = "+ this.getProducer()+"]";
	}
}
