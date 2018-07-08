package restcontroller;



import repository.IngredientRepository;
import repository.OrdersRepository;
import entity.Ingredient;
import entity.Order;
import entity.OrderDetails;

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


@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private IngredientRepository ingredientRepository;
    //private ObjectMapper mapper = new ObjectMapper();
    
    @RequestMapping(value = "/order", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Order>> getAllOrders(){
    	List<Order> orders = ordersRepository.findAll();
    	if(orders == null){
    		return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
    	}
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
    	Order order = ordersRepository.findById(Integer.parseInt(id));
    	if(order == null)
    		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody Order order,UriComponentsBuilder ucBuilder){
    	
    	//valdiation(check for inventory)
    	Map<String, Integer> count = new HashMap<String, Integer>();
    	for(OrderDetails od: order.getOrderdetails()){
    		for(String ig: od.getPizza().getIngredients()){
    			if(count.containsKey(ig)){
    				count.put(ig, count.get(ig)+1);
    			}else{
    				count.put(ig,1);
    			}
    		}
    	}
    	
    	for(Map.Entry<String, Integer> entry : count.entrySet()) {
    	    String key = entry.getKey();
    	    int value = entry.getValue();
    	    
    	    Ingredient ing = ingredientRepository.findByName(key);
    	    if(ing==null || value>ing.inventory){
    	    	//error not enough inventory
    	    	return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
    	    }
    	}
    	//pass
    	ordersRepository.save(order);
    	//deduct inventory
    	for(Map.Entry<String, Integer> entry : count.entrySet()) {
    	    String key = entry.getKey();
    	    int value = entry.getValue();
    	    
    	    Ingredient ing = ingredientRepository.findByName(key);
    	    ing.setInventory(ing.getInventory()-value);
    	    ingredientRepository.save(ing);
    	}
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri());
    	return new ResponseEntity<Order>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrder(@PathVariable("id") String id,@RequestBody Order order){
    	Order cur_order = ordersRepository.findById(Integer.parseInt(id));
    	
    	if(cur_order == null)
    		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    	
    	Map<String, Integer> count = new HashMap<String,Integer>();
    	
    	//deduct curr ingredient
    	for(OrderDetails od: cur_order.getOrderdetails()){
    		for(String ig: od.getPizza().getIngredients()){
    			if(count.containsKey(ig)){
    				count.put(ig, count.get(ig)-1);
    			}else{
    				count.put(ig,-1);
    			}
    		}
    	}
    	//add new ingredient and see how much we need now
    	for(OrderDetails od: order.getOrderdetails()){
    		for(String ig: od.getPizza().getIngredients()){
    			if(count.containsKey(ig)){
    				count.put(ig, count.get(ig)+1);
    			}else{
    				count.put(ig,1);
    			}
    		}
    	}
    	for(Map.Entry<String, Integer> entry : count.entrySet()) {
    	    String key = entry.getKey();
    	    int value = entry.getValue();
    	    
    	    Ingredient ing = ingredientRepository.findByName(key);
    	    if(ing==null || value>ing.inventory){
    	    	//error not enough inventory
    	    	return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
    	    }
    	}
    	//pass
    	cur_order.setCustomer(order.getCustomer());
    	cur_order.setOrderdetails(order.getOrderdetails());
    	ordersRepository.save(cur_order);
    	
    	//deduct inventory
    	for(Map.Entry<String, Integer> entry : count.entrySet()) {
    	    String key = entry.getKey();
    	    int value = entry.getValue();
    	    
    	    Ingredient ing = ingredientRepository.findByName(key);
    	    ing.setInventory(ing.getInventory()-value);
    	    ingredientRepository.save(ing);
    	}
    	
    	return new ResponseEntity<Order>(cur_order, HttpStatus.OK);
    }
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") String id,@RequestBody Order order){
    	Order cur_order = ordersRepository.findById(Integer.parseInt(id));
    	
    	if(cur_order == null)
    		return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    	ordersRepository.deleteById(id);
    	return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
    
}
