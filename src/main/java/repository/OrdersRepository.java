package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import entity.Order;

public interface OrdersRepository extends MongoRepository<Order, String> {
	Order findById(int id);
	
	@Query("{'customer.name': ?0}")
	List<Order> findByCustomerName(String name);

}
