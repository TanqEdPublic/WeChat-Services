/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.security;

import com.tanqed.sw.model.ListOfUsers;
import com.tanqed.sw.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eduar
 */
@Component
public class AuthenticationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationControl.class);
    @Autowired
    private ListOfUsers users;

    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean login(String login, String password) {
        boolean loginFlag = false;

        users.getUserList().forEach(user -> {

            if (login.equals(user.getUsername())
                    && password.equals(user.getPassword())) {

            }
        });

        return false;
    }

    public void register(String username, String password) {

        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpEntity request = new HttpEntity(null, headers);

        Runnable task = () -> {

            // 34.251.207.109 - AWS
            // localhost
            try {
                String status = restTemplate.postForObject("http://localhost:8080/sign-up" 
                                                          + "?username=" + username 
                                                          + "&password=" + password,
                                                          request, String.class); // , login(username, password)
                LOGGER.info(status);
            } catch (RestClientException e) {
                LOGGER.info("#### Exception in REGISTRATION ####");
                e.printStackTrace();
            }

        };

        new Thread(task).start();
    }
}
