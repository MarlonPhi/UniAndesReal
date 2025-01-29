package co.edu.uniandes.fuse.api.academico.models.estudiante.homologaciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de Homologaciones")
public class HomologacionesEstudiante {

	@JsonProperty
	private String codigo;
	@JsonProperty
	private String pidm;
	@JsonProperty
	private String nombres;
	@JsonProperty
	private String apellidos;
	@JsonProperty
	private String email;
	@JsonProperty
	private String telefonoCasa;
	@JsonProperty
	private String celular;
	@JsonProperty
	private String semestreSegunCredito;
	@JsonProperty
	private String estadoAcademico;
	@JsonProperty
	private String periodoAdmision;
	@JsonProperty
	private String colegio;
	@JsonProperty
	private String suspencioDisciplinaria;
	@JsonProperty
	private List<ProgramaHomologacion> programa;
	@JsonProperty
	private List<OpcionesAcademicasHomologacion> opcionesAcademicas;
	@JsonProperty
	private List<PeriodosSuspencionAcademicaHomologacion> periodosSuspencionAcademica;
	
	@ApiModelProperty(value = "Codigo del estudiante", required = false, example = "string")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@ApiModelProperty(value = "PIDM del estudiante", required = false, example = "0")
	public String getPidm() {
		return pidm;
	}
	public void setPidm(String pidm) {
		this.pidm = pidm;
	}
	@ApiModelProperty(value = "Nombres del estudiante", required = false, example = "string")
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	@ApiModelProperty(value = "Apellidos del estudiante", required = false, example = "string")
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	@ApiModelProperty(value = "Email del estudiante", required = false, example = "string")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@ApiModelProperty(value = "Telefono Casa del estudiante", required = false, example = "string")
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	@ApiModelProperty(value = "Celular del estudiante", required = false, example = "string")
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	@ApiModelProperty(value = "Semestres segun credito", required = false, example = "0")
	public String getSemestreSegunCredito() {
		return semestreSegunCredito;
	}
	public void setSemestreSegunCredito(String semestreSegunCredito) {
		this.semestreSegunCredito = semestreSegunCredito;
	}
	@ApiModelProperty(value = "Estado Academico del estudiante", required = false, example = "string")
	public String getEstadoAcademico() {
		return estadoAcademico;
	}
	public void setEstadoAcademico(String estadoAcademico) {
		this.estadoAcademico = estadoAcademico;
	}
	@ApiModelProperty(value = "Periodo de admision del estudiante", required = false, example = "0")
	public String getPeriodoAdmision() {
		return periodoAdmision;
	}
	public void setPeriodoAdmision(String periodoAdmision) {
		this.periodoAdmision = periodoAdmision;
	}
	@ApiModelProperty(value = "Colegio del estudiante", required = false, example = "string")
	public String getColegio() {
		return colegio;
	}
	public void setColegio(String colegio) {
		this.colegio = colegio;
	}
	@ApiModelProperty(value = "Suspencion disciplinaria del estudiante", required = false, example = "string")
	public String getSuspencioDisciplinaria() {
		return suspencioDisciplinaria;
	}
	public void setSuspencioDiciplinaria(String suspencioDisciplinaria) {
		this.suspencioDisciplinaria = suspencioDisciplinaria;
	}
	@ApiModelProperty(value = "Programas del estudiante", required = false, example = "")
	public List<ProgramaHomologacion> getPrograma() {
		return programa;
	}
	public void setPrograma(List<ProgramaHomologacion> programa) {
		this.programa = programa;
	}
	@ApiModelProperty(value = "Opciones academicas del estudiante", required = false, example = "")
	public List<OpcionesAcademicasHomologacion> getOpcionesAcademicas() {
		return opcionesAcademicas;
	}
	public void setOpcionesAcademicas(List<OpcionesAcademicasHomologacion> opcionesAcademicas) {
		this.opcionesAcademicas = opcionesAcademicas;
	}
	@ApiModelProperty(value = "Periodos Suspencion Acemica", required = false, example = "")
	public List<PeriodosSuspencionAcademicaHomologacion> getPeriodosSuspencionAcademica() {
		return periodosSuspencionAcademica;
	}
	public void setPeriodosSuspencionAcademica(List<PeriodosSuspencionAcademicaHomologacion> periodosSuspencionAcademica) {
		this.periodosSuspencionAcademica = periodosSuspencionAcademica;
	}
	
}
