/**
 * 
 */
package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author n.ibarra
 *
 */
public class KeywordsGestEstudiante {
	
	private List<KeywordList> listaKeywordParameterStudent;
	
	
	
	public KeywordsGestEstudiante(List<KeywordList> listaKeywordParameterStudent) {
		this.listaKeywordParameterStudent = listaKeywordParameterStudent;
	}

	public KeywordsGestEstudiante() {
	}

	public List<KeywordList> getListaKeywordParameterStudent() {
		return listaKeywordParameterStudent;
	}

	public void setListaKeywordParameterStudent(List<KeywordList> listaKeywordParameterStudent) {
		this.listaKeywordParameterStudent = listaKeywordParameterStudent;
	}






	public class Keywords {
	    @JsonProperty("nombreKeyword")
	    private String nombreKeyword;

	    @JsonProperty("valueKeyword")
	    private String valueKeyword;

	    // Getters and setters

	    public String getNombreKeyword() {
	        return nombreKeyword;
	    }

	    public void setNombreKeyword(String nombreKeyword) {
	        this.nombreKeyword = nombreKeyword;
	    }

	    public String getValueKeyword() {
	        return valueKeyword;
	    }

	    public void setValueKeyword(String valueKeyword) {
	        this.valueKeyword = valueKeyword;
	    }
	}

	public class KeywordList {
	    @JsonProperty("keywords")
	    private List<Keywords> Keywords;

	    // Getter and setter

	    public List<Keywords> getKeywords() {
	        return Keywords;
	    }

	    public void setKeywords(List<Keywords> Keywords) {
	        this.Keywords = Keywords;
	    }
	}

}
