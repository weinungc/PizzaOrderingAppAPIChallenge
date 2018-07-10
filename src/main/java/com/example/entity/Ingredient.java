package com.example.entity;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;

public class Ingredient {
	@Id
    public String id;
	
	@NotNull
	public String name;
	@NotNull
	@PositiveOrZero
	public int inventory;
	@NotNull
	@PositiveOrZero
	public double price;	
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getInventory() {
		return inventory;
	}



	public void setInventory(int inventory) {
		this.inventory = inventory;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	@Override
	public String toString() {
	    return "{id: \"" + this.id + "\", name:\"" + this.name +  "\", inventory:" + this.inventory  + ", price:" + this.price+"}";
	}
	
}
