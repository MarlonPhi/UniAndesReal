package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.HashMap;

public class SimpleResponse {
	
	private boolean status;
	private String message;
	private HashMap<?, ?> data;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HashMap<?, ?> getData() {
		return data;
	}
	public void setData(HashMap<?, ?> data) {
		this.data = data;
	}

}
