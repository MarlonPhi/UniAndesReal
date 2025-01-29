package co.edu.uniandes.fuse.api.academico.models.programa;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Modelo de Programa para Homologaciones con códigos y nombres de programa, facultad y nivel de programa
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-10-21
 */
@ApiModel(description = "Modelo de Programa para Homologaciones")
public class ProgramaHomologaciones {
		
//-------------------------------------------------------------------------------
// ----------------------------- ATTRIBUTES -------------------------------------
//-------------------------------------------------------------------------------
	@JsonProperty
	private String facultyCode;
	@JsonProperty
	private String facultyName;
	@JsonProperty
	private String departamentCode;
	@JsonProperty
	private String departamentName;
	@JsonProperty
	private String programCode;
	@JsonProperty
	private String programName;
	@JsonProperty
	private String programLevel;
	
//------------------------------------------------------------------------------
// ----------------------------- METHODS ---------------------------------------
//------------------------------------------------------------------------------	
	
	@ApiModelProperty(value= "Código facultad", required = false, example = "CI")
	public String getFacultyCode() {
		return facultyCode;
	}
	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}
	
	@ApiModelProperty(value= "Nombre facultad", required = false, example = "CIENCIAS")
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	@ApiModelProperty(value= "Codigo departamento", required = false, example = "BIOL")
	public String getDepartamentCode() {
		return departamentCode;
	}
	public void setDepartamentCode(String departamentCode) {
		this.departamentCode = departamentCode;
	}
	
	@ApiModelProperty(value= "Nombre departamento", required = false, example = "BIOLOGIA")
	public String getDepartamentName() {
		return departamentName;
	}
	public void setDepartamentName(String departamentName) {
		this.departamentName = departamentName;
	}
	
	@ApiModelProperty(value= "Codigo programa", required = false, example = "BIOL")
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	
	@ApiModelProperty(value= "Nombre programa", required = false, example = "BIOLOGIA")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	@ApiModelProperty(value= "Nivel de programa", required = false, example = "PR")
	public String getProgramLevel() {
		return programLevel;
	}
	public void setProgramLevel(String programLevel) {
		this.programLevel = programLevel;
	}
	
	
	

}
