/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers.authentication;

import com.tanqed.sw.NexusCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author eduar
 */
@Component
public class LoginPageController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPageController.class);
    private final NexusCore nexus;

    @Autowired
    public LoginPageController(NexusCore nexus) {
        LOGGER.info("Creating Login Page Controller");
        LOGGER.info("Nexus ID: " + nexus.toString());
        this.nexus = nexus;
    }
    
    
    public void goToReg(){
        // Navigate to registration scene
        // Deligate scene swapping to a Nexus
        nexus.goToRegistarionView();
    }
    
    
}
