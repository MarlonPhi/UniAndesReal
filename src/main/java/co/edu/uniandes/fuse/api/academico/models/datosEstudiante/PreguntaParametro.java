package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect
@JsonSerialize
public class PreguntaParametro implements Serializable {



	@JsonProperty("IsPreguntaParametrica")
	private Boolean isPreguntaParametrica;

	@JsonProperty("STableName")
	private String sTableName;

	@JsonProperty("SCodeIn")
	private String sCodeIn;

	@JsonProperty("SAnswerOut")
	private String sAnswerOut;



	public PreguntaParametro(Boolean isPreguntaParametrica, String sTableName, String sCodeIn, String sAnswerOut) {
		this.isPreguntaParametrica = isPreguntaParametrica;
		this.sTableName = sTableName;
		this.sCodeIn = sCodeIn;
		this.sAnswerOut = sAnswerOut;
	}
	
	

	public PreguntaParametro() {
	}



	public Boolean getIsPreguntaParametrica() {
		return this.isPreguntaParametrica;
	}

	public void setIsPreguntaParametrica(Boolean isPreguntaParametrica) {
		this.isPreguntaParametrica = isPreguntaParametrica;
	}

	public String getsTableName() {
		return this.sTableName;
	}

	public void setsTableName(String sTableName) {
		this.sTableName = sTableName;
	}

	public String getsCodeIn() {
		return this.sCodeIn;
	}

	public void setsCodeIn(String sCodeIn) {
		this.sCodeIn = sCodeIn;
	}

	public String getsAnswerOut() {
		return this.sAnswerOut;
	}

	public void setsAnswerOut(String sAnswerOut) {
		this.sAnswerOut = sAnswerOut;
	}
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

}
