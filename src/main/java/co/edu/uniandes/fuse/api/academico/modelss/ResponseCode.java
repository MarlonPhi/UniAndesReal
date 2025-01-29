package co.edu.uniandes.fuse.api.academico.modelss;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCode {
	
	@JsonProperty("Status")
	private Boolean status;
	
	@JsonProperty("Message")
	private String message;
	
	

	public ResponseCode(Boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	

	public ResponseCode() {
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
