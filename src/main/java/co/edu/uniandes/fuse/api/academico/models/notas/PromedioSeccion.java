package co.edu.uniandes.fuse.api.academico.models.notas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Nota;

public class PromedioSeccion {

	@JsonIgnoreProperties(value= {"LNotasParciales"})
	@JsonProperty(value="PromedioSeccion")
	private List<Nota> nota;

	public PromedioSeccion(List<Nota> nota) {
		this.nota = nota;
	}

	public PromedioSeccion() {
		
	}

	public List<Nota> getNota() {
		return nota;
	}

	public void setNota(List<Nota> nota) {
		this.nota = nota;
	}
	
	
	





	
	
	
}
