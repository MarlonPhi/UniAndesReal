package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import co.edu.uniandes.fuse.api.academico.models.entity.Estudiante;
import co.edu.uniandes.fuse.api.academico.models.entity.Persona;
import co.edu.uniandes.fuse.api.academico.processors.datosEstudiante.IdentificacionEstudiante;
import co.edu.uniandes.model.Identificacion;

//@JsonIgnoreProperties({"",""})
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.DEFAULT)
public class InformacionAcudiente implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("Estudiante")
	  private Estudiante estudiante;

	  @JsonProperty("Persona")
	  private List<Persona> persona;

	  @JsonProperty("Identificacion")
	  private IdentificacionEstudiante identificacion;

	 
	  @JsonProperty("Acudiente")
	  private Acudiente acudiente;
	  
	  
	  
	  
	  

	public InformacionAcudiente(Estudiante estudiante, List<Persona> persona, IdentificacionEstudiante identificacion,
			Acudiente acudiente) {
	
		this.estudiante = estudiante;
		this.persona = persona;
		this.identificacion = identificacion;
		this.acudiente = acudiente;
	}
	
	
	

	public InformacionAcudiente() {
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

	public Acudiente getAcudiente() {
		return acudiente;
	}

	public void setAcudiente(Acudiente acudiente) {
		this.acudiente = acudiente;
	}
	  
	  
	  

}
