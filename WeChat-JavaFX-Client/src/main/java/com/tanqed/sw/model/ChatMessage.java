/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessage implements Serializable{
    
    private String id;
    private String username;
    private String date;
    private String room;
    private String message;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessage{" + "id=" + id + ", username=" + username + ", date=" + date + ", room=" + room + ", message=" + message + '}';
    }

    
    
}
