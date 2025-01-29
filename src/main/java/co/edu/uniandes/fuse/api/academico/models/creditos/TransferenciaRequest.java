package co.edu.uniandes.fuse.api.academico.models.creditos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferenciaRequest {

	@JsonProperty
	private String pidm;
	@JsonProperty
	private String tipoHomologacion;
	@JsonProperty
	private String codigoUniversidad;
	@JsonProperty
	private String prefijoCurso;
	@JsonProperty
	private String numeroCurso;
	@JsonProperty
	private String nivel;
	@JsonProperty
	private String programa;
	@JsonProperty
	private String grado;
	@JsonProperty
	private String periodoAsistencia;
	@JsonProperty
	private String idioma;
	@JsonProperty
	private String examen;
	
	public String getPidm() {
		return pidm;
	}
	public void setPidm(String pidm) {
		this.pidm = pidm;
	}
	public String getTipoHomologacion() {
		return tipoHomologacion;
	}
	public void setTipoHomologacion(String tipoHomologacion) {
		this.tipoHomologacion = tipoHomologacion;
	}
	public String getCodigoUniversidad() {
		return codigoUniversidad;
	}
	public void setCodigoUniversidad(String codigoUniversidad) {
		this.codigoUniversidad = codigoUniversidad;
	}
	public String getPrefijoCurso() {
		return prefijoCurso;
	}
	public void setPrefijoCurso(String prefijoCurso) {
		this.prefijoCurso = prefijoCurso;
	}
	public String getNumeroCurso() {
		return numeroCurso;
	}
	public void setNumeroCurso(String numeroCurso) {
		this.numeroCurso = numeroCurso;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getPeriodoAsistencia() {
		return periodoAsistencia;
	}
	public void setPeriodoAsistencia(String periodoAsistencia) {
		this.periodoAsistencia = periodoAsistencia;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getExamen() {
		return examen;
	}
	public void setExamen(String examen) {
		this.examen = examen;
	}
	
	
}
