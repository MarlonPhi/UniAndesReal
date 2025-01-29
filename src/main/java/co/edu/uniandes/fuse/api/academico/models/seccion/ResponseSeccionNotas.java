package co.edu.uniandes.fuse.api.academico.models.seccion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Nota;

public class ResponseSeccionNotas {
	
	@JsonIgnoreProperties(value= {"SNombreCurso","SCodigoCurso","SPeriodo","SNivel","LNotasParciales"})
	@JsonProperty("Nota")
	private List<Nota> notas;
	
	
	

	public ResponseSeccionNotas() {
	}

	public ResponseSeccionNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
	

}
