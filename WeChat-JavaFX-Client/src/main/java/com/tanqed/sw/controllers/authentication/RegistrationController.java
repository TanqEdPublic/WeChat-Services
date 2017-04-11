/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers.authentication;

import com.tanqed.sw.Application;
import com.tanqed.sw.ConfigurationControllers;
import com.tanqed.sw.security.Authenticator;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduar
 */
@Component
public class RegistrationController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    @Qualifier("authent") @Autowired Authenticator authenticator;
    
    @Qualifier("loginView")
    @Autowired
    private ConfigurationControllers.View view;

    
    @FXML TextField loginField;
    @FXML PasswordField passwordField;
    @FXML Label statusMessage;
    
    @FXML
    public void goToLogin(){
        // Navigate to registration scene
        try {
            LOGGER.info("##### Inside navigation method #####");
            view.setParentViewToNull();
        } catch (Exception e) {
            //e.printStackTrace();#
            Application.stage.setScene(new Scene(view.getParentView()));
        }
    } // end of goToLogin
    
    @FXML
    public void register(){
        
        boolean status = authenticator.register(loginField.getText(), passwordField.getText());
        LOGGER.info("##### Registration Submission status returned... #####" + status);
        
        if(!status){
           statusMessage.setText("User exist! Try another...");
           statusMessage.setTextFill(Color.RED);
        }else{
           statusMessage.setText("Registration Successful");
           statusMessage.setTextFill(Color.GREEN);
           
           // go to login
        }
        
        
        loginField.clear();
        passwordField.clear();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} // end of class
