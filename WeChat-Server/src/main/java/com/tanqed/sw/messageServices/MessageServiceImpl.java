package com.tanqed.sw.messageServices;

import com.tanqed.sw.models.message_models.Message;
import com.tanqed.sw.repositories.MongoDAO;
import com.tanqed.sw.userServices.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * MessageService Implementation class, generally called Service.
 * Wraps the CRUD operations against MongoDB.
 * RestControllers are de-coupled from business logic of an application.
 * It can also include utility objects that Controllers shouldn't know about.
 * 	- Data validators
 * 	- Response header factories 
 * 	- Token factories
 *  - State Readers
 * 	- etc..
 * 
 * Service class implements MessageServices interface that defines a contract methods 
 * that class most follow, i.e., implementing business functionality.
 * 
 */

@Service("MessageImpl")
public class MessageServiceImpl implements MessageServices {

    @Autowired
    private MongoDAO mongoDB; // repository instance that carries on CRUD on DB 

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // Method that receives Message object from ChatRoomsController
    // Various validations on the message can be performed here before saving 
    // it to database.
    @Override
    public String addMessage(Message message) {
        try {
        	
        	// calling a method from repository to save message into MongoDB
            mongoDB.save(message);
        } catch (Exception exc) {

            // Need to handle other exceptions here, like invalid entry to DB,
            // preferably on a Clients before sending Request
            logger.error("@@@ Something went wrong with saving message. Handle exception here.... " + exc.getMessage() + " @@@");
        }
        return "success";
    }

    
    // Preparing full chat for deployment to a client application.
    // Function returns ArrayList of all messages in a chat.
    @Override
    public ArrayList<Message> getMessage() {
    	
        ArrayList<Message> messages = null; // Array List of type Message to store a result set of a query from a database
        try {
        	// Populating array list with a result set of a query. findByRoom function also return ArrayList<Message>
        	// Note that Message POJO contains @Id annotation that identifies this object in MongoDB
            messages = mongoDB.findByRoom("public");


        } catch (Exception exc) {
            // Need to handle other exceptions here, like invalid entry to DB,
            // preferably on a Clients before sending Request
            logger.error("@@@ User failed to be finded... Reason: " + exc.getMessage() + " @@@");
        }
        return messages;
    }
}
