package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.entity.Order;
import com.example.repository.IngredientRepository;
import com.example.repository.OrderRepository;


@Controller
public class OrderController {
	
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//show page
		@RequestMapping(value = "/", method = RequestMethod.GET)
	    public String mainPage(ModelMap modelMap) {
			modelMap.addAttribute("ingredients", ingredientRepository.findAll());
	        return "home";
	    }
	
	//show page
	@RequestMapping(value = "/weborder", method = RequestMethod.GET)
    public String orderPage(ModelMap modelMap) {
		modelMap.addAttribute("ingredients", ingredientRepository.findAll());
        return "home";
    }
	
	
//	//post new one
//	@RequestMapping(value = "/addweborder", method = RequestMethod.POST)
//	public String saveOrder(@Valid @RequestBody Order order, UriComponentsBuilder ucBuilder) {
//			
//		return "redirect:/weborder";
//	}
	//get specific one
	@RequestMapping(value = "/weborder/{id}", method = RequestMethod.GET)
	public String editOrder(@PathVariable("id") String id,ModelMap modelMap) {
		System.out.println("HIHI");
		Order order = orderRepository.findById(id).get();
		if(order != null)
			
			modelMap.addAttribute("order",order);
			
		return "showorder";
	}
	
	//update specific one
	@RequestMapping(value = "/updateweborder", method = RequestMethod.POST)
	public String updateOrder() {
			
		return "redirect:/weborder";
	}
	
	//delete specific one
	@RequestMapping(value = "/deleteweborder/{id}", method = RequestMethod.GET)
	public String deleteOrder() {
			
		return "redirect:/weborder";
	}
}
