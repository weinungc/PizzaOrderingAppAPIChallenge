package entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import javax.persistence.OneToMany;

public class Order {
	
	@Id
    private Long id;
	
	Customer customer;
	List<Pizza> pizzas;
	@OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderdetails = new HashSet<>();
	
	
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public String getAddress() {
//		return address;
//	}
//	
//	public void setPhoneNumber(String phonenumber) {
//		this.phonenumber = phonenumber;
//	}
//	
//	public String getPhoneNumber() {
//		return phonenumber;
//	}
	
	

}
