package co.edu.uniandes.fuse.api.academico.models.inscripciones;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Turno {

	@JsonProperty(value = "NombreTurno")
	private String nombreTurno;
	
	@JsonProperty(value = "Prioridad")
	private String prioridad;
	
	@JsonProperty(value = "PrioridadGrupo")
	private String prioridadGrupo;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonProperty(value = "FechaInicio")	
	private Date fechaInicio;
	
	@JsonProperty(value = "HoraInicio")	
	private String horaInicio;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonProperty(value = "FechaFin")
	private Date fechaFin;
	
	@JsonProperty(value = "HoraFin")	
	private String horaFin;
	
	
	

	public Turno(String nombreTurno, String sprioridad, String prioridadGrupo, Date fechaInicio, String horaInicio,
			Date fechaFin, String horaFin) {
		this.nombreTurno = nombreTurno;
		this.prioridad = sprioridad;
		this.prioridadGrupo = prioridadGrupo;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.fechaFin = fechaFin;
		this.horaFin = horaFin;
	}
	

	public Turno() {
	}



	public String getNombreTurno() {
		return nombreTurno;
	}

	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getPrioridadGrupo() {
		return prioridadGrupo;
	}

	public void setPrioridadGrupo(String prioridadGrupo) {
		this.prioridadGrupo = prioridadGrupo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	
	
}
