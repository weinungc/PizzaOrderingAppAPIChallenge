package com.example.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.entity.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String>{
	Ingredient findByName(String name);

//	Ingredient findOne(String id);
	
	

	//Ingredient fineOne(String id);
	
}
