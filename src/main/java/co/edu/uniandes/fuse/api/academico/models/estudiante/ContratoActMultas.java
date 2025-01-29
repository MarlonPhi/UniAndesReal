package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
public class ContratoActMultas {
	
	  @JsonProperty("Multa")
	  private MultaEstudiantes multa;
	  @JsonProperty("Estudiante")
	  private EstudianteMultas estudiante;

	  @JsonProperty("MensajeIn")
	  private MensajeInMultas mensaje;
	  

	public ContratoActMultas() {
	}


	public ContratoActMultas(EstudianteMultas estudiante, MultaEstudiantes multa, MensajeInMultas mensaje) {
		this.estudiante = estudiante;
		this.multa = multa;
		this.mensaje = mensaje;
	}



	public EstudianteMultas getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(EstudianteMultas estudiante) {
		this.estudiante = estudiante;
	}


	public MultaEstudiantes getMulta() {
		return multa;
	}


	public void setMulta(MultaEstudiantes multa) {
		this.multa = multa;
	}



	public MensajeInMultas getMensaje() {
		return mensaje;
	}



	public void setMensaje(MensajeInMultas mensaje) {
		this.mensaje = mensaje;
	}
	
	
	


	
	
	


	  
	  

}
