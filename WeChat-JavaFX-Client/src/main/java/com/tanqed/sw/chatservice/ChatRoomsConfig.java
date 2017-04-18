/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.chatservice;

import com.tanqed.sw.chatservice.ChatRoom;
import com.tanqed.sw.chatservice.chatrooms.PublicChatRoom;
import com.tanqed.sw.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eduar
 */
@Configuration
public class ChatRoomsConfig {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ChatRoomsConfig.class);
    
    @Bean(name = "publicRoom")
    ChatRoom getPublicChatRoom(RestTemplate restTemplate){
        ChatRoom publicChat = new PublicChatRoom();
        publicChat.setRestTemplate(restTemplate);
        
        LOGGER.info("#### Public Room Bean Created ####");
        return publicChat;
    }
}
