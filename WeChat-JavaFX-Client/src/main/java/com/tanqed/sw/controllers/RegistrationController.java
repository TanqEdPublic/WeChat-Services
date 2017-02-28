package com.tanqed.sw.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.tanqed.sw.model.ListOfUsers;
import com.tanqed.sw.model.User;
import javafx.fxml.FXML;
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
public class RegistrationController{


    @FXML TextField userName;
    @FXML PasswordField password;
    @FXML PasswordField repeatPass;
    
    @Autowired private ListOfUsers userList;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    
    public RegistrationController() {
        LOGGER.info("##### Constructing registration controller! #####");
    }
    
    @FXML
    public void registerUser(){
        LOGGER.info("##### Entered registration listener event! #####");
        
        // Validate fields 
        if (password.getText().equals(repeatPass.getText())) {
            LOGGER.info("Passwords match");
        } else {
            LOGGER.info("Password does not match! Exiting...");
            return;
        }
        
        
        
        // check if submitted user already exists
        userList.getUserList().forEach(user -> {
            // do an action for each user in the list
            if (user.getUsername().equals(userName.getText())) {
                // user name already exist
                LOGGER.info("User already exist! Exiting...");
                return;
            } else {
                // validate password repeat field
                
                
                // add user 
                userList.getUserList().add(new User(userName.getText(), password.getText()));
            }
            
        });
    }
    
    
}
