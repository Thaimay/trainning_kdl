package com.saisystem.kdldb.Model;

public class ProjectInfo {
	
	private int id;
	private String buildingName;
	private String shopName;
	public ProjectInfo() {
		
	}
	public ProjectInfo(int id, String building_name, String shop_name) {
		this.id = id;
		this.buildingName = building_name;
		this.shopName = shop_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName_building() {
		return buildingName;
	}
	public void setName_building(String name_building) {
		this.buildingName = name_building;
	}
	public String getName_i_shop() {
		return shopName;
	}
	public void setName_i_shop(String name_i_shop) {
		this.shopName = name_i_shop;
	}
	
	

}
