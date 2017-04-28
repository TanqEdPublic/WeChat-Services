package com.tanqed.sw;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

// Class to bind Spring Context with JavaFX Application
// ref.: https://habrahabr.ru/post/265511/
public abstract class AbstractJavaFxApplicationSupport extends Application {

    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    // Infiltrating Spring Context during FX App initialization phase. 
    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    // tiding up context as well on application stop
    // that where to stop thread executioners as well
    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    // Launch FX application on Spring from Application.class
    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> appClass, String[] args) {
        AbstractJavaFxApplicationSupport.savedArgs = args;
        Application.launch(appClass, args);
    }
}
