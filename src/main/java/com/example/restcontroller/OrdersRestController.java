package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.entity.Ingredient;
import com.example.entity.Order;
import com.example.entity.OrderDetails;
import com.example.repository.IngredientRepository;
import com.example.repository.OrderRepository;

@RestController
public class OrdersRestController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private IngredientRepository ingredientRepository;
	// private ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/order/all", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		if (orders == null)
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
		Order order = orderRepository.findById(id).get();
		if (order == null)
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order, UriComponentsBuilder ucBuilder) {

		// valdiation(check for enough inventory)
		Map<String, Integer> count = new HashMap<String, Integer>();
		for (OrderDetails od : order.getOrderdetails()) {
			int qty = od.getQty();
			for (String ig : od.getPizza().getIngredients()) {
				if (count.containsKey(ig)) {
					count.put(ig, count.get(ig) + qty);
				} else {
					count.put(ig, qty);
				}
			}
		}
		for (Map.Entry<String, Integer> entry : count.entrySet()) {
			String igName = entry.getKey();
			int qtyNeed = entry.getValue();

			Ingredient ing = ingredientRepository.findByName(igName);
			if (ing == null || qtyNeed > ing.inventory) {
				// error not enough inventory
				return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
			}
		}
		// pass
		orderRepository.save(order);
		// deduct inventory
		for (Map.Entry<String, Integer> entry : count.entrySet()) {
			String igName = entry.getKey();
			int qtyNeed = entry.getValue();

			Ingredient ing = ingredientRepository.findByName(igName);
			ing.setInventory(ing.getInventory() - qtyNeed);
			ingredientRepository.save(ing);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri());
		return new ResponseEntity<Order>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Order> updateOrder(@PathVariable("id") String id, @RequestBody Order order) {
		Order cur_order = orderRepository.findById(id).get();

		if (cur_order == null)
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);

		Map<String, Integer> count = new HashMap<String, Integer>();

		// deduct curr ingredient
		for (OrderDetails od : cur_order.getOrderdetails()) {
			int qty = od.getQty();
			for (String ig : od.getPizza().getIngredients()) {
				
				if (count.containsKey(ig)) {
					count.put(ig, count.get(ig) - qty);
				} else {
					count.put(ig, -qty);
				}
			}
		}
		// add new ingredient and see how much we need now
		for (OrderDetails od : order.getOrderdetails()) {
			int qty = od.getQty();
			for (String ig : od.getPizza().getIngredients()) {
				if (count.containsKey(ig)) {
					count.put(ig, count.get(ig) + qty);
				} else {
					count.put(ig, qty);
				}
			}
		}
		for (Map.Entry<String, Integer> entry : count.entrySet()) {
			String igName = entry.getKey();
			int qtyNeed = entry.getValue();

			Ingredient ing = ingredientRepository.findByName(igName);
			if (ing == null || qtyNeed > ing.inventory) {
				// error not enough inventory
				return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
			}
		}
		// pass
		cur_order.setCustomer(order.getCustomer());
		cur_order.setOrderdetails(order.getOrderdetails());
		orderRepository.save(cur_order);

		// deduct inventory
		for (Map.Entry<String, Integer> entry : count.entrySet()) {
			String igName = entry.getKey();
			int qtyNeed = entry.getValue();

			Ingredient ing = ingredientRepository.findByName(igName);
			ing.setInventory(ing.getInventory() - qtyNeed);
			ingredientRepository.save(ing);
		}

		return new ResponseEntity<Order>(cur_order, HttpStatus.OK);
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Order> deleteOrder(@PathVariable("id") String id, @RequestBody Order order) {
		Order cur_order = orderRepository.findById(id).get();

		if (cur_order == null)
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		orderRepository.deleteById(id);
		return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
	}

}
