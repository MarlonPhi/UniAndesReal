package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;


import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


import co.edu.uniandes.fuse.api.academico.models.entity.Estudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.processors.datosEstudiante.IdentificacionEstudiante;

public class InformacionResEconomico {
	


	  @JsonProperty("Estudiante")
	  private Estudiante estudiante;

	  @JsonProperty("Persona")
	  private List<Persona> persona;

	  @JsonProperty("Identificacion")
	  private IdentificacionEstudiante identificacion;

	 
	  @JsonProperty("ResponsableEconomico")
	  private ResponsableEconomico responsableEconomico;
	  
	  
	  
	  
	  

	public InformacionResEconomico(Estudiante estudiante, List<Persona> persona, IdentificacionEstudiante identificacion,
			ResponsableEconomico responsableEconomico) {
	
		this.estudiante = estudiante;
		this.persona = persona;
		this.identificacion = identificacion;
		this.responsableEconomico = responsableEconomico;
	}
	
	
	

	public InformacionResEconomico() {
	}




	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<Persona> getPersona() {
		return persona;
	}

	public void setPersona(List<Persona> persona) {
		this.persona = persona;
	}

	public IdentificacionEstudiante getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(IdentificacionEstudiante identificacion) {
		this.identificacion = identificacion;
	}

	public ResponsableEconomico getResponsableEconomico() {
		return responsableEconomico;
	}

	public void setResponsableEconomico(ResponsableEconomico responsableEconomico) {
		this.responsableEconomico = responsableEconomico;
	}
	  
	  
	  

}
