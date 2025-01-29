package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Multa;
import co.edu.uniandes.model.PrestamoRecurso;
import co.edu.uniandes.model.RecursoBibliografico;

public class PagosMultas {
	
	@JsonIgnoreProperties(value= {"sIdItem","sCodigo","sNumeroDocumento","dFechaPrestamo","dFechaDevolucion"})
	@JsonProperty(value="PrestamoRecurso")
	private PrestamoRecurso prestamoRecurso;
	
	@JsonIgnoreProperties(value= {"sIdItem","sMateria","sAutor"})
	@JsonProperty(value="RecursoBibliografico")
	private RecursoBibliografico recursoBibliografico;
	
	@JsonIgnoreProperties(value= {"dFechaPago","iUserKey","iLibrary","sNumeroDocumento","dFechaVencimiento"
								,"sConceptoMulta","dSaldo","iLibrary"})
	@JsonProperty(value="Multa")
	private Multa multa;
	
	
	

	public PagosMultas() {
	}

	public PagosMultas(PrestamoRecurso prestamoRecurso, RecursoBibliografico recursoBibliografico, Multa multa) {

		this.prestamoRecurso = prestamoRecurso;
		this.recursoBibliografico = recursoBibliografico;
		this.multa = multa;
	}

	public PrestamoRecurso getPrestamoRecurso() {
		return prestamoRecurso;
	}

	public void setPrestamoRecurso(PrestamoRecurso prestamoRecurso) {
		this.prestamoRecurso = prestamoRecurso;
	}

	public RecursoBibliografico getRecursoBibliografico() {
		return recursoBibliografico;
	}

	public void setRecursoBibliografico(RecursoBibliografico recursoBibliografico) {
		this.recursoBibliografico = recursoBibliografico;
	}

	public Multa getMulta() {
		return multa;
	}

	public void setMulta(Multa multa) {
		this.multa = multa;
	}
	
	
	

}
