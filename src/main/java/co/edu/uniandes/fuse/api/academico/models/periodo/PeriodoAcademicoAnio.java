package co.edu.uniandes.fuse.api.academico.models.periodo;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.edu.uniandes.model.util.CanonicalDateDeserializer;
import co.edu.uniandes.model.util.CanonicalDateSerializer;

@JsonIgnoreProperties({"sAnioPeiodo","sTipoPeiodo"})
public class PeriodoAcademicoAnio {

	
	@JsonProperty(value = "DFechaInicio")	
	private String dFechaInicio;
	@JsonProperty(value = "DFechaFin")
	private String dFechaFin;
	@JsonProperty(value = "SPeriodo")
	private String sPeriodo;
	@JsonProperty(value = "SDescripcionPeriodo")
	private String sDescripcionPeriodo;
	@JsonProperty(value = "SAnioPeiodo")
	private String sAnioPeiodo;
	@JsonProperty(value = "STipoPeiodo")
	private String sTipoPeiodo;
	
	
	
	
	public PeriodoAcademicoAnio(String dFechaInicio, String dFechaFin, String sPeriodo, String sDescripcionPeriodo,
			String sAnioPeiodo, String sTipoPeiodo) {
		
		this.dFechaInicio = dFechaInicio;
		this.dFechaFin = dFechaFin;
		this.sPeriodo = sPeriodo;
		this.sDescripcionPeriodo = sDescripcionPeriodo;
		this.sAnioPeiodo = sAnioPeiodo;
		this.sTipoPeiodo = sTipoPeiodo;
	}

	public PeriodoAcademicoAnio() {
		super();
	}
	
	public String getdFechaInicio() {
		return dFechaInicio;
	}
	public void setdFechaInicio(String dFechaInicio) {
		this.dFechaInicio = dFechaInicio;
	}
	public String getdFechaFin() {
		return dFechaFin;
	}
	public void setdFechaFin(String dFechaFin) {
		this.dFechaFin = dFechaFin;
	}
	public String getsPeriodo() {
		return sPeriodo;
	}
	public void setsPeriodo(String sPeriodo) {
		this.sPeriodo = sPeriodo;
	}
	public String getsDescripcionPeriodo() {
		return sDescripcionPeriodo;
	}
	public void setsDescripcionPeriodo(String sDescripcionPeriodo) {
		this.sDescripcionPeriodo = sDescripcionPeriodo;
	}
	public String getsAnioPeiodo() {
		return sAnioPeiodo;
	}
	public void setsAnioPeiodo(String sAnioPeiodo) {
		this.sAnioPeiodo = sAnioPeiodo;
	}
	public String getsTipoPeiodo() {
		return sTipoPeiodo;
	}
	public void setsTipoPeiodo(String sTipoPeiodo) {
		this.sTipoPeiodo = sTipoPeiodo;
	}
}
