package restcontroller;

import entity.Ingredient;
import repository.IngredientRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
public class IngredientsResttController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @RequestMapping(value = "/allIngredient", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Ingredient> getAllIngredient() {
        List<Ingredient> listingredient = ingredientRepository.findAll();
        return listingredient;
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") String id) {
    	Ingredient ingredient = ingredientRepository.findById(Integer.parseInt(id));
        if(ingredient == null)
        	return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
    }

    @RequestMapping(value = "/ingredient", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Void> saveProduct(@RequestBody Ingredient p) {
//    	if(ingredientRepository.exists(p))
//    		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    	HttpHeaders headers = new HttpHeaders();
    	ingredientRepository.save(p);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Ingredient> updateProduct(@PathVariable("id") String id,@RequestBody Ingredient p) {
    	Ingredient ingredient = ingredientRepository.findById(Integer.parseInt(id));
    	if(ingredient == null){
    		return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
    	}
    	ingredient.setInventory(p.getInventory());
    	ingredient.setName(p.getName());
    	ingredient.setPrice(p.getPrcie());
    	ingredientRepository.save(ingredient);
    	
    	return new ResponseEntity<Ingredient>(ingredient, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<Ingredient> deleteProduct(@PathVariable("id") String id) {
    	Ingredient ingredient = ingredientRepository.findById(Integer.parseInt(id));
    	if(ingredient ==null){
    		return new ResponseEntity<Ingredient>(HttpStatus.NOT_FOUND);
    	}
    	ingredientRepository.deleteById(id);
    	return new ResponseEntity<Ingredient>(HttpStatus.NO_CONTENT);
    }

}
