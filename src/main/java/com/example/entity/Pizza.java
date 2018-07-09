package com.example.entity;

import java.util.List;

public class Pizza {
	String size;
	String base;
	String sauce;
	List<String> ingredients;
	double price;
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getSauce() {
		return sauce;
	}
	public void setSauce(String sauce) {
		this.sauce = sauce;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
	    return "{size: " + this.size + ", base:\"" + this.base +  "\", sauce:\"" + this.sauce  + "\", price:\"" + this.price+ "\", ingredients:" + this.ingredients+"}";
	}
		

}
