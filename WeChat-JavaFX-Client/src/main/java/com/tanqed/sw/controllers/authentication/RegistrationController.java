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
public class RegistrationController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private NexusCore nexusCore;

    public RegistrationController() {
        LOGGER.info("THIS WAS NEVER TOUCHED or WAS IT??");
        nexusCore = null;
    }

    @Autowired
    public RegistrationController(NexusCore nexus) {
        LOGGER.info("Creating Registration Page Controller");
        LOGGER.info("Nexus ID: " + nexus.toString());
        this.nexusCore = nexus;
        
        LOGGER.info("Nexus reference in Reg Controller" + nexusCore.toString());
    }
    
    public void goToLogin(){
        
        if(nexusCore == null){
            LOGGER.info("NEXUS IS NULL ");
        }else{
            nexusCore.goToLoginView();
        }
    }
    
}
