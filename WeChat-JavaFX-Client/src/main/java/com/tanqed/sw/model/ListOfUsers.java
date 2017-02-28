/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduar
 */

@Component
public class ListOfUsers {
    
    private final ArrayList<User> userList;  

    public ListOfUsers() {
        this.userList = new ArrayList<>();
        
        userList.add(new User("Admin", "Admin"));
        
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
    
    
}
