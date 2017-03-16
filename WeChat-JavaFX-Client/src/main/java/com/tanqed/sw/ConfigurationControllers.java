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


@Configuration
public class ConfigurationControllers {


    @Bean(name = "loginView")
    public View getLoginView() throws IOException {
        return loadView("fxml/authentication/LoginPage.fxml");
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
        private Parent view;
        private Object controller;
        private Logger logger = LoggerFactory.getLogger(View.class);
        
        
        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
            logger.info("New FX Controller created! ID: " + this.toString());
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
        
        public void setViewToNull(){
            if(view != null){
                view.getScene().setRoot(null);
            }
        }
    }

}
