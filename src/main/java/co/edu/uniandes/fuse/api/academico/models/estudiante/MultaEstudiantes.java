package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class MultaEstudiantes {
	
	@JsonProperty(value = "ITipoPago")
	private Integer ITipoPago;
	@JsonProperty(value="IBillBNumber")
	private Integer IBillBNumber;
	@JsonProperty(value="DMontoPagado")
	private BigDecimal DMontoPagado;
	@JsonProperty(value="ILibrary")
	private Integer ILibrary;
	
	
	
	public MultaEstudiantes() {
		
	}
	

	public Integer getITipoPago() {
		return ITipoPago;
	}

	public void setITipoPago(Integer iTipoPagos) {
		ITipoPago = iTipoPagos;
	}

	public Integer getIBillBNumber() {
		return IBillBNumber;
	}



	public void setIBillBNumber(Integer iBillBNumbers) {
		IBillBNumber = iBillBNumbers;
	}



	public BigDecimal getDMontoPagado() {
		return DMontoPagado;
	}



	public void setDMontoPagado(BigDecimal dMontoPagados) {
		DMontoPagado = dMontoPagados;
	}



	public Integer getILibrary() {
		return ILibrary;
	}



	public void setILibrary(Integer iLibrarys) {
		ILibrary = iLibrarys;
	}




	
	

}
