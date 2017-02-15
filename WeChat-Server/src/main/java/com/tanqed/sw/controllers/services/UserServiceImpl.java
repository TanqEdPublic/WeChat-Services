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

@Service("LRImpl")
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

//         if( user doesn't exist){
//			  try{
//			  		// add user
//			  } catch(){}
//		   } else { user exist, return message, don't register }

//      this should be like this:
//		if (sqlDB.findByUsername(username) == null && mongoDB.findByUsername(username) == null){
//      but for the first time running(there is no user table in mysql) should change to this:
        if (mongoDB.findByUsername(username) == null) {
            try {
                sqlDB.save(new SqlUser(username, password));
                mongoDB.save(new MongoUser(username, password));
            } catch (Exception exc) {

                logger.error("@@@ User failed to be saved... Reason: " + exc.getMessage() + " @@@");
            }
            return "register success!";
        } else {
            logger.error("ERROR: user exist!");
            return "ERROR: user exist!";
        }
    } // end of createUser()

    // Process login
    @Override
    public String loginUser(String username, String password) {

        // First, check if user exists
//		if( user exist){
//  			// check for password
//  			if(password correct){
//  				allow to login
//  			} else { incorrect password }
//  		} else { user doesn't exist }
        if (sqlDB.findByUsername(username) == null && mongoDB.findByUsername(username) == null) {
            return "no such user !";
        } else {
            if (!password.equals(sqlDB.findByUsername(username).getPassword())) {
                return "Password is wrong !";
            } else {
                return "login success !";
            }
        }
    }


    @Override
    public List<Object> showAll() {
        return null;
    }

}
