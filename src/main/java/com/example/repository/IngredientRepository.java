package com.example.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.entity.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String>{
	Ingredient findByName(String name);	
}
