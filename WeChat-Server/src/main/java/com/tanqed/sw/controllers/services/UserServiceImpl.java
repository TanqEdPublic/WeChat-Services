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


    // Method returns a status of a registration operation in String
    // Method accepts two parameters: username and password to register into DB
    @Override
    public String createUser(String username, String password) throws MySQLIntegrityConstraintViolationException {

    	// Before creating new user, we check if user already exist.
    	// Database querying is delegated to Hibernate API. 
    	// Our interface SqlDAO extends repository that implements
    	// basic CRUD operations. Read queries return POJO Entity object that
    	// is bound to SqlDAO interface.
        if (sqlDB.findByUsername(username) == null) {
            try {
            	// if user is new, save to database
                sqlDB.save(new SqlUser(username, password));
                //mongoDB.save(new MongoUser(username, password));
            } catch (Exception exc) {

            	// Need to handle other exceptions here, like invalid entry to DB,
            	// preferably on a Clients before sending Request
                logger.error("@@@ User failed to be saved... Reason: " + exc.getMessage() + " @@@");
            }
            
            logger.error("@@@ User: " + username + " is registered! @@@");
            return "registered";
        } else {
        	logger.error("@@@ User: " + username + " Already Exists @@@");
            return "deplicate_user";
        }
    } // end of createUser()

    
    // Method returns a status of login operation in String
    // Method accepts two parameters: username and password 
    // to perform checks against database.
    @Override
    public String loginUser(String username, String password) {

    	// Check if user exist in DB. Delegate this task to SqlDAO repository.
    	// Hibernate provides a method findBy___(param) to perform querying.
    	// If you want, you can easily switch to MongoDB
        if (sqlDB.findByUsername(username) == null /*&& mongoDB.findByUsername(username) == null*/) {
        	logger.info("@@@ Wrong Username! @@@");
            return "no_user";
        } else {
        	// If user exist in DB, Hibernate will return a POJO of SqlUser. 
        	// We can access it's password field via getter and compare with
        	// password sent in the request from a Client.
            if (!password.equals(sqlDB.findByUsername(username).getPassword())) {
            	logger.info("@@@ Wrong Password! @@@");
                return "wrong_pass";
            } else {
            	// if username/password pass validation, return status back to Client
            	logger.info("@@@ Logged In! @@@");
                return "logged";
            }
        }
    } // end of loginUser this method can be refactored to return a Authentication Token
    	// returned token than can be placed into response header and sent to Client.


    @Override
    public List<Object> showAll() {
        return null;
    }

}
