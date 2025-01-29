package co.edu.uniandes.fuse.api.academico.models.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import co.edu.uniandes.model.util.CanonicalDateDeserializer;
import co.edu.uniandes.model.util.CanonicalDateSerializer;

public class Multa {
	
	@JsonProperty(value = "SCodigo")
	private String sCodigo;
	@JsonProperty(value = "SNumeroDocumento")
	private String sNumeroDocumento;
	@JsonProperty(value = "IUserKey")
	private Integer iUserKey;
	@JsonProperty(value = "SIdItem")
	private String sIdItem;
	@JsonProperty(value = "DFechaMulta")	
	private String dFechaMulta;
	@JsonProperty(value = "DFechaPago")	
	private String dFechaPago;
	@JsonProperty(value = "DFechaVencimiento")	
	private String dFechaVencimiento;
	@JsonProperty(value = "SConceptoMulta")	
	private String sConceptoMulta;
	@JsonProperty(value = "SCodigoRazonMulta")	
	private String sCodigoRazonMulta;
	@JsonProperty(value = "SRazonMulta")	
	private String sRazonMulta;
	@JsonProperty(value = "INumeroPagos")	
	private Integer iNumeroPagos;
	@JsonProperty(value = "DMontoPagado")	
	private BigDecimal dMontoPagado;
	@JsonProperty(value  = "DMontoAbonado")	
	private BigDecimal dMontoAbonado;
	@JsonProperty(value = "DSaldo")
	private BigDecimal dSaldo;	
	@JsonProperty(value = "ICodigoTipoPago")
	private Integer iCodigoTipoPago;
	@JsonProperty(value = "STipoPago")
	private String sTipoPago;
	@JsonProperty(value = "IBillBNumber")
	private Integer iBillBNumber;
	@JsonProperty(value = "ILibrary")
	private Integer iLibrary;
	

	
	

	
	
	
	public Multa(String sCodigo, String sNumeroDocumento, Integer iUserKey, String sIdItem, String dFechaMulta,
			String dFechaPago, String dFechaVencimiento, String sConceptoMulta, String sCodigoRazonMulta,
			String sRazonMulta, Integer iNumeroPagos, BigDecimal dMontoPagado, BigDecimal dMontoAbonado,
			BigDecimal dSaldo, Integer iCodigoTipoPago, String sTipoPago, Integer iBillBNumber, Integer iLibrary) {
		
		this.sCodigo = sCodigo;
		this.sNumeroDocumento = sNumeroDocumento;
		this.iUserKey = iUserKey;
		this.sIdItem = sIdItem;
		this.dFechaMulta = dFechaMulta;
		this.dFechaPago = dFechaPago;
		this.dFechaVencimiento = dFechaVencimiento;
		this.sConceptoMulta = sConceptoMulta;
		this.sCodigoRazonMulta = sCodigoRazonMulta;
		this.sRazonMulta = sRazonMulta;
		this.iNumeroPagos = iNumeroPagos;
		this.dMontoPagado = dMontoPagado;
		this.dMontoAbonado = dMontoAbonado;
		this.dSaldo = dSaldo;
		this.iCodigoTipoPago = iCodigoTipoPago;
		this.sTipoPago = sTipoPago;
		this.iBillBNumber = iBillBNumber;
		this.iLibrary = iLibrary;
	}


	public Multa() {
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
	public Integer getiUserKey() {
		return iUserKey;
	}
	public void setiUserKey(Integer iUserKey) {
		this.iUserKey = iUserKey;
	}
	public String getsIdItem() {
		return sIdItem;
	}
	public void setsIdItem(String sIdItem) {
		this.sIdItem = sIdItem;
	}
	public String getdFechaMulta() {
		return dFechaMulta;
	}
	public void setdFechaMulta(String dFechaMulta) {
		this.dFechaMulta = dFechaMulta;
	}
	public String getdFechaPago() {
		return dFechaPago;
	}
	public void setdFechaPago(String dFechaPago) {
		this.dFechaPago = dFechaPago;
	}
	public String getdFechaVencimiento() {
		return dFechaVencimiento;
	}
	public void setdFechaVencimiento(String dFechaVencimiento) {
		this.dFechaVencimiento = dFechaVencimiento;
	}
	public String getsConceptoMulta() {
		return sConceptoMulta;
	}
	public void setsConceptoMulta(String sConceptoMulta) {
		this.sConceptoMulta = sConceptoMulta;
	}
	public String getsCodigoRazonMulta() {
		return sCodigoRazonMulta;
	}
	public void setsCodigoRazonMulta(String sCodigoRazonMulta) {
		this.sCodigoRazonMulta = sCodigoRazonMulta;
	}
	public String getsRazonMulta() {
		return sRazonMulta;
	}
	public void setsRazonMulta(String sRazonMulta) {
		this.sRazonMulta = sRazonMulta;
	}
	public Integer getiNumeroPagos() {
		return iNumeroPagos;
	}
	public void setiNumeroPagos(Integer iNumeroPagos) {
		this.iNumeroPagos = iNumeroPagos;
	}
	public BigDecimal getdMontoPagado() {
		return dMontoPagado;
	}
	public void setdMontoPagado(BigDecimal dMontoPagado) {
		this.dMontoPagado = dMontoPagado;
	}
	public BigDecimal getdMontoAbonado() {
		return dMontoAbonado;
	}
	public void setdMontoAbonado(BigDecimal dMontoAbonado) {
		this.dMontoAbonado = dMontoAbonado;
	}
	public BigDecimal getdSaldo() {
		return dSaldo;
	}
	public void setdSaldo(BigDecimal dSaldo) {
		this.dSaldo = dSaldo;
	}

	public Integer getiCodigoTipoPago() {
		return iCodigoTipoPago;
	}


	public void setiCodigoTipoPago(Integer iCodigoTipoPago) {
		this.iCodigoTipoPago = iCodigoTipoPago;
	}


	public String getsTipoPago() {
		return sTipoPago;
	}


	public void setsTipoPago(String sTipoPago) {
		this.sTipoPago = sTipoPago;
	}


	public Integer getiBillBNumber() {
		return iBillBNumber;
	}
	public void setiBillBNumber(Integer iBillBNumber) {
		this.iBillBNumber = iBillBNumber;
	}
	public Integer getiLibrary() {
		return iLibrary;
	}
	public void setiLibrary(Integer iLibrary) {
		this.iLibrary = iLibrary;
	}
	
	
	
	

}
