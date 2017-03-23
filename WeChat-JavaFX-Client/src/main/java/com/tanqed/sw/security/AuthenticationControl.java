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
public class AuthenticationControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationControl.class);

    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean login(String login, String password) {
        boolean loginFlag = false;
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        User user = new User();
        user.setUsername(login);
        user.setPassword(password);

        HttpEntity request = new HttpEntity(null, headers);

        Callable<String> task = () -> restTemplate.getForObject("http://localhost:8080/login"
                + "?username=" + login
                + "&password=" + password,
                String.class);

        Future<String> future = Executors.newCachedThreadPool().submit(task);

        try {
            switch (future.get()) {
                case "Wrong username/password":
                    loginFlag = false;
                    break;
                case "Logged in":
                    loginFlag = true;
                    break;
            }
        } catch (InterruptedException | ExecutionException ex) {
            java.util.logging.Logger.getLogger(AuthenticationControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return loginFlag;
    }

    public boolean register(String username, String password) {

        boolean loginFlag = false;
        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        HttpEntity request = new HttpEntity(null, headers);

             // 34.251.207.109 - AWS
            // localhost
        Callable<String> task = () -> restTemplate.postForObject("http://localhost:8080/sign-up"
                + "?username=" + username
                + "&password=" + password,
                request, String.class);

        Future<String> future = Executors.newCachedThreadPool().submit(task);

        try {
            switch (future.get()) {
                case "Duplicate":
                    loginFlag = false;
                    break;
                case "OK":
                    loginFlag = true;
                    break;
            }
        } catch (InterruptedException | ExecutionException ex) {
            java.util.logging.Logger.getLogger(AuthenticationControl.class.getName()).log(Level.SEVERE, null, ex);
        }

       return loginFlag;
    }
}
