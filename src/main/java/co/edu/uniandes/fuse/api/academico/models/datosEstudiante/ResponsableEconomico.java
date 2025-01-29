package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponsableEconomico {
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
	@JsonProperty(value = "SCorreo")
	private String sCorreo;
	@JsonProperty(value = "SCorreoOpt1")
	private String sCorreoOpt1;
	@JsonProperty(value = "SCorreoOpt2")
	private String sCorreoOpt2;
	
	
	
	
	
	public ResponsableEconomico() {
		
	}
	public ResponsableEconomico(String sParentesco, String sPrimerNombre, String sSegundoNombre, String sApellidos,
			String sTipoDocumento, String sNumeroDocumento, String sCorreo, String sCorreoOpt1, String sCorreoOpt2) {
		this.sParentesco = sParentesco;
		this.sPrimerNombre = sPrimerNombre;
		this.sSegundoNombre = sSegundoNombre;
		this.sApellidos = sApellidos;
		this.sTipoDocumento = sTipoDocumento;
		this.sNumeroDocumento = sNumeroDocumento;
		this.sCorreo = sCorreo;
		this.sCorreoOpt1 = sCorreoOpt1;
		this.sCorreoOpt2 = sCorreoOpt2;
	}
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
	public String getsCorreo() {
		return sCorreo;
	}
	public void setsCorreo(String sCorreo) {
		this.sCorreo = sCorreo;
	}
	public String getsCorreoOpt1() {
		return sCorreoOpt1;
	}
	public void setsCorreoOpt1(String sCorreoOpt1) {
		this.sCorreoOpt1 = sCorreoOpt1;
	}
	public String getsCorreoOpt2() {
		return sCorreoOpt2;
	}
	public void setsCorreoOpt2(String sCorreoOpt2) {
		this.sCorreoOpt2 = sCorreoOpt2;
	}	
	
	
	
	
}