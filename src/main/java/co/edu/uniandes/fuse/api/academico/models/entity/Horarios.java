package co.edu.uniandes.fuse.api.academico.models.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Horarios {
	
	@JsonProperty(value = "SCRN")
	private String sCRN;
	
	@JsonProperty(value = "SMateria")
	private String sMateria;
	
	@JsonProperty(value = "SSeccion")
	private String sSeccion;
	
	@JsonProperty(value = "FfechaInicio")
	private String fechaInicio;
	
	@JsonProperty(value = "FfechaFin")
	private String fechaFin;
	
	@JsonProperty(value = "SHoraInicio")
	private String sHoraInicio;
	
	@JsonProperty(value = "SHoraFin")
	private String sHoraFin;
	
	@JsonProperty(value = "SSalon")
	private String sSalon;
	
	@JsonProperty(value = "SLunes")
	private String sLunes = "";
	
	@JsonProperty(value = "SMartes")
	private String sMartes = "";
	
	@JsonProperty(value = "SMiercoles")
	private String sMiercoles = "";
	
	@JsonProperty(value = "SJueves")
	private String sJueves = "";
	
	@JsonProperty(value = "SViernes")
	private String sViernes = "";
	
	@JsonProperty(value = "SSabado")
	private String sSabado = "";
	
	@JsonProperty(value = "SDomingo")
	private String sDomingo = "";
	
	
	
	
	

	public Horarios(String sCRN, String sMateria, String sSeccion, String fechaInicio, String fechaFin, String sHoraInicio,
			String sHoraFin, String sSalon, String sLunes, String sMartes, String sMiercoles, String sJueves,
			String sViernes, String sSabado, String sDomingo) {

		this.sCRN = sCRN;
		this.sMateria = sMateria;
		this.sSeccion = sSeccion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sHoraInicio = sHoraInicio;
		this.sHoraFin = sHoraFin;
		this.sSalon = sSalon;
		this.sLunes = sLunes;
		this.sMartes = sMartes;
		this.sMiercoles = sMiercoles;
		this.sJueves = sJueves;
		this.sViernes = sViernes;
		this.sSabado = sSabado;
		this.sDomingo = sDomingo;
	}
	
	
	

	public Horarios() {
	}




	public String getsCRN() {
		return sCRN;
	}

	public void setsCRN(String sCRN) {
		this.sCRN = sCRN;
	}

	public String getsMateria() {
		return sMateria;
	}

	public void setsMateria(String sMateria) {
		this.sMateria = sMateria;
	}

	public String getsSeccion() {
		return sSeccion;
	}

	public void setsSeccion(String sSeccion) {
		this.sSeccion = sSeccion;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getsHoraInicio() {
		return sHoraInicio;
	}

	public void setsHoraInicio(String sHoraInicio) {
		this.sHoraInicio = sHoraInicio;
	}

	public String getsHoraFin() {
		return sHoraFin;
	}

	public void setsHoraFin(String sHoraFin) {
		this.sHoraFin = sHoraFin;
	}

	public String getsSalon() {
		return sSalon;
	}

	public void setsSalon(String sSalon) {
		this.sSalon = sSalon;
	}

	public String getsLunes() {
		if (sLunes == null) sLunes = ""; 
		return sLunes;
	}

	public void setsLunes(String sLunes) {
		this.sLunes = sLunes;
	}

	public String getsMartes() {
		if (sMartes == null) sMartes = "";
		return sMartes;
	}

	public void setsMartes(String sMartes) {
		this.sMartes = sMartes;
	}

	public String getsMiercoles() {
		if (sMiercoles == null) sMiercoles = "";
		return sMiercoles;
	}

	public void setsMiercoles(String sMiercoles) {
		this.sMiercoles = sMiercoles;
	}

	public String getsJueves() {
		if (sJueves == null) sJueves = "";
		return sJueves;
	}

	public void setsJueves(String sJueves) {
		this.sJueves = sJueves;
	}

	public String getsViernes() {
		if (sViernes == null) sViernes = "";
		return sViernes;
	}

	public void setsViernes(String sViernes) {
		this.sViernes = sViernes;
	}

	public String getsSabado() {
		if (sSabado == null) sSabado = "";
		return sSabado;
	}

	public void setsSabado(String sSabado) {
		this.sSabado = sSabado;
	}

	public String getsDomingo() {
		if (sDomingo == null) sDomingo = "";
		return sDomingo;
	}

	public void setsDomingo(String sDomingo) {
		this.sDomingo = sDomingo;
	}
	
	
	
	

}
