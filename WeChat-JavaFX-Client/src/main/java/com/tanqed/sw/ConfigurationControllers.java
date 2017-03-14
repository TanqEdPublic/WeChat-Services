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


/**
 * Date: 27.08.15
 * Time: 11:04
 *
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
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

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его сделать произвести все необходимые инъекции.
     */
    
    @Bean
    public LoginPageController loginPageController()throws IOException {
        return (LoginPageController) getLoginView().getController();
    }
    
    @Bean
    public RegistrationController registrationPageController()throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }
    
    
    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
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

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа {@link Application}.
     */
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
