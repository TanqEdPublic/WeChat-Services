package com.tanqed.sw.controllers.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tanqed.sw.models.user_models.MongoDAO;
import com.tanqed.sw.models.user_models.MongoUser;
import com.tanqed.sw.models.user_models.SqlDAO;
import com.tanqed.sw.models.user_models.SqlUser;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private MongoDAO mongoDB;
	@Autowired
	private SqlDAO sqlDB;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	// Create new user for MySql and Mongo databases;
	@Override
	public String createUser(String username, String password) throws MySQLIntegrityConstraintViolationException {
		
		// Check if db entry with this username already exist
		
/*         if( user doesn't exist){
			  try{
			  		// add user
			  } catch(){}
		   } else { user exist, return message, don't register }                         */
		
		return null;
	} // end of createUser()

	// Process login
	@Override
	public String loginUser(String username, String password) {
		
		// First, check if user exists
/*		if( user exist){
 * 			// check for password
 * 			if(password correct){
 * 				allow to login
 * 			} else { incorrect password }
 * 		} else { user doesn't exist }
 
 * */
		return null;
	}

	@Override
	public List<Object> showAll() {
		return null;
	}
	
}
