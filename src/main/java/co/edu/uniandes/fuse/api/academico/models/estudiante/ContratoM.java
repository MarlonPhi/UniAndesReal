package co.edu.uniandes.fuse.api.academico.models.estudiante;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modelo de actualizacion de multas")
public class ContratoM {
	
	@ApiModelProperty(value="PIDM del estudiante", required = true, example = "177431")
	@JsonProperty
	private String SPidm;
	@ApiModelProperty(value="Tipo de pago", required = true, example = "25")
	@JsonProperty
	private Integer ITipoPago;
	@ApiModelProperty(value="Bill number", required = true, example = "1")
	@JsonProperty
	private Integer IBillBNumber;
	@ApiModelProperty(value="Monto pagado", required = true, example = "90000")
	@JsonProperty
	private BigDecimal DMontoPagado;
	@ApiModelProperty(value="id libreria", required = true, example = "1")
	@JsonProperty
	private Integer ILibrary;
	@ApiModelProperty(value="id canal", required = true, example = "1")
	@JsonProperty
	private String ICanal;
	@ApiModelProperty(value="id proceso", required = true, example = "procesos")
	@JsonProperty
	private String SIdentificadorProceso;
	@ApiModelProperty(value="host", required = true, example = "1")
	@JsonProperty
	private String SHost;
	@ApiModelProperty(value="Nombre operacion", required = true, example = "actualizarMultasEstudiante")
	@JsonProperty
	private String SNombreOperacion;
	@ApiModelProperty(value="usuario", required = true, example = "1")
	@JsonProperty
	private String SUsuario;
	@ApiModelProperty(value="version", required = true, example = "1")
	@JsonProperty
	private String SVersion;
	@ApiModelProperty(value="origen", required = true, example = "1")
	@JsonProperty
	private String SOrigenCliente;
	
	
	public String getSPidm() {
		return SPidm;
	}
	public void setSPidm(String sPidm) {
		SPidm = sPidm;
	}
	public Integer getITipoPago() {
		return ITipoPago;
	}
	public void setITipoPago(Integer iTipoPago) {
		ITipoPago = iTipoPago;
	}
	public Integer getIBillBNumber() {
		return IBillBNumber;
	}
	public void setIBillBNumber(Integer iBillBNumber) {
		IBillBNumber = iBillBNumber;
	}
	public BigDecimal getDMontoPagado() {
		return DMontoPagado;
	}
	public void setDMontoPagado(BigDecimal dMontoPagado) {
		DMontoPagado = dMontoPagado;
	}
	public Integer getILibrary() {
		return ILibrary;
	}
	public void setILibrary(Integer iLibrary) {
		ILibrary = iLibrary;
	}
	public String getICanal() {
		return ICanal;
	}
	public void setICanal(String iCanal) {
		ICanal = iCanal;
	}
	public String getSIdentificadorProceso() {
		return SIdentificadorProceso;
	}
	public void setSIdentificadorProceso(String sIdentificadorProceso) {
		SIdentificadorProceso = sIdentificadorProceso;
	}
	public String getSHost() {
		return SHost;
	}
	public void setSHost(String sHost) {
		SHost = sHost;
	}
	public String getSNombreOperacion() {
		return SNombreOperacion;
	}
	public void setSNombreOperacion(String sNombreOperacion) {
		SNombreOperacion = sNombreOperacion;
	}
	public String getSUsuario() {
		return SUsuario;
	}
	public void setSUsuario(String sUsuario) {
		SUsuario = sUsuario;
	}
	public String getSVersion() {
		return SVersion;
	}
	public void setSVersion(String sVersion) {
		SVersion = sVersion;
	}
	public String getSOrigenCliente() {
		return SOrigenCliente;
	}
	public void setSOrigenCliente(String sOrigenCliente) {
		SOrigenCliente = sOrigenCliente;
	}
	
	
	
	

}
