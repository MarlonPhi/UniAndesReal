package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PrestamoRecurso {
	
	@JsonProperty(value = "SIdItem")
	private String sIdItem;
	
	@JsonProperty(value = "SBiblioteca")
	private String sBiblioteca;
	
	@JsonProperty(value = "SCodigo")
	private String sCodigo;
	
	@JsonProperty(value = "SNumeroDocumento")
	private String sNumeroDocumento;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonProperty(value = "DFechaPrestamo")	
	private Date dFechaPrestamo;
	
	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonProperty(value = "DFechaDevolucion")
	private Date dFechaDevolucion;
	
	
	public String getsIdItem() {
		return sIdItem;
	}
	public void setsIdItem(String sIdItem) {
		this.sIdItem = sIdItem;
	}
	public String getsBiblioteca() {
		return sBiblioteca;
	}
	public void setsBiblioteca(String sBiblioteca) {
		this.sBiblioteca = sBiblioteca;
	}
	public String getsCodigo() {
		return sCodigo;
	}
	public void setsCodigo(String sCodigo) {
		this.sCodigo = sCodigo;
	}
	public String getsNumeroDocumento() {
		return sNumeroDocumento;
	}
	public void setsNumeroDocumento(String sNumeroDocumento) {
		this.sNumeroDocumento = sNumeroDocumento;
	}
	public Date getdFechaPrestamo() {
		return dFechaPrestamo;
	}
	public void setdFechaPrestamo(Date dFechaPrestamo) {
		this.dFechaPrestamo = dFechaPrestamo;
	}
	public Date getdFechaDevolucion() {
		return dFechaDevolucion;
	}
	public void setdFechaDevolucion(Date dFechaDevolucion) {
		this.dFechaDevolucion = dFechaDevolucion;
	}
	
	
	
	

}
