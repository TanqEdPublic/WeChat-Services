package com.tanqed.sw;

import com.tanqed.sw.controllers.authentication.LoginPageController;
import com.tanqed.sw.controllers.authentication.RegistrationController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    Configuration-annotated class. Typically consists of @Bean-annotated
    methods that define instantiation, configuration, and initialization logic
    for objects that will be managed by the Spring IoC container.
*/

@Configuration
public class ConfigurationControllers {


    
    /*
        This methods return instances of FXML views. When application compiles,
        Spring instantiate them. You can set this instance as a new scene on an
        application stage. Each view also have an individual controller to 
        drive GUI. 
    */
    
    @Bean(name = "loginView") // we can autowire particular view we want using naming conventions
    public View getLoginView() throws IOException {
        return loadView("fxml/authentication/LoginPage.fxml"); 
        /* Method loadView() return object of type View class that is declaired inside configuration file.
           It excepts path to a fxml file as a parameter.
        */ 
    }
    
    @Bean(name = "regView")
    public View getRegistrationView() throws IOException {
        return loadView("fxml/authentication/RegistrationPage.fxml");
    }

    
    @Bean
    public LoginPageController loginPageController()throws IOException {
        return (LoginPageController) getLoginView().getController();
    }
    
    @Bean
    public RegistrationController registrationPageController()throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }
    
    // Standard way of loading FXML files into memory
    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class View {
        private Parent parentView;
        private Object controller;
        private Logger logger = LoggerFactory.getLogger(View.class);
        
        
        public View(Parent view, Object controller) {
            this.parentView = view;
            this.controller = controller;
            logger.info("New FX Controller created! ID: " + this.toString());
        }

        public Parent getParentView() {
            return parentView;
        }

        public void setParentView(Parent parentView) {
            this.parentView = parentView;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
        
        public void setParentViewToNull(){
            if(parentView != null){
                parentView.getScene().setRoot(null);
            }
        }
    }

}
