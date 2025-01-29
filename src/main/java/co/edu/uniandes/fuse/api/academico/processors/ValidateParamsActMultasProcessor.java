package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.estudiante.ContratoActMultas;

public class ValidateParamsActMultasProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		

		if ((exchange.getIn().getBody() instanceof ContratoActMultas)) {
			
			 ContratoActMultas contrato = (ContratoActMultas)exchange.getIn().getBody(ContratoActMultas.class);
		      String request = new ObjectMapper().writeValueAsString(contrato);
		      exchange.setProperty("requestString", request);
		      validate(contrato);
		      
		      if (contrato.getMensaje().getSHost() != null) {
		            contrato.getMensaje().setSHost(contrato.getMensaje().getSHost().trim());
		        }
		      if (contrato.getMensaje().getSOrigenCliente() != null) {
		            contrato.getMensaje().setSOrigenCliente(contrato.getMensaje().getSOrigenCliente().trim());
		        }
		      if (contrato.getEstudiante().getsCodigo() != null) {
		        	contrato.getEstudiante().setsCodigo(contrato.getEstudiante().getsCodigo().trim());
		        }
		          
		}
	}
	
	private void validate(ContratoActMultas contrato) {
		
	    String msg = null;

	    if (contrato.getEstudiante() == null) {
	      msg = "elemento Estudiante requerido";
	    }
	    else if ((contrato.getEstudiante().getsPidm() == null) || (contrato.getEstudiante().getsPidm().trim().equals("")))
	    {
	      msg = "campo sCodigo requerido";
	    }
	    else if (contrato.getMensaje() == null) {
	      msg = "elemento MensajeIn requerido";
	    }
	    else if ((contrato.getMensaje().getSNombreOperacion() == null) || (contrato.getMensaje().getSNombreOperacion().trim().equals("")))
	    {
	      msg = "campo SNombreOperacion requerido";
	    }
	    else if ((contrato.getMensaje().getSHost() == null) || (contrato.getMensaje().getSHost().trim().equals("")))
	    {
	      msg = "campo SHost requerido";
	    }
	    else if ((contrato.getMensaje().getSOrigenCliente() == null) || (contrato.getMensaje().getSOrigenCliente().trim().equals("")))
	    {
	      msg = "campo SOrigenCliente requerido";
	    }
	    else if (contrato.getMulta() == null) {
	      msg = "elemento Multa requerido";
	    }
	    else if ((contrato.getMulta().getITipoPago() == null) || (contrato.getMulta().getITipoPago().equals(""))) {
	      msg = "campo ITipoPago requerido";
	    }
	    else if ((contrato.getMulta().getIBillBNumber() == null) || (contrato.getMulta().getIBillBNumber().equals("")))
	    {
	      msg = "campo IBillBNumber requerido";
	    }
	    else if ((contrato.getMulta().getDMontoPagado() == null) || (contrato.getMulta().getDMontoPagado().equals("")))
	    {
	      msg = "campo DMontoPagado requerido";
	    }
	    else if ((contrato.getMulta().getILibrary() == null) || (contrato.getMulta().getILibrary().equals("")))
	    {
	      msg = "campo ILibrary requerido";
	    }

	    if (msg != null)
	      throw new IllegalArgumentException(msg);
		
	}

}
