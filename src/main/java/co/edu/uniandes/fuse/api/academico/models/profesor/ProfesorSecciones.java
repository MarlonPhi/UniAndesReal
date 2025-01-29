package co.edu.uniandes.fuse.api.academico.models.profesor;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Profesor Secciones")
public class ProfesorSecciones {

	@JsonProperty
	private boolean profesorPrincipal;
	@JsonProperty
	private String dedicacionSeccion;
	@JsonProperty
	private String responsabilidadSeccion;
	@JsonProperty
	private String crn;
	@JsonProperty
	private String nombreCurso;
	@JsonProperty
	private String nombreCursoLargo;
	@JsonProperty
	private String materia;
	@JsonProperty
	private String seccion;
	@JsonProperty
	private String numero;
		
	@ApiModelProperty(value = "Es profesor principal", required = false, example = "true")
	public boolean isProfesorPrincipal() {
		return profesorPrincipal;
	}
	public void setProfesorPrincipal(boolean profesorPrincipal) {
		this.profesorPrincipal = profesorPrincipal;
	}
	@ApiModelProperty(value = "Dedicación sección", required = false, example = "100")
	public String getDedicacionSeccion() {
		return dedicacionSeccion;
	}
	public void setDedicacionSeccion(String dedicacionSeccion) {
		this.dedicacionSeccion = dedicacionSeccion;
	}
	@ApiModelProperty(value = "Responsabilidad de sección", required = false, example = "100")
	public String getResponsabilidadSeccion() {
		return responsabilidadSeccion;
	}
	public void setResponsabilidadSeccion(String responsabilidadSeccion) {
		this.responsabilidadSeccion = responsabilidadSeccion;
	}
	@ApiModelProperty(value = "CRN", required = false, example = "19434")
	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	@ApiModelProperty(value = "Nombre del curso corto", required = false, example = "INGENIERIA DE TELETRAFICO")
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	@ApiModelProperty(value = "Nombre del curso largo", required = false, example = "INGENIERIA DE TELETRAFICO")
	public String getNombreCursoLargo() {
		return nombreCursoLargo;
	}
	public void setNombreCursoLargo(String nombreCursoLargo) {
		this.nombreCursoLargo = nombreCursoLargo;
	}
	@ApiModelProperty(value = "Materia", required = false, example = "IELE")
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	@ApiModelProperty(value = "Sección", required = false, example = "1")
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	@ApiModelProperty(value = "Número", required = false, example = "3420")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
	
	
	
	
	
	
	
}
