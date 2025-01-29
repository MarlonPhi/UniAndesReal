package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContratoEZProxy {
	
	@JsonProperty(value="username")
    protected String username;
	@JsonProperty(value="password")
    protected String password;
	@JsonProperty(value="cliente")
    protected String cliente;
	@JsonProperty(value="users_vip")
    protected String users_vip;
	@JsonProperty(value="access_vip")
    protected String access_vip;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getUsers_vip() {
		return users_vip;
	}
	public void setUsers_vip(String users_vip) {
		this.users_vip = users_vip;
	}
	public String getAccess_vip() {
		return access_vip;
	}
	public void setAccess_vip(String access_vip) {
		this.access_vip = access_vip;
	}
	
	

}
