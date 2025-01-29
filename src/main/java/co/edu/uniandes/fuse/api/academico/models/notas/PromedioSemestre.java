package co.edu.uniandes.fuse.api.academico.models.notas;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Nota;

public class PromedioSemestre {
	
	@JsonIgnoreProperties(value= {"SPidm","SCRN","SNombreCurso","SCodigoCurso","SPeriodo","SNivel","DFechaNota","LNotasParciales"})
	@JsonProperty(value="PromedioSemestre")
	private Nota nota;

	
	
	public PromedioSemestre(Nota nota) {
		this.nota = nota;
	}



	public PromedioSemestre() {
	}



	public Nota getNota() {
		return nota;
	}


	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	
	
	
	

}
