package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonAutoDetect
@JsonSerialize
public class RespuestaInformacionPersonal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("SRespuesta")
	private String sRespuesta;
	
	
	
	

	public RespuestaInformacionPersonal(String sRespuesta) {
		this.sRespuesta = sRespuesta;
	}
	
	

	public RespuestaInformacionPersonal() {
		
	}



	public String getsRespuesta() {
		return this.sRespuesta;
	}

	public void setsRespuesta(String sRespuesta) {
		this.sRespuesta = sRespuesta;
	}
}
