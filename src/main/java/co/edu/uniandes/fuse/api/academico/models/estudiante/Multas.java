package co.edu.uniandes.fuse.api.academico.models.estudiante;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import co.edu.uniandes.fuse.api.academico.models.entity.Multa;
import co.edu.uniandes.model.PrestamoRecurso;
import co.edu.uniandes.model.RecursoBibliografico;

@JsonAutoDetect
public class Multas {
	
	@JsonIgnoreProperties(value= {"sIdItem","sCodigo","sNumeroDocumento","dFechaPrestamo","dFechaDevolucion"})
	@JsonProperty(value="PrestamoRecurso")
	private PrestamoRecurso prestamoRecurso;
	
	@JsonIgnoreProperties(value= {"sIdItem","sMateria"})
	@JsonProperty(value="RecursoBibliografico")
	private RecursoBibliografico recursoBibliografico;
	
	@JsonIgnoreProperties(value= {"dFechaPago","sTipoPago","iLibrary","iCodigoTipoPago"})
	@JsonProperty(value="Multa")
	private Multa multa;
	
	public Multas(PrestamoRecurso prestamoRecurso, RecursoBibliografico recursoBibliografico, Multa multa) {
		this.prestamoRecurso = prestamoRecurso;
		this.recursoBibliografico = recursoBibliografico;
		this.multa = multa;
	}

	public Multas() {
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
