package co.edu.uniandes.fuse.api.academico.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCodeMessage {

	@JsonProperty(value="Message")
	private String message;
	
	

	public ResponseCodeMessage() {
	}

	public ResponseCodeMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
