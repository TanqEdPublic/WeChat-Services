package com.tanqed.sw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication // initializing Spring application
// extending application to become JavaFX Application
public class ApplicationEntryPoint extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationEntryPoint.class);
    private ConfigurableApplicationContext springContext; // for manual context configurations
    private static Parent rootNode; // The base class for all nodes that have children in the scene graph
    private static Stage stage; // Primery Stage referance

    public static void main(final String[] args) {
        LOGGER.info("##### APPLICATION LAUNCH #####");
        Application.launch(args); // Launch a standalone application
    }

    /* The main entry point for all JavaFX applications. 
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     */
    @Override
    public void init() throws Exception {
        LOGGER.info("##### APPLICATION INITIALIZATION #####");
        // setting SpringContext to initialize this app as a Spring Application
        springContext = SpringApplication.run(ApplicationEntryPoint.class);
        // Loading Main fxml view as a starting point of an app
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/authentication/LoginPage.fxml"));
        // Injection of controllers happens here
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load(); //Loads a nodes hierarchy from a FXML document
    }

    @Override
    public void start(Stage stage) throws Exception {
        LOGGER.info("##### JAVA FX START #####");
        try {
            this.stage = stage;
            LOGGER.info("PRIMERY STAGE: " + this.stage);
            stage.setResizable(false);
            stage.setScene(new Scene(rootNode)); // set a scene with a root node
            stage.show(); // show what is on stage (scene is displayed)

        } catch (Exception ex) {
            ex.printStackTrace();
            // Logger.getLogger(ApplicationEntryPoint.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // This method is called when the application should stop, 
    // and provides a convenient place to prepare for application exit and destroy resources
    @Override
    public void stop() throws Exception {
        springContext.close(); // close Spring resources
    }

    /* GETTERS */
    protected static Parent getRootNode() {
        return rootNode;
    }

    protected static Stage getStage() {
        LOGGER.info("Stage from APP: " + stage.toString());
        return stage;
    }

} // end of ApplicationEntryPoint

//Options ...   
//gets called for each Controller that you want DI. If you put a println
//in before the return, you can see which Controller gets DI by spring
//via good old anonymous class
//        loader.setControllerFactory(new Callback<Class<?>, Object>() {
//            @Override
//            public Object call(Class<?> clazz) {
//                return springContext.getBean(clazz);
//            }
//        });
// via lambda
//        loader.setControllerFactory((clazz) -> springContext.getBean(clazz));
//  via method reference       
// loader.setControllerFactory(springContext::getBean);
