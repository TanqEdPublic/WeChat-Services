/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanqed.sw.security;

import com.tanqed.sw.controllers.authentication.RegistrationController;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eduar
 */
@Configuration
public class AuthenticationConfigurations {
    
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationConfigurations.class);
    
    @Bean(name = "authent")
    Authenticator authenticationControl(RestTemplate restTemplate){
        Authenticator authenticationControl = new Authenticator();
        
        LOGGER.info("### Setting Rest Template CONFIG ###");
        authenticationControl.setRestTemplate(restTemplate);
        
        return authenticationControl;
    }
    
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
    
    
    
//            RestTemplate restTemplate =
//                new RestTemplate(Collections.<HttpMessageConverter<?>>singletonList (
//                        new MappingJackson2HttpMessageConverter()));
//    
//            restTemplate.setMessageConverters
//            (Collections.<HttpMessageConverter<?>>singletonList
//           (new MappingJackson2HttpMessageConverter()));
//            
//            return restTemplate;
        return builder.build();
    }
}
