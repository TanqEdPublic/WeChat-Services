package com.tanqed.sw.messageServices;

import com.tanqed.sw.models.message_models.Message;

import java.util.List;

/**
 * Created by kyle on 2017/3/23.
 */
public interface MessageServices {

    public String addMessage(Message message);
    public List<Message> getMessage();
}
