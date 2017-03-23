package com.tanqed.sw.models.message_models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

public class Message {

	@Id
	@Getter @Setter private String id;
	@Getter @Setter private String username;
	@Getter @Setter private String date;
	@Getter @Setter private String room;
	@Getter @Setter private String message;
	
	
}