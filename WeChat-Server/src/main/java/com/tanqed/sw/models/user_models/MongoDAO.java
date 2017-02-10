package com.tanqed.sw.models.user_models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDAO extends MongoRepository<MongoUser, String>{

	public MongoUser findByUsername(String username);
}
