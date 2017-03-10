/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author eduar
 *
 * Should be able to replace root node content 1. Login scene is loaded in init
 * method, but can be placed again from registration scene
 */
@Component
public final class NexusCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(NexusCore.class);
   
    private Parent rootNode;
    
    public NexusCore() {
        LOGGER.info("Nexus ID: " + this.toString());
    }
    
    // Method that navigates from Login to Registration
    public void goToRegistarionView() {        
        try {
            rootNode = null;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/authentication/RegistrationPage.fxml"));
            rootNode = fxmlLoader.load();

            System.out.println("##### Before setting new scene #####");
            ApplicationEntryPoint.getStage().setScene(new Scene(rootNode));
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(NexusCore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goToLoginView() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/authentication/LoginPage.fxml"));
            rootNode = fxmlLoader.load();
            
            ApplicationEntryPoint.getStage().setScene(new Scene(rootNode));
        }catch(IOException ex){
            ex.printStackTrace(); 
        }
    }
    
    


//    private void setRootNode() {
//        this.rootNode = app.getRootNode();
//        System.out.println("Root Node WAS SET: " + rootNode.toString());
//    }
    
    

}
