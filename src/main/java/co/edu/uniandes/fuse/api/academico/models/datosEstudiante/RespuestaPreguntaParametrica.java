package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize
public class RespuestaPreguntaParametrica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("SRespuestaParametrica")
	private String sRespuestaParametrica;
	
	
	public RespuestaPreguntaParametrica(String sRespuestaParametrica) {
		this.sRespuestaParametrica = sRespuestaParametrica;
	}
	
	
	public RespuestaPreguntaParametrica() {
	}



	public String getsRespuestaParametrica() {
		return this.sRespuestaParametrica;
	}

	public void setsRespuestaParametrica(String sRespuestaParametrica) {
		this.sRespuestaParametrica = sRespuestaParametrica;
	}

}
