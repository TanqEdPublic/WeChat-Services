package com.tanqed.sw.utilities;

import com.tanqed.sw.controllers.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author eduar
 * 
 * This class performs views swapping operations 
 * It implements an interface Swappable to represent implementation in caller classes
 */



@Component("ViewCtrl")
public class SceneController implements Swappable{
    
    @Autowired private MainViewController mainView;
    private static final Logger LOGGER = LoggerFactory.getLogger(SceneController.class);
    
    
    public void loadRegistration(){
        System.out.println();
    }

    /* Called to initialize a controller after its root element has been
     * completely processed. */
    
    @Override
    public void replaceSceneContent(String fxml) throws Exception{
        // what is passed..
            // pass url for fxml file 
            
        // what's need to be done
            // load fxml file into a node 

        AnchorPane page;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            page = (AnchorPane) fxmlLoader.load();
            
           LOGGER.info(fxmlLoader.getLocation().toString());
           LOGGER.info(mainView.getBorderPane().toString());
           mainView.getBorderPane().setCenter(page);
        }catch(Error err){
            LOGGER.info("FXML Loader didn't load.");
        }
        

        //LOGGER.info(fxmlLoader);
        
        
//        try {
//            page = (AnchorPane) loader.load(in);
//        } finally {
//            in.close();
//        }
        
            // append page to an element in a main view
            // deligate to other function
           
        
        // whats need to be returned..
            // return Controller for loaded fxml
        //return (Initializable) loader.getController();
    }
    
}
