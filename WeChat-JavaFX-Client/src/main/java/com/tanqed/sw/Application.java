package com.tanqed.sw;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;


/*
    Application Runner class.
    - Launches java fx application thread 
    - Overrides start() method 
    - Holds static stage variable for view swapping accross an app
    - Sets Login Page as a starting view



*/
@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    public static Stage stage;
    @Value("${ui.title:JavaFX Application}")//
    private String windowTitle;

    @Qualifier("loginView")
    @Autowired
    private ConfigurationControllers.View view;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getParentView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
