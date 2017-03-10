/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers;

import com.tanqed.sw.model.ListOfUsers;
import com.tanqed.sw.utilities.SceneController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author eduar
 */
@Component("loginControl")
public class LoginController extends AnchorPane implements Initializable{

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Hyperlink registerLink;

    @Autowired
    private ListOfUsers userList;
    @Autowired
    private SceneController myCtrl;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // LOGGER.info("***** Initializing Login Controller *****");
    }    
    
    private static final Logger  LOGGER = LoggerFactory .getLogger(LoginController.class);

    public LoginController() {
       // LOGGER.info("***** Constructing Login Controller *****");
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
    }

    @FXML
    private void validateUser() {
        
      //  myCtrl.loadRegistration();
        
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
