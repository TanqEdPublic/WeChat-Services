/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers;

import com.tanqed.sw.utilities.Swappable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * FXML Controller class
 *
 * @author eduar
 */
@Component
public class MainViewController implements Initializable{

    @FXML private BorderPane borderPane;
    
    @Autowired @Qualifier("ViewCtrl") private Swappable viewCtrl;
    
    @Autowired AnchorPane loginControl;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);
    
    public MainViewController(){
        LOGGER.info("#####MainViewController is constructed...#####");
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
       // System.out.println("### Location: " + resources.toString());
        LOGGER.info("##### Initializing components... #####");
        if(borderPane!=null){
            LOGGER.info("##### Adding Element to Border Pane #####\n" 
                        + loginControl.getChildren().toString() + "\n ##################");
            
            LOGGER.info(borderPane.topProperty().getName());
            LOGGER.info(viewCtrl.toString());
            try {
                LOGGER.info("Trying to load fxml LOGIN");
                viewCtrl.replaceSceneContent("/fxml/Login.fxml");
            } catch (Exception e) {
                //LOGGER.error();
                e.printStackTrace();
            }
            
        }else{
            LOGGER.info("##### borderPane is not initialized.... #####");
        }
        
    } // end of initialize

    public BorderPane getBorderPane() {
        return this.borderPane;
    }
    
    
    
    

    
    
}
