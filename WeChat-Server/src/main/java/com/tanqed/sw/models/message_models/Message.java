package com.tanqed.sw.models.message_models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Getter @Setter private String id;
	@Getter @Setter private String username;
	@Getter @Setter private String date;
	@Getter @Setter private String room;
	@Getter @Setter private String message;

	public Message() {
	}

	public Message(String username, String date, String room, String message) {
		this.username = username;
		this.date = date;
		this.room = room;
		this.message = message;
	}
}
