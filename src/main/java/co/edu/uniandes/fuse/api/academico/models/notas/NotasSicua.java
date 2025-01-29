package co.edu.uniandes.fuse.api.academico.models.notas;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.model.NotaParcial;

public class NotasSicua {

	@JsonProperty("NotaParcial")
	private List<NotaParcial> notaParcials;

	public NotasSicua(List<NotaParcial> notaParcials) {
		this.notaParcials = notaParcials;
	}

	public NotasSicua() {
	}

	public List<NotaParcial> getNotaParcials() {
		if (notaParcials == null) notaParcials = new ArrayList<>();
		return notaParcials;
	}

	public void setNotaParcials(List<NotaParcial> notaParcials) {
		this.notaParcials = notaParcials;
	}

}
