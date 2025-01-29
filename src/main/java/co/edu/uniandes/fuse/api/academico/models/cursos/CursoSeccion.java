package co.edu.uniandes.fuse.api.academico.models.cursos;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CursoSeccion {
	
	@JsonProperty(value = "SNombreCurso")
	private String sNombreCurso = "";
	@JsonProperty(value = "SCodigoCurso")
	private String sCodigoCurso = "";
	@JsonProperty(value = "SCRN")
	private String sCRN = "";
	@JsonProperty(value = "SSeccion")
	private String sSeccion = "";
	@JsonProperty(value = "SPeriodo")
	private String sPeriodo = "";
	@JsonProperty(value = "ICupoMax")
	private String iCupoMax = "";
	@JsonProperty(value = "IInscritos")
	private String iInscritos = "";
	@JsonProperty(value = "SCreditos")
	private String sCreditos = "";
	@JsonProperty(value = "SAtributo")
	private String sAtributo = "";
	@JsonProperty(value = "SAtributoDescripcion")
	private String sAtributoDescripcion = "";
	
	

	public CursoSeccion(String sNombreCurso, String sCodigoCurso, String sCRN, String sSeccion, String sPeriodo,
			String iCupoMax, String iInscritos, String sCreditos, String sAtributo, String sAtributoDescripcion) {
		this.sNombreCurso = sNombreCurso;
		this.sCodigoCurso = sCodigoCurso;
		this.sCRN = sCRN;
		this.sSeccion = sSeccion;
		this.sPeriodo = sPeriodo;
		this.iCupoMax = iCupoMax;
		this.iInscritos = iInscritos;
		this.sCreditos = sCreditos;
		this.sAtributo = sAtributo;
		this.sAtributoDescripcion = sAtributoDescripcion;
	}


	public CursoSeccion() {
	}


	public String getsCreditos() {
		return sCreditos;
	}

	public void setsCreditos(String sCreditos) {
		this.sCreditos = sCreditos;
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

	public String getsCRN() {
		return sCRN;
	}

	public void setsCRN(String sCRN) {
		this.sCRN = sCRN;
	}

	public String getsPeriodo() {
		return sPeriodo;
	}

	public void setsPeriodo(String sPeriodo) {
		this.sPeriodo = sPeriodo;
	}

	public String getiCupoMax() {
		return iCupoMax;
	}

	public void setiCupoMax(String iCupoMax) {
		this.iCupoMax = iCupoMax;
	}

	public String getiInscritos() {
		return iInscritos;
	}

	public void setiInscritos(String iInscritos) {
		this.iInscritos = iInscritos;
	}

	public String getsSeccion() {
		return sSeccion;
	}

	public void setsSeccion(String sSeccion) {
		this.sSeccion = sSeccion;
	}

	public String getsAtributo() {
		return sAtributo;
	}

	public void setsAtributo(String sAtributo) {
		this.sAtributo = sAtributo;
	}

	public String getsAtributoDescripcion() {
		return sAtributoDescripcion;
	}

	public void setsAtributoDescripcion(String sAtributoDescripcion) {
		this.sAtributoDescripcion = sAtributoDescripcion;
	}
	
	

}
