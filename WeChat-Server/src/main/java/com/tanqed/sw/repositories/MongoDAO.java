package com.tanqed.sw.repositories;

import com.tanqed.sw.models.message_models.Message;
import com.tanqed.sw.models.user_models.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDAO extends MongoRepository<Message, String>{

	//public MongoUser findByUsername(String username);
    public Message findByRoom(String room);
}
