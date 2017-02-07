package com.tanqed.sw.controllers.services;

import com.tanqed.sw.models.user_models.User;
import com.tanqed.sw.models.user_models.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* Class that performs transactions with potential user. 
 * */

@Service
public class UserServices {

	@Autowired
	private UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);
	
	// @Transactional // used here
	@Transactional(noRollbackFor = Exception.class)
	public void createUser(String username, String password){

		logger.info("Checking paramaters: " + username + " " + password);
		User newUser = new User(username, password);

		logger.info("### ATTEMPTING TO SAVE NEW USER ###");

		try{
			logger.info("Quick Check on User bean: " + newUser.getId() + newUser.getUsername() + newUser.getPassword());
			userDAO.save(newUser);
		}catch(Exception exc){

			exc.printStackTrace();
			//logger.error("This is CAUSE: " );
			logger.error("@@@ User failed to be saved... Reason: " + exc.getMessage() + " @@@");
		}
	}

}
