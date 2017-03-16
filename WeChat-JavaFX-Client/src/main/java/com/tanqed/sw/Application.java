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


    - Holds a static reference to a Main Stage to use by Configuration class for controllers
        - The stage object is manipulated from ConfigurationControllers.java class 
        - This class defines a wrapper class called View that serves as a prototype 
          for a each view we want to present to a user.
        - In JavaFX each view can be represented in one or more files. 
          Those files include: FXML resource, JavaFX Controller and CSS style sheet.
            FXML resource is a GUI representation file that defines elements for 
          an individual view. Structure of elements follow the DOM standard, 
          aka parent-child relationship between components, or technically saying
          Nodes. 
            FX Coontroller is a POJO that is able to manipulate components of a view.
            CSS style sheet applies style the elements of FXML file.
        - Coming back to our View object, at a construction time, constructor takes
          two parameters: reference to fxml and controller for this resource.
          Considering to expand to 3 param constructor to include Style Sheet
        - In ConfigurationControllers.java class we also create named Spring Beans
          with a method to get a reference to resources and controllers
        - When the program compiles, Spring create this beans in IOC so that we could
          refer to them later when we need to change a view.
        - Anytime we need to set a new view, we can autowire a particular one
          using it's name conventions. Spring provides annotation @Qualifier that
          accepts a Spring bean name as a parameter and autowires corresponding bean.
        - All whats left to do than is use the object's method getView() and 
          set what returns as a paramatere of a new Scene.



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
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

}
