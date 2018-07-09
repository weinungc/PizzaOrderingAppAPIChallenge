package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.repository.IngredientRepository;


@Controller
public class OrderController {
	
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	//show page
	@RequestMapping(value = "/weborder", method = RequestMethod.GET)
    public String orderPage(ModelMap modelMap) {
		modelMap.addAttribute("ingredients", ingredientRepository.findAll());
        return "home";
    }
	//post new one
	@RequestMapping(value = "/addweborder", method = RequestMethod.POST)
	public String saveOrder() {
			
		return "redirect:/weborder";
	}
	//get specific one
	@RequestMapping(value = "/editweborder/{id}", method = RequestMethod.GET)
	public String editOrder() {
			
		return "weborder";
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
