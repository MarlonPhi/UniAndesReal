package co.edu.uniandes.fuse.api.academico.models.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nota {

	@JsonProperty(value = "SPidm")
	private String sPidm = "";
	@JsonProperty(value = "SCRN")
	private String sCRN = "";
	@JsonProperty(value = "SNombreCurso")
	private String sNombreCurso = "";
	@JsonProperty(value = "SCodigoCurso")
	private String sCodigoCurso = "";
	@JsonProperty(value = "SPeriodo")
	private String sPeriodo = "";
	@JsonProperty(value = "SNivel")
	private String sNivel = "";
	@JsonProperty(value = "DFechaNota")
	private String dFechaNota;
	@JsonProperty(value = "SNota")
	private String sNota = "";
	@JsonProperty(value = "LNotasParciales")
	private List notasParciales;
	
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
	
	public String getsNombreCurso() {
		return sNombreCurso;
	}
	
	public void setsNombreCurso(String sNombreCurso) {
		this.sNombreCurso = sNombreCurso;
	}
	
	public String getsCodigoCurso() {
		return sCodigoCurso;
	}
	
	public void setsCodigoCurso(String sCodigoCurso) {
		this.sCodigoCurso = sCodigoCurso;
	}
	
	public String getsPeriodo() {
		return sPeriodo;
	}
	
	public void setsPeriodo(String sPeriodo) {
		this.sPeriodo = sPeriodo;
	}
	
	public String getsNivel() {
		return sNivel;
	}
	
	public void setsNivel(String sNivel) {
		this.sNivel = sNivel;
	}
	
	public String getdFechaNota() {
		return dFechaNota;
	}
	
	public void setdFechaNota(String dFechaNota) {
		this.dFechaNota = dFechaNota;
	}
	
	public String getsNota() {
		return sNota;
	}
	
	public void setsNota(String sNota) {
		this.sNota = sNota;
	}
	
	public List getNotasParciales() {
		return notasParciales;
	}
	
	public void setNotasParciales(List notasParciales) {
		this.notasParciales = notasParciales;
	}
}
