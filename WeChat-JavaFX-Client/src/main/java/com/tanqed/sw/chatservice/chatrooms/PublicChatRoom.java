/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.chatservice.chatrooms;

import com.tanqed.sw.chatservice.ChatRoom;
import com.tanqed.sw.model.ChatMessage;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Information on generics: https://docs.oracle.com/javase/tutorial/java/generics/types.html
 * @author eduar
 */


public class PublicChatRoom implements ChatRoom{

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicChatRoom.class);

    private RestTemplate restTemplate;
    
    // used to set RestTemplate from config
    @Override
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
    @Override
    public Collection<ChatMessage> getChat() {
        
        Collection<ChatMessage> temp;
        /* Prepare request to AWS 34.251.207.109 */
        
        // Request headers
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
       
        // Class that represents request with headers and body
        HttpEntity request = new HttpEntity(null, headers);
        
        /* Response catching */
        ResponseEntity<List<ChatMessage>> response = 
               restTemplate.exchange("http://34.251.207.109:8080/chatroom/public", 
                                     HttpMethod.GET, null, 
                                     new ParameterizedTypeReference<List<ChatMessage>>(){});
        
        temp = response.getBody();
        
        LOGGER.info("### Chat size from AWS: " + String.valueOf(temp.size()) + " ###");
        // return collection with messages from back-end
        return temp;
    }

    @Override
    public void sendMessage(ChatMessage message) {
        
        /* Prepare request to AWS 34.251.207.109 */
        
        // Request headers
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        
        HttpEntity request = new HttpEntity(message, headers);
        
        ResponseEntity<String> response = 
                restTemplate.exchange("http://34.251.207.109:8080/chatroom/send-msg", 
                                     HttpMethod.POST, request, 
                                     String.class);
        
        if(response.getBody().equals("success")){
            
                LOGGER.info("### Message successfully arrived to Service ###");
        }
    }
    
}
