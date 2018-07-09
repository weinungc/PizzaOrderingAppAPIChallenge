package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
	Order findById(int id);
	
	@Query("{'customer.name': ?0}")
	List<Order> findByCustomerName(String name);

}
