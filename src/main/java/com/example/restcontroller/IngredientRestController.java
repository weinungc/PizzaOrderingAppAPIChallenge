package com.example.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.entity.Ingredient;
import com.example.repository.IngredientRepository;

@RestController
public class IngredientRestController {

	@Autowired
	private IngredientRepository ingredientRepository;

	@RequestMapping(value = "/ingredient/all", method = RequestMethod.GET)
	public ResponseEntity<List<Ingredient>> getAllIngredient() {
		List<Ingredient> listingredient = ingredientRepository.findAll();
		if (listingredient == null)
			return new ResponseEntity<List<Ingredient>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Ingredient>>(listingredient, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient", method = RequestMethod.GET)
	public Ingredient getIngredientByName(@RequestParam(value = "name") String name) {
		Ingredient ingredient = ingredientRepository.findByName(name);
		return ingredient;
	}

	@RequestMapping(value = "ingredient/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") String id) {

		Ingredient ingredient = ingredientRepository.findById(id).get();
		if (ingredient == null)
			return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Void> saveProduct(@RequestBody Ingredient p, UriComponentsBuilder ucBuilder) {

		if (p.getId() != null && p.getId().isEmpty())
			p.id = null;
		Ingredient cur_p = ingredientRepository.findByName(p.getName());
		if (cur_p != null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		ingredientRepository.save(p);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/ingredient/{id}").buildAndExpand(p.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/ingredient", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Ingredient> updateProduct(@RequestBody Ingredient p) {

		Ingredient ingredient = ingredientRepository.findById(p.getId()).get();

		if (ingredient == null) {
			return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		}
		ingredient.setInventory(p.getInventory());
		ingredient.setName(p.getName());
		ingredient.setPrice(p.getPrice());
		ingredientRepository.save(ingredient);

		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Ingredient> updateProductwithId(@PathVariable("id") String id, @RequestBody Ingredient p) {
		Ingredient ingredient = ingredientRepository.findById(id).get();

		if (ingredient == null) {
			return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		}
		ingredient.setInventory(p.getInventory());
		ingredient.setName(p.getName());
		ingredient.setPrice(p.getPrice());
		ingredientRepository.save(ingredient);

		return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
	}

	@RequestMapping(value = "/ingredient/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Ingredient> deleteProduct(@PathVariable("id") String id) {
		Ingredient ingredient = ingredientRepository.findById(id).get();
		if (ingredient == null) {
			return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
		}
		ingredientRepository.deleteById(id);
		return new ResponseEntity<Ingredient>(HttpStatus.NO_CONTENT);
	}

}
