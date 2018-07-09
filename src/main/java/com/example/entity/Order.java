package com.example.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Order {
	
	@Id
    private String id;
	
	Customer customer;
	List<OrderDetails> orderdetails;
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderDetails> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(List<OrderDetails> orderdetails) {
		this.orderdetails = orderdetails;
	}
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	@Override
	public String toString() {
	    return "{id: \"" + this.id + "\", customer:" + this.customer +  ", orderdetails:" + this.orderdetails + "}";
	}
}
