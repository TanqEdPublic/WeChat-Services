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
 * Created by kyle on 2017/3/23.
 */

@Service("MessageImpl")
public class MessageServiceImpl implements MessageServices {

    @Autowired
    private MongoDAO mongoDB;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String addMessage(Message message) {
        try {
//            String username = message.getUsername();
//            String date = message.getDate();
//            String room = message.getRoom();
//            String msg = message.getMessage();
            mongoDB.save(message);
        } catch (Exception exc) {

            // Need to handle other exceptions here, like invalid entry to DB,
            // preferably on a Clients before sending Request
            logger.error("@@@ User failed to be saved... Reason: " + exc.getMessage() + " @@@");
        }
        return "success";
    }

    @Override
    public ArrayList<Message> getMessage() {
        ArrayList<Message> messages = null;
        try {
            messages = mongoDB.findByRoom("public");


        } catch (Exception exc) {
            // Need to handle other exceptions here, like invalid entry to DB,
            // preferably on a Clients before sending Request
            logger.error("@@@ User failed to be finded... Reason: " + exc.getMessage() + " @@@");
        }
        return messages;
    }
}
