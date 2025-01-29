package co.edu.uniandes.fuse.api.academico.models.cursos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseInfoCursos {

	
	@JsonProperty(value="Cursos")
	private List<InfoCursos> cursos;
	
	

	public ResponseInfoCursos() {
	}


	public List<InfoCursos> getCursos() {
		if(this.cursos == null) this.cursos = new ArrayList<InfoCursos>();
		return cursos;
	}

	public void setCursos(List<InfoCursos> cursos) {
		this.cursos = cursos;
	}
	
	
}
