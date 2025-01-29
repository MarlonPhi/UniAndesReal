package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import co.edu.uniandes.model.util.CanonicalDateDeserializer;
import co.edu.uniandes.model.util.CanonicalDateSerializer;

public class Retencion {
	
	@JsonProperty(value = "SCodigoRetencion")
	private String sCodigoRetencion = "";
	@JsonProperty(value = "SOrigenRetencion")
	private String sOrigenRetencion = "";
	@JsonProperty(value = "SRazonRetencion")
	private String sRazonRetencion = "";
	@JsonProperty(value = "DFechaInicio")
	private String dFechaInicio;
	@JsonProperty(value = "DFechaFin")
	private String dFechaFin = "";
	@JsonProperty(value = "DFechaCreacionRetencion")
	private String dFechaCreacionRetencion = "";

	public Retencion(String sCodigoRetencion, String sOrigenRetencion, String sRazonRetencion, String dFechaInicio,
			String dFechaFin, String dFechaCreacionRetencion) {
		this.sCodigoRetencion = sCodigoRetencion;
		this.sOrigenRetencion = sOrigenRetencion;
		this.sRazonRetencion = sRazonRetencion;
		this.dFechaInicio = dFechaInicio;
		this.dFechaFin = dFechaFin;
		this.dFechaCreacionRetencion = dFechaCreacionRetencion;
	}

	public Retencion() {
	}

	public String getsCodigoRetencion() {
		return sCodigoRetencion;
	}

	public void setsCodigoRetencion(String sCodigoRetencion) {
		this.sCodigoRetencion = sCodigoRetencion;
	}

	public String getsOrigenRetencion() {
		return sOrigenRetencion;
	}

	public void setsOrigenRetencion(String sOrigenRetencion) {
		this.sOrigenRetencion = sOrigenRetencion;
	}

	public String getsRazonRetencion() {
		return sRazonRetencion;
	}

	public void setsRazonRetencion(String sRazonRetencion) {
		this.sRazonRetencion = sRazonRetencion;
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

	public String getdFechaCreacionRetencion() {
		return dFechaCreacionRetencion;
	}

	public void setdFechaCreacionRetencion(String dFechaCreacionRetencion) {
		this.dFechaCreacionRetencion = dFechaCreacionRetencion;
	}

	
	
	
}
