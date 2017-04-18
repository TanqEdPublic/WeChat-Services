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
    
    
    @FXML private TextField loginField;
    @FXML  private PasswordField passwordField;
    @FXML Label statusMessage;
    
    
    
    public LoginPageController() {
        LOGGER.info("Creating Login Page Controller");
        if(authenticator == null){
            LOGGER.info("@@@@ AUTHENTICATOR IS NULL IN LoginController! @@@@");
        }
    }

    @FXML
    public void goToReg() {
        // Navigate to registration scene
        try {
            LOGGER.info("##### Inside navigation method #####");
            regView.setParentViewToNull();
        } catch (Exception e) {
            //e.printStackTrace();#
            Application.stage.setScene(new Scene(regView.getParentView()));
        }
    }
    
    @FXML
    public void loginSubmit(ActionEvent event){
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
            
            try {
                chatView.setParentViewToNull();
            } catch (Exception e) {
                Application.stage.setScene(new Scene(chatView.getParentView()));
            }
        } 
        
        loginField.clear();
        passwordField.clear();
    }


    
    
    
    

}
