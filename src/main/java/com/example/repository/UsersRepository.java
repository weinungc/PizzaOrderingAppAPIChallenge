package com.example.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.entity.User;

public interface UsersRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
