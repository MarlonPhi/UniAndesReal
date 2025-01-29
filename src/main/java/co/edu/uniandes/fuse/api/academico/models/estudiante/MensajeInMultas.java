package co.edu.uniandes.fuse.api.academico.models.estudiante;

import org.codehaus.jackson.annotate.JsonProperty;

public class MensajeInMultas {
	
	@JsonProperty(value = "ICanal")
	private Integer ICanal;
	@JsonProperty(value = "SIdentificadorProceso")
	private String SIdentificadorProceso;
	@JsonProperty(value = "SHost")
	private String SHost;
	@JsonProperty(value = "SNombreOperacion")
	private String SNombreOperacion;
	@JsonProperty(value = "SUsuario")
	private String SUsuario;
	@JsonProperty(value = "SVersion")
	private String SVersion;
	@JsonProperty(value = "SOrigenCliente")
	private String SOrigenCliente;
	
	
	
	
	
	
	public MensajeInMultas() {
	}
	
	public Integer getICanal() {
		return ICanal;
	}
	public void setICanal(Integer iCanal) {
		ICanal = iCanal;
	}
	public String getSIdentificadorProceso() {
		return SIdentificadorProceso;
	}
	public void setSIdentificadorProceso(String sIdentificadorProceso) {
		SIdentificadorProceso = sIdentificadorProceso;
	}
	public String getSHost() {
		return SHost;
	}
	public void setSHost(String sHost) {
		SHost = sHost;
	}
	public String getSNombreOperacion() {
		return SNombreOperacion;
	}
	public void setSNombreOperacion(String sNombreOperacion) {
		SNombreOperacion = sNombreOperacion;
	}
	public String getSUsuario() {
		return SUsuario;
	}
	public void setSUsuario(String sUsuario) {
		SUsuario = sUsuario;
	}
	public String getSVersion() {
		return SVersion;
	}
	public void setSVersion(String sVersion) {
		SVersion = sVersion;
	}
	public String getSOrigenCliente() {
		return SOrigenCliente;
	}
	public void setSOrigenCliente(String sOrigenCliente) {
		SOrigenCliente = sOrigenCliente;
	}
	
	
	

}
