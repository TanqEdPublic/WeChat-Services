/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers;

import com.tanqed.sw.model.ListOfUsers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author eduar
 */
@Component
public class LoginController {

    @FXML
    TextField userName;
    @FXML
    PasswordField password;
    @FXML
    Button loginBtn;
    @FXML
    Hyperlink registerLink;

    @Autowired
    ListOfUsers userList;
    
    private static final Logger  LOGGER = LoggerFactory .getLogger(LoginController.class);

    public LoginController() {
        LOGGER.info("Constructing Login Controller");
    }

    @FXML
    private void validateUser() {
        
        LOGGER.info("Before evaluating user");
        
        // itterate over the user list
        userList.getUserList().forEach(user -> {
            // compare user credentials against field entries
            if(user.getUsername().equals(userName.getText()) 
            && user.getPassword().equals(password.getText())){
                // if successful
                LOGGER.info("Loggin");
            }
            else{
                // if not successful
                LOGGER.info("Wrong Username or password");
            }
        });
        
    }
} // end of Controller
