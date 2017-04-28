/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.chatservice;

import com.tanqed.sw.chatservice.chatrooms.PublicChatRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eduar
 * 
 * Config class to define bean for Public Chatroom
 * In future, other static chat rooms can be defined here.
 * 
 */
@Configuration
public class ChatRoomsConfig {
    // logger for debugging 
    private final Logger LOGGER = LoggerFactory.getLogger(ChatRoomsConfig.class);
   
    // Public Room bean with injected Rest Template
    @Bean(name = "publicRoom")
    ChatRoom getPublicChatRoom(RestTemplate restTemplate){
        ChatRoom publicChat = new PublicChatRoom();
        publicChat.setRestTemplate(restTemplate); 
        
        LOGGER.info("#### Public Room Bean Created ####");
        return publicChat;
    }
}
