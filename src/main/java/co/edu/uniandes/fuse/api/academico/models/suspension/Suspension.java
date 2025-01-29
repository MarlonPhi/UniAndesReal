package co.edu.uniandes.fuse.api.academico.models.suspension;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Suspension {
	
	
	@JsonProperty(value = "STipoSuspension")
	private String sTipoSuspension = "";
	@JsonProperty(value = "SPeriodo")
	private String sPeriodo = "";
	@JsonProperty(value = "SCodigoSuspencion")
	private String sCodigoSuspencion = "";
	@JsonProperty(value = "DSuspensionDiscInicio")
	private String dSuspensionDiscInicio = "";
	@JsonProperty(value = "DSuspenionDiscFin")
	private String dSuspenionDiscFin = "";
	@JsonProperty(value = "DescipcionSuspDisc")
	private String sDescipcionSuspDisc = "";
	@JsonProperty(value = "SsRazonSuspDisc")
	private String sRazonSuspDisc = "";
	
	
	public Suspension(String sTipoSuspension, String sPeriodo, String sCodigoSuspencion, String dSuspensionDiscInicio,
			String dSuspenionDiscFin, String sDescipcionSuspDisc, String sRazonSuspDisc) {
		this.sTipoSuspension = sTipoSuspension;
		this.sPeriodo = sPeriodo;
		this.sCodigoSuspencion = sCodigoSuspencion;
		this.dSuspensionDiscInicio = dSuspensionDiscInicio;
		this.dSuspenionDiscFin = dSuspenionDiscFin;
		this.sDescipcionSuspDisc = sDescipcionSuspDisc;
		this.sRazonSuspDisc = sRazonSuspDisc;
	}

	public Suspension() {
	}

	public String getsTipoSuspension() {
		return sTipoSuspension;
	}
	
	public void setsTipoSuspension(String sTipoSuspension) {
		this.sTipoSuspension = sTipoSuspension;
	}
	
	public String getsPeriodo() {
		return sPeriodo;
	}
	
	public void setsPeriodo(String sPeriodo) {
		this.sPeriodo = sPeriodo;
	}
	
	public String getsCodigoSuspencion() {
		return sCodigoSuspencion;
	}
	
	public void setsCodigoSuspencion(String sCodigoSuspencion) {
		this.sCodigoSuspencion = sCodigoSuspencion;
	}	
	
	public String getdSuspensionDiscInicio() {
		return dSuspensionDiscInicio;
	}
	
	public void setdSuspensionDiscInicio(String dSuspensionDiscInicio) {
		this.dSuspensionDiscInicio = dSuspensionDiscInicio;
	}
	
	public String getdSuspenionDiscFin() {
		return dSuspenionDiscFin;
	}
	
	public void setdSuspenionDiscFin(String dSuspenionDiscFin) {
		this.dSuspenionDiscFin = dSuspenionDiscFin;
	}
	
	public String getsDescipcionSuspDisc() {
		return sDescipcionSuspDisc;
	}
	
	public void setsDescipcionSuspDisc(String sDescipcionSuspDisc) {
		this.sDescipcionSuspDisc = sDescipcionSuspDisc;
	}
	
	public String getsRazonSuspDisc() {
		return sRazonSuspDisc;
	}
	
	public void setsRazonSuspDisc(String sRazonSuspDisc) {
		this.sRazonSuspDisc = sRazonSuspDisc;
	}

}
