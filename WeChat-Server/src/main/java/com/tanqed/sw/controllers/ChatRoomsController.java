package com.tanqed.sw.controllers;

import java.util.List;

import com.tanqed.sw.messageServices.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tanqed.sw.models.message_models.Message;

@RequestMapping("/chatroom/")
@RestController
public class ChatRoomsController {

	/* mark class as an end point for /chatroom/
	 *
	 *  create end point for public room /public/
		 	- GET method
		 	- return Array Representation of chat room that is parsed into json

		create end point for adding message to a chat /public/add_msg
			- POST method
			- returns "added" and client send GET to /public/
	*/

	@Autowired
	@Qualifier("MessageImpl")
	private MessageServices messageService;

	@ResponseBody
	@GetMapping("/public")
	public List<Message> getPublicChat(){
		// call a ChatService to Read chat messages from DB for public chat
		return messageService.getMessage();
	}

	@ResponseBody
	@PostMapping("/send-msg")
	public String postMessageToPublic(@RequestBody Message message){

		// Message object can be reused by implementing Serializable interface to a Message object
		
		//Message msg = new Message(message.getUsername(),message.getDate(),message.getRoom(),message.getMessage());
		// call ChatService to store message in repository
		return messageService.addMessage(message);
	}

}