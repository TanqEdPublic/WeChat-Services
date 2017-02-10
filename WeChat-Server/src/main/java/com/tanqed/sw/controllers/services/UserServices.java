package com.tanqed.sw.controllers.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tanqed.sw.models.user_models.MongoDAO;
import com.tanqed.sw.models.user_models.MongoUser;
import com.tanqed.sw.models.user_models.User;
import com.tanqed.sw.models.user_models.UserDAO;


/* Class that performs transactions with potential user. 
 * */

@Service
public class UserServices {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MongoDAO mongoDB;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);
	
	public void createUser(String username, String password) throws MySQLIntegrityConstraintViolationException{

		logger.info("Checking paramaters: " + username + " " + password);
		User newUser = new User(username, password);
		MongoUser mUser = new MongoUser(username, password);

		logger.info("### ATTEMPTING TO SAVE NEW USER ###");

		try{
			logger.info("Quick Check on User bean: " + newUser.getId() + newUser.getUsername() + newUser.getPassword());
			
			userDAO.save(newUser);
			mongoDB.save(mUser);
		}catch(Exception exc){

			logger.error("@@@ User failed to be saved... Reason: " + exc.getMessage() + " @@@");
		}
	}
	public User findUser(String username){

		return userDAO.findByUsername(username);
	}

	public ArrayList<MongoUser> showAll(){
		
		return (ArrayList<MongoUser>) mongoDB.findAll();
	}
}
