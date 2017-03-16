/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduar
 */

@Component
public class User implements Serializable{

    /*
        With JavaFX it's common to use Properties for all fields of a model class.
        A Property allows us, for example, to automatically be notified when
        the lastName or any other variable is changed. This helps us keep the view
        in sync with the data.
    */
    
    private static final long serialVersionUID = 1L;
    private final StringProperty username;
    private final StringProperty password;

    public User() {
        this(null, null);
    }

    public User(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
