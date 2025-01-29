package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.edu.uniandes.fuse.api.academico.models.estudiante.ContratoEZProxy;

public class ConvertBodyEzProxyProcessor implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		
		ContratoEZProxy contrato = new ContratoEZProxy();
		
		contrato.setUsername(ex.getProperty("username", String.class));
		contrato.setPassword(ex.getProperty("password", String.class));
		contrato.setCliente(ex.getProperty("cliente", String.class));
		contrato.setUsers_vip(ex.getProperty("usersVip", String.class));
		contrato.setAccess_vip(ex.getProperty("accessVip", String.class));
		
		ex.getIn().setBody(contrato, ContratoEZProxy.class);

	}

}
