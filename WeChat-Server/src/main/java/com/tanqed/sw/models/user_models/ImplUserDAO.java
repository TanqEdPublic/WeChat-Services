package com.tanqed.sw.models.user_models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/* Class that encapsulates operations against database  */

// @Component
public class ImplUserDAO {

	@Autowired
	private UserDAO userDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ImplUserDAO.class);
	
	
}
