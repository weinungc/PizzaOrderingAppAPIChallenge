package repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import entity.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String>{
	Ingredient findByName(String name);

	Ingredient findById(int id);
	
}
