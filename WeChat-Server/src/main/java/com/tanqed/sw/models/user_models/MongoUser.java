package com.tanqed.sw.models.user_models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

public class MongoUser {

	@Id
	@Getter @Setter private String id;
	@Getter @Setter private String username;
	@Getter @Setter private String password;
	
	public MongoUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
}
