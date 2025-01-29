package co.edu.uniandes.fuse.api.academico.processors.datosEstudiante;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class IdentificacionEstudiante {
	
	@JsonProperty(value = "STipoDocumento")
	private String sTipoDocumento;
	@JsonProperty(value = "SNumeroDocumento")
	private String sNumeroDocumento;
	@JsonProperty(value = "DFechaExpedicion")
	private String dFechaExpedicion;
	@JsonProperty(value = "DFechaExpiracion")
	private String dFechaExpiracion;
	@JsonProperty(value = "BActivo")
	private boolean bActivo;
	
	
	public IdentificacionEstudiante(String sTipoDocumento, String sNumeroDocumento, String dFechaExpedicion,
			String dFechaExpiracion, boolean bActivo) {

		this.sTipoDocumento = sTipoDocumento;
		this.sNumeroDocumento = sNumeroDocumento;
		this.dFechaExpedicion = dFechaExpedicion;
		this.dFechaExpiracion = dFechaExpiracion;
		this.bActivo = bActivo;
	}


	public IdentificacionEstudiante() {
	}


	public String getsTipoDocumento() {
		return sTipoDocumento;
	}


	public void setsTipoDocumento(String sTipoDocumento) {
		this.sTipoDocumento = sTipoDocumento;
	}


	public String getsNumeroDocumento() {
		return sNumeroDocumento;
	}


	public void setsNumeroDocumento(String sNumeroDocumento) {
		this.sNumeroDocumento = sNumeroDocumento;
	}


	public String getdFechaExpedicion() {
		return dFechaExpedicion;
	}


	public void setdFechaExpedicion(String dFechaExpedicion) {
		this.dFechaExpedicion = dFechaExpedicion;
	}


	public String getdFechaExpiracion() {
		return dFechaExpiracion;
	}


	public void setdFechaExpiracion(String dFechaExpiracion) {
		this.dFechaExpiracion = dFechaExpiracion;
	}


	public boolean isbActivo() {
		return bActivo;
	}


	public void setbActivo(boolean bActivo) {
		this.bActivo = bActivo;
	}
	
	
	
	
	

}
