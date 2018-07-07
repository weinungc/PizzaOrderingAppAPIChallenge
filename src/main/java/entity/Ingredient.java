package entity;

import org.springframework.data.annotation.Id;

public class Ingredient {
	@Id
    public String id;
	
	public String name;
	public int inventory;
	public double price;
//	boolean basicingredient;
	
//	public Ingredient(String name , int inventory, double price, boolean basicingredient) {
//		this.name = name;
//		this.inventory = inventory;
//		this.price = price;
//		this.basicingredient = basicingredient;
//	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setInventory (int inventory) {
		this.inventory = inventory;
	}
	public int getInventory() {
		return inventory;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrcie() {
		return inventory;
	}
//	public void SetBasicIngredient(boolean basicingredient) {
//		this.basicingredient = basicingredient;
//	}
//	public boolean getBasicIngredient() {
//		return basicingredient;
//	}
	
}
