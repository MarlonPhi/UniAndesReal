package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class Horario {
	
	@JsonProperty(value="salon")
	private String salon;
	@JsonProperty(value="horaInicio")
	private String hora_inicio;
	@JsonProperty(value="horaFin")
	private String hora_fin;
	@JsonProperty(value="diaSemana")
	private List<String> dia_semana;
	
	
	
	public Horario() {
	}



	public Horario(String salon, String hora_inicio, String hora_fin, List<String> dia_semana) {
		super();
		this.salon = salon;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.dia_semana = dia_semana;
	}



	public String getSalon() {
		return salon;
	}



	public void setSalon(String salon) {
		this.salon = salon;
	}



	public String getHora_inicio() {
		return hora_inicio;
	}



	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}



	public String getHora_fin() {
		return hora_fin;
	}



	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}



	public List<String> getDia_semana() {
		return dia_semana;
	}



	public void setDia_semana(List<String> dia_semana) {
		this.dia_semana = dia_semana;
	}


}
