package com.tanqed.sw.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tanqed.sw.controllers.services.UserServices;
import com.tanqed.sw.models.user_models.MongoUser;
import com.tanqed.sw.models.user_models.User;


/**
 * Restful server
 * 
 * Service end-points controller that maps to other controllers
 * route:  /login
 * route:  /register
 * route: /users
 */
@RestController
public class MainController {

	// Spring Boot injecting beans through @Autowired annotation
    @Autowired
    private UserServices userService;

    // Server console logger
    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    // handler for sign-up root, excepts parameters from URI and stores as a java variable
    @PostMapping("/sign-up")
    public String signUp(@RequestParam("username")String username,
                         @RequestParam("password")String password){
        logger.info("Before Creating User: " + username + "  " + password);
        
        User user = userService.findUser(username); // find user from MySql
        MongoUser mUser = userService.findMongoUser(username);  // find user in MongoDB
        if (user == null || mUser == null){ // check if users exist
            try{
                userService.createUser(username, password); // Delegate creation of a new user to user services class
            }catch(MySQLIntegrityConstraintViolationException ex){
                System.out.println(ex.getMessage());
            }
            logger.info("register success.");
            return "register success!";
        }else {
            logger.info("register fail, the username is exist.");
            return "this username is existed!";
        }


    }

    /* Login end point. Requests parameters from URI, i.e.,  ../login?username=admin&password=123 */ 
    @GetMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password){
        logger.info("Before Login User: " + username + "  " + password); // log to server console
        
        User user = userService.findUser(username); // mysql jdbc search
        MongoUser mongoUser = userService.findMongoUser(username); // mongodb search
        String psw, mpsw;  
        if (user == null && mongoUser == null){
            logger.info("do not find such user!");
            return "no such user!";
        }else {
            psw = user.getPassword(); // get password of mysql user object
            mpsw = mongoUser.getPassword(); // get password of mongodb user object
            if (password.equals(psw) && password.equals(mpsw)){ // check if given passwords match
                logger.info("login success.");
                return "login success!";
            }else {
                logger.info("wrong password.");
                return "password is wrong!";
            }
        }
        
        /* Consider to move away business/service logic away from RestController
         * Whose only purpose is to act as an end-points trigger and events shooter. 
         * e.g., checks on user and password. Do not expose model beans class in RestController*/

    } // end of /login end point
    
    /* Find all users and return as a list. Jackson parser than parse it to json */
    @RequestMapping("/users")
    public List<MongoUser> allUsers(){
    	return userService.showAll();
    }
} // end of class
