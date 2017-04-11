/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.security;

import com.tanqed.sw.model.User;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eduar
 */
@Component
public class Authenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(Authenticator.class);

    private RestTemplate restTemplate;

    // used to set RestTemplate from config
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean login(String login, String password) {
        boolean loginFlag = false;
        String status;
        // Request headers
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        // user Model
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);

        // request wrapper class
        HttpEntity request = new HttpEntity(null, headers);

        // AWS 34.251.207.109
        
        status = restTemplate.getForObject("http://localhost:8080/login"
                + "?username=" + login
                + "&password=" + password,
                String.class);
        
        switch (status) {
            case "wrong_pass":
                break;
            case "logged":
                loginFlag = true;
                break;
        }

        return loginFlag;
    }

    public boolean register(String username, String password) {

        boolean regFlag = false; // registration status
        String result; // response result
        
        // add headers to the request 
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        // user model to post if end point was handling request body
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        HttpEntity request = new HttpEntity(null, headers); // request information wrapper

        // 34.251.207.109 - AWS
        // localhost
        // POST request to an end-point with parameters. Request body is set to null,
        // but may contain user object.
        result = restTemplate.postForObject("http://localhost:8080/sign-up"
                + "?username=" + username
                + "&password=" + password,
                request, String.class);

        switch (result) {
            case "duplicate_user":
                break;
            case "registered":
                regFlag = true;
                break;
        }
        
       return regFlag;
    }
}
