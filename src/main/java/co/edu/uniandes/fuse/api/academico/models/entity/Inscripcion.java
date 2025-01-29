package co.edu.uniandes.fuse.api.academico.models.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Inscripcion {
	
	@JsonProperty(value = "SPidm")
	private String sPidm;
	@JsonProperty(value = "SCRN")
	private String sCRN;
	@JsonProperty(value = "SNivel")
	private String sNivel;
	@JsonProperty(value = "SPeriodo")
	private String sPeriodo;
	@JsonProperty(value = "STipoRegistro")
	private String sTipoRegistro;
	@JsonProperty(value = "SEstadoInscripcion")
	private String sEstadoInscripcion;
	@JsonProperty(value = "DFechaActividad")
	private String dFechaActividad;
	
	
	
	
	
	public Inscripcion() {
	}


	public Inscripcion(String sPidm, String sCRN, String sNivel, String sPeriodo, String sTipoRegistro,
			String sEstadoInscripcion, String dFechaActividad) {

		this.sPidm = sPidm;
		this.sCRN = sCRN;
		this.sNivel = sNivel;
		this.sPeriodo = sPeriodo;
		this.sTipoRegistro = sTipoRegistro;
		this.sEstadoInscripcion = sEstadoInscripcion;
		this.dFechaActividad = dFechaActividad;
	}
	
	
	public String getsPidm() {
		return sPidm;
	}
	public void setsPidm(String sPidm) {
		this.sPidm = sPidm;
	}
	public String getsCRN() {
		return sCRN;
	}
	public void setsCRN(String sCRN) {
		this.sCRN = sCRN;
	}
	public String getsNivel() {
		return sNivel;
	}
	public void setsNivel(String sNivel) {
		this.sNivel = sNivel;
	}
	public String getsPeriodo() {
		return sPeriodo;
	}
	public void setsPeriodo(String sPeriodo) {
		this.sPeriodo = sPeriodo;
	}
	public String getsTipoRegistro() {
		return sTipoRegistro;
	}
	public void setsTipoRegistro(String sTipoRegistro) {
		this.sTipoRegistro = sTipoRegistro;
	}
	public String getsEstadoInscripcion() {
		return sEstadoInscripcion;
	}
	public void setsEstadoInscripcion(String sEstadoInscripcion) {
		this.sEstadoInscripcion = sEstadoInscripcion;
	}
	public String getdFechaActividad() {
		return dFechaActividad;
	}
	public void setdFechaActividad(String dFechaActividad) {
		this.dFechaActividad = dFechaActividad;
	}
	
	
	
	

}
