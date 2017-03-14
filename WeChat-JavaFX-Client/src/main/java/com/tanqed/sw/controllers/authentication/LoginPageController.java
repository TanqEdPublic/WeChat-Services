/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers.authentication;

import com.tanqed.sw.Application;
import com.tanqed.sw.ConfigurationControllers;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
    private ConfigurationControllers.View view;

    public LoginPageController() {
        LOGGER.info("Creating Login Page Controller");
    }

    @FXML
    public void goToReg() {
        // Navigate to registration scene
        try {
            LOGGER.info("##### Inside navigation method #####");
            view.setViewToNull();
        } catch (Exception e) {
            //e.printStackTrace();#
            Application.stage.setScene(new Scene(view.getView()));
        }
    }

}
