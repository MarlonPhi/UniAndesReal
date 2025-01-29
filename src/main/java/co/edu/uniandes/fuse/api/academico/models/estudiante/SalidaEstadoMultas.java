package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SalidaEstadoMultas {

	@ApiModelProperty(value="codigo respuesta", required = true, example = "302")
	@JsonProperty(value="CodigoRespuesta")
	private String codigoRespuesta = "";
	@ApiModelProperty(value="estado transaccion", required = true, example = " El pago ya estaba registrado en Symphony")
	@JsonProperty(value="EstadoTransaccion")
	private String estadoTransaccion = "";
	@ApiModelProperty(value="tipo proceso", required = true, example = "14")
	@JsonProperty(value="TipoProceso")
	private String tipoProceso = "";
	
	
	public SalidaEstadoMultas() {
	}




	public SalidaEstadoMultas(String codigoRespuesta, String estadoTransaccion, String tipoProceso) {
		this.codigoRespuesta = codigoRespuesta;
		this.estadoTransaccion = estadoTransaccion;
		this.tipoProceso = tipoProceso;
	}




	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

	public String getTipoProceso() {
		return tipoProceso;
	}

	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

}
