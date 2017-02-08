package com.tanqed.sw.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tanqed.sw.controllers.services.UserServices;
import com.tanqed.sw.models.user_models.User;


/**
 * Restful server
 * 
 * Service end-points controller that maps to other controllers
 * route:  /login
 * route:  /register
 */
@RestController
public class MainController {

    @Autowired
    private UserServices userService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    public MainController(UserServices userService) {
        this.userService = userService;
    }

    // handler for sign-up root
    @PostMapping("/sign-up")
    //@Transactional(dontRollbackOn = Exception.class)
    public String signUp(@RequestParam("username")String username,
                         @RequestParam("password")String password){
        logger.info("Before Creating User: " + username + "  " + password);
        User user = userService.findUser(username);
        if (user == null){
            try{
                userService.createUser(username, password);
            }catch(MySQLIntegrityConstraintViolationException ex){
                System.out.println(ex.getMessage());
            }
            logger.info("register success.");
            return "redister success!";
        }else {
            logger.info("register fail, the username is exist.");
            return "this username is existed!";
        }


    }

    @GetMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password){
        logger.info("Before Login User: " + username + "  " + password);
        User user = userService.findUser(username);
        String psw;
        if (user == null){
            logger.info("do not find such user!");
            return "no such user!";
        }else {
            psw = user.getPassword();
            if (password.equals(psw)){
                logger.info("login success.");
                return "login success!";
            }else {
                logger.info("wrong password.");
                return "password is wrong!";
            }
        }

    }
}
