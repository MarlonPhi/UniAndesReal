package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistorialPrestamos {
	
	@JsonProperty(value = "SIdTitulo")
	private String sIdTitulo;
	@JsonProperty(value = "STitulo")
	private String sTitulo;
	@JsonProperty(value = "SAutor")
	private String sAutor;
	@JsonProperty(value = "SClasificacion")
	private String sClasificacion;
	@JsonProperty(value = "SIdItem")
	private String sIdItem;
	@JsonProperty(value = "SCodigo")
	private String sCodigo;
	@JsonProperty(value = "SBiblioteca")
	private String sBiblioteca;
	@JsonProperty(value = "SBibliotecaPrestamo")
	private String sBibliotecaPrestamo;
	@JsonProperty(value = "DFechaPrestamo")
	private String dFechaPrestamo;
	@JsonProperty(value = "DFechaVencimiento")
	private String dFechaVencimiento;
	@JsonProperty(value = "DFechaRenovacion")
	private String dFechaRenovacion;
	@JsonProperty(value = "SRenovaciones")
	private String sRenovaciones;
	@JsonProperty(value = "DFechaDevolucion")
	private String dFechaDevolucion;
	@JsonProperty(value = "SMulta")
	private String sMulta;
	@JsonProperty(value = "SBibliotecaDevolucion")
	private String sBibliotecaDevolucion;
	@JsonProperty(value = "SEstado")
	private boolean sEstado;
	
	
	
	public HistorialPrestamos() {
	}
	
	

	
	
	public HistorialPrestamos(String sIdTitulo, String sTitulo, String sAutor, String sClasificacion, String sIdItem,
			String sCodigo, String sBiblioteca, String sBibliotecaPrestamo, String dFechaPrestamo,
			String dFechaVencimiento, String dFechaRenovacion, String sRenovaciones, String dFechaDevolucion,
			String sMulta, String sBibliotecaDevolucion, boolean sEstado) {
		super();
		this.sIdTitulo = sIdTitulo;
		this.sTitulo = sTitulo;
		this.sAutor = sAutor;
		this.sClasificacion = sClasificacion;
		this.sIdItem = sIdItem;
		this.sCodigo = sCodigo;
		this.sBiblioteca = sBiblioteca;
		this.sBibliotecaPrestamo = sBibliotecaPrestamo;
		this.dFechaPrestamo = dFechaPrestamo;
		this.dFechaVencimiento = dFechaVencimiento;
		this.dFechaRenovacion = dFechaRenovacion;
		this.sRenovaciones = sRenovaciones;
		this.dFechaDevolucion = dFechaDevolucion;
		this.sMulta = sMulta;
		this.sBibliotecaDevolucion = sBibliotecaDevolucion;
		this.sEstado = sEstado;
	}





	public String getsIdTitulo() {
		return sIdTitulo;
	}
	public void setsIdTitulo(String sIdTitulo) {
		this.sIdTitulo = sIdTitulo;
	}
	public String getsTitulo() {
		return sTitulo;
	}
	public void setsTitulo(String sTitulo) {
		this.sTitulo = sTitulo;
	}
	public String getsAutor() {
		return sAutor;
	}
	public void setsAutor(String sAutor) {
		this.sAutor = sAutor;
	}
	public String getsClasificacion() {
		return sClasificacion;
	}
	public void setsClasificacion(String sClasificacion) {
		this.sClasificacion = sClasificacion;
	}
	public String getsIdItem() {
		return sIdItem;
	}
	public void setsIdItem(String sIdItem) {
		this.sIdItem = sIdItem;
	}
	public String getsCodigo() {
		return sCodigo;
	}
	public void setsCodigo(String sCodigo) {
		this.sCodigo = sCodigo;
	}
	public String getsBiblioteca() {
		return sBiblioteca;
	}
	public void setsBiblioteca(String sBiblioteca) {
		this.sBiblioteca = sBiblioteca;
	}
	public String getsBibliotecaPrestamo() {
		return sBibliotecaPrestamo;
	}
	public void setsBibliotecaPrestamo(String sBibliotecaPrestamo) {
		this.sBibliotecaPrestamo = sBibliotecaPrestamo;
	}
	public String getdFechaPrestamo() {
		return dFechaPrestamo;
	}
	public void setdFechaPrestamo(String dFechaPrestamo) {
		this.dFechaPrestamo = dFechaPrestamo;
	}
	public String getdFechaVencimiento() {
		return dFechaVencimiento;
	}
	public void setdFechaVencimiento(String dFechaVencimiento) {
		this.dFechaVencimiento = dFechaVencimiento;
	}
	public String getdFechaRenovacion() {
		return dFechaRenovacion;
	}
	public void setdFechaRenovacion(String dFechaRenovacion) {
		this.dFechaRenovacion = dFechaRenovacion;
	}
	public String getsRenovaciones() {
		return sRenovaciones;
	}
	public void setsRenovaciones(String sRenovaciones) {
		this.sRenovaciones = sRenovaciones;
	}
	public String getdFechaDevolucion() {
		return dFechaDevolucion;
	}
	public void setdFechaDevolucion(String dFechaDevolucion) {
		this.dFechaDevolucion = dFechaDevolucion;
	}
	public String getsMulta() {
		return sMulta;
	}
	public void setsMulta(String sMulta) {
		this.sMulta = sMulta;
	}
	public String getsBibliotecaDevolucion() {
		return sBibliotecaDevolucion;
	}
	public void setsBibliotecaDevolucion(String sBibliotecaDevolucion) {
		this.sBibliotecaDevolucion = sBibliotecaDevolucion;
	}
	public boolean issEstado() {
		return sEstado;
	}
	public void setsEstado(boolean sEstado) {
		this.sEstado = sEstado;
	}
	
	

}
