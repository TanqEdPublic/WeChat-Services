package com.tanqed.sw.controllers;

import com.tanqed.sw.controllers.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Restfull server
 * 
 * Service end-points controller that maps to a controller
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
    @RequestMapping("/sign-up")
    public String signUp(@RequestParam("username")String username,
                         @RequestParam("password")String password) throws Exception{
        logger.info("Before Creating User: " + username + password);

        userService.createUser(username, password);
        logger.info("about to return to index");
        return "index";
    }
}
