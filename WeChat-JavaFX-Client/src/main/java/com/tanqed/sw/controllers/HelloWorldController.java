/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author eduar
 */
@Component
public class HelloWorldController {

    @FXML
    private TextArea printHello;
    @FXML
    private Button helloBtn;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        printHello.appendText("Hello World!\n");
    }

    public TextArea getPrintHello() {
        return printHello;
    }

    public Button getHelloBtn() {
        return helloBtn;
    }

}
