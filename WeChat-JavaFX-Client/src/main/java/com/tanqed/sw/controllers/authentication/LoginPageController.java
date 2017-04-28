/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers.authentication;

import com.tanqed.sw.Application;
import com.tanqed.sw.ConfigurationControllers;
import com.tanqed.sw.security.Authenticator;
import javafx.event.ActionEvent;
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

/**
 *
 * @author eduar
 * 
 * FX Controller for Login Page. 
 * Contains references to elements in the UI and autowired beans 
 * for navigation options.
 * Authenticator for login on cloud
 */
public class LoginPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageController.class);

    @Qualifier("regView")
    @Autowired
    private ConfigurationControllers.View regView;
    
    @Qualifier("chatRoomView")
    @Autowired
    private ConfigurationControllers.View chatView;
    
    @Qualifier("authent")
    @Autowired
    private Authenticator authenticator;
    
    // Properties that correspond to UI elemenets.
    @FXML private TextField loginField;
    @FXML  private PasswordField passwordField;
    @FXML Label statusMessage;
    
    
   // Little bit of debugging inside constructor
   // Authenticator should be null at the moment of a class construction
    // Autowiring happening at Post Construct of a class
    public LoginPageController() {
        LOGGER.info("Creating Login Page Controller");
        if(authenticator == null){
            LOGGER.info("@@@@ AUTHENTICATOR IS NULL IN LoginController! @@@@");
        }
    }

    // Method to navigate to Registration page
    @FXML
    public void goToReg() {
        // Navigate to registration scene
        try {
            LOGGER.info("##### Inside navigation method #####");
            regView.setParentViewToNull(); // bad bad bad practice... workaround an exception
        } catch (Exception e) {
            // set new Scene to a stage. There must be better way...
            Application.stage.setScene(new Scene(regView.getParentView()));
        }
    }
    
    // Submission for login
    @FXML
    public void loginSubmit(ActionEvent event){
        // send authenticator to do login on cloud and return true/false to indicate status
        boolean status = authenticator.login(loginField.getText(), passwordField.getText());
        
        LOGGER.info("##### Login Submission status returned... #####" + status);
        if(!status){
            // login produce error message
            statusMessage.setText("Username/Password is incorrect");
            statusMessage.setTextFill(Color.RED);
            
        }else{
            // navigate to chat room
            statusMessage.setText("Successfully logged in!");
            statusMessage.setTextFill(Color.GREEN);
            authenticator.setUserName(loginField.getText());
            try {
                chatView.setParentViewToNull();
            } catch (Exception e) {
                Application.stage.setScene(new Scene(chatView.getParentView()));
            }
        } 
        
        // clear fields, if login fails...
        loginField.clear();
        passwordField.clear();
    }


    
    
    
    

}
