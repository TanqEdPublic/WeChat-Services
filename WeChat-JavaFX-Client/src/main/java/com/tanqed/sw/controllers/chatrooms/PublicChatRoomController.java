/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.controllers.chatrooms;

import com.tanqed.sw.chatservice.ChatRoom;
import com.tanqed.sw.model.ChatMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * FXML Controller class
 *
 * @author eduar
 */

public class PublicChatRoomController{
    
    private final Logger LOGGER = LoggerFactory.getLogger(PublicChatRoomController.class);
    
    @Qualifier("publicRoom")
    @Autowired private ChatRoom publicChat;
    
    
    @FXML TextArea messageTextArea;
    @FXML VBox chatView;
    
    // Stores messages 
    private ObservableList<ChatMessage> observableChat;
    
    public void initialize() {}    
    
    // Use post construct annotation to do something when properties of class are 
    // set. Some properties maybe set after construction of class.
    @PostConstruct
    public void initChat() throws Exception {


        // request data from Service
        // bind data to an observable collection       // cast to List, as method return Collection Type
        observableChat = FXCollections.observableArrayList(publicChat.getChat());
        
        LOGGER.info(String.valueOf(observableChat.size()));
        // Initialize chat history 
        // Add HBox (Formatted Message) to VBox (Chat View) for every
        // element in observable array list.
        
        observableChat.forEach((chatMessage) -> {
            
            chatView.getChildren().add(messageBuilder(chatMessage));
            
        });

        
    }
    
    
    private VBox messageBuilder(ChatMessage messageObject){
        Label userName = new Label(messageObject.getUsername());
        Label date = new Label(messageObject.getDate().substring(0, 10));
        Label time = new Label(messageObject.getDate().substring(11));
        Label messageBody = new Label(messageObject.getMessage());
        
        VBox message = new VBox(); // message node that return from function
        HBox header = new HBox(10); // header of the message
        header.setPadding(new Insets(3)); 
        header.getChildren().addAll(date, userName);
        
        message.getChildren().addAll(header, messageBody, time);
       
        String cssLayout = "-fx-border-color: gray;\n" +
                           "-fx-border-insets: 5;\n" +
                           "-fx-border-width: 1;\n";
        message.setStyle(cssLayout);
        message.setPrefWidth(270);
        return message;
    }
    
}
