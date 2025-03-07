package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)

public class Acudiente {
	/*@JsonProperty(value = "SPidm")
	private String sPidm;*/
	@JsonProperty(value = "SParentesco")
	private String sParentesco;
	@JsonProperty(value = "SPrimerNombre")
	private String sPrimerNombre;
	@JsonProperty(value = "SSegundoNombre")
	private String sSegundoNombre;	
	@JsonProperty(value = "SApellidos")
	private String sApellidos;
	@JsonProperty(value = "STipoDocumento")
	private String sTipoDocumento;
	@JsonProperty(value = "SNumeroDocumento")
	private String sNumeroDocumento;
	
	
	/*public String getsPidm() {
		return sPidm;
	}
	public void setsPidm(String sPidm) {
		this.sPidm = sPidm;
	}*/
	public String getsParentesco() {
		return sParentesco;
	}
	public void setsParentesco(String sParentesco) {
		this.sParentesco = sParentesco;
	}
	public String getsPrimerNombre() {
		return sPrimerNombre;
	}
	public void setsPrimerNombre(String sPrimerNombre) {
		this.sPrimerNombre = sPrimerNombre;
	}
	public String getsSegundoNombre() {
		return sSegundoNombre;
	}
	public void setsSegundoNombre(String sSegundoNombre) {
		this.sSegundoNombre = sSegundoNombre;
	}
	public String getsApellidos() {
		return sApellidos;
	}
	public void setsApellidos(String sApellidos) {
		this.sApellidos = sApellidos;
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
}