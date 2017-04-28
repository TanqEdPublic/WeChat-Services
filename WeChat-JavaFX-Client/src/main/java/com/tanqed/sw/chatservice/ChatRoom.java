/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.chatservice;

import com.tanqed.sw.model.ChatMessage;
import java.util.Collection;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Interface that defines methods for standard chat room
 */
public interface ChatRoom{
    void sendMessage(ChatMessage message);
    Collection<ChatMessage> getChat();

    // method to set rest template. Ideally shouldn't be here,
    // but no time to develope work around. 
    void setRestTemplate(RestTemplate restTemplate);
}
