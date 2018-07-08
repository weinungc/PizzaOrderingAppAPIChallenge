package entity;


public class OrderDetails {

	
//	@Id
//    private Long id;
	Pizza pizza;
    private double price;
    private int qty;

    public OrderDetails() {
    }

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}


}
