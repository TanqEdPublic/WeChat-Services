package com.tanqed.sw.controllers;

import com.tanqed.sw.controllers.services.UserServices;
import com.tanqed.sw.models.user_models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Restfull server
 * 
 * Service end-points controller that maps to other controllers
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
    public String signUp(@RequestParam("username")String username,
                         @RequestParam("password")String password) throws Exception{
        logger.info("Before Creating User: " + username + password);

        userService.createUser(username, password);
        logger.info("about to return to index");
        return "index";
    }

    @GetMapping("/find")
    public User findUser(@RequestParam("username")String username){
        return userService.findUser(username);
    }
}
