package com.tanqed.sw.models.user_models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/* Class that encapsulates operations against database  */

@Repository
public class ImplUserDAO {

	@Autowired
	private UserDAO userDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ImplUserDAO.class);
	
	public void addUser(String username, String password){
		User newUser = new User(username, password);
		
		logger.info("##### Saving new user... #####");
		userDAO.save(newUser);
	}
	
}
