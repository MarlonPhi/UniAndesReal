package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.uniandes.fuse.api.academico.models.estudiante.ContratoEZProxy;

public class ValidateParamsEzProxyProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		ContratoEZProxy contrato = new ContratoEZProxy();
		
		if (exchange.getProperty("request") instanceof ContratoEZProxy) {
			
			contrato = (ContratoEZProxy) exchange.getProperty("request");
			//String requestJson = new ObjectMapper().writeValueAsString(contrato);
			//exchange.setProperty("requestAudit", requestJson);
			String usuariosPublicos = exchange.getProperty("clientesPublicos", String.class);
			
			boolean esPublico = validarUsuarioPublico(contrato.getCliente(), usuariosPublicos);
			
			exchange.setProperty("esPublico", esPublico);
			String msg = validate(contrato, esPublico);
			
				if (msg != null) {
					exchange.setProperty("continua", "0");
					exchange.setProperty(Exchange.CONTENT_TYPE,"plain/text; charset=UTF-8");
					exchange.getIn().setBody(msg, String.class);
					
					//exchange.getIn().setHeader("continua", "0");
					//exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "plain/text; charset=UTF-8");
					
				} else {
					//exchange.getIn().setHeader("continua", "1");
					exchange.setProperty("continua", "1");
				}
		}

	}
	
	/**
	 * Metodo de validación de contrato con sus correspondientes valores de usuario,
	 * y validación de el usuario de acceso VIP.
	 * @param contrato (ContratoEzProxy)
	 * @param esPublico (valor boolean - validado por metodo "validarUsuarioPublico")
	 * @return - String - retorna un mensaje de validacion de contrato o el mensaje
	 * de validación de acceso VIP.
	 */
	private String validate(ContratoEZProxy contrato, boolean esPublico) {
		//VALIDACIÓN PARÁMETROS	 
		String msg = null;
		if ((contrato.getUsername() == null) || (contrato.getUsername().trim().equals(""))) {
			msg = "Campo Username vacío o inválido";
		}

		else if ((contrato.getPassword() == null) || (contrato.getPassword().trim().equals(""))) {
			msg = "Campo Password vacío o inválido";
		}

		else if ((contrato.getCliente() == null) || (contrato.getCliente().trim().equals(""))) {
			msg = "Campo Cliente vacío o inválido";
		}
		
		if (!esPublico) {
			//VALIDACIÓN ACCESO VIP:
			String usernameTemp = contrato.getUsername();
			String users_vip = contrato.getUsers_vip();
			String access_vip = contrato.getAccess_vip();

			access_vip = access_vip.toUpperCase();
			users_vip = (";" + users_vip.replace(" ", "") + ";").toLowerCase();
			usernameTemp = ";" + usernameTemp.toLowerCase() + ";";

			// SI ESTÁ INGRESANDO UN USUARIO QUE NO ES VIP (comunidad uniandina), VERIFICA
			// QUE ASÍ SEA, DE LO CONTRARIO,
			// SI ES USUARIO VIP, ESTE SCRIPT LE NIEGA EL ACCESO PARA QUE EL EZPROXY SALTE A
			// LA CONFIGURACIÓN CORRESPONDIENTE PARA LOS USUARIOS VIP.
			if (access_vip.equals("NO")) {
				Boolean pos = users_vip.contains(usernameTemp);
				if (pos != false) {
					msg = "+ES usuario vip"; // INDICA AL EZPROXY QUE NO LO DEJE ENTRAR
				}
			}

			// LUEGO DE QUE EL EZPROXY SALTE A LA CONFIGURACIÓN CORRESPONDIENTE PARA LOS
			// USUARIOS VIP,
			// SE RE-VERIFICA QUE EL USUARIO ESTÉ DENTRO DE LOS USUARIOS VIP QUE SE SUPONE
			// QUE SON, DE LO CONTRARIO ESTE SCRIPT LE NIEGA EL ACCESO.

			else if (access_vip.equals("YES")) {

				Boolean pos = users_vip.contains(usernameTemp);
				if (pos == false) {
					msg = "+NO ES usuario vip"; // INDICA AL EZPROXY QUE NO LO DEJE ENTRAR
				}
			}
		}

		return msg;
	}
	
	/**
	 * Metodo de validación de usuario publico comparado por parametros predefinidos.
	 * @param cliente (Tipo de cliente)
	 * @param usuariosPublicos (Parametros predefinidos de Usuarios).
	 * @return - Boolean - (Retorna true o false si dentro de los parametros predefinidos se encuentra
	 * el tipo de cliente).
	 */
	private boolean validarUsuarioPublico(String cliente, String usuariosPublicos) {
		ArrayList<String> listaUsuariosPublicos = new ArrayList<String>(Arrays.asList(usuariosPublicos.split("\\|")));
		if (listaUsuariosPublicos.contains(cliente.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}

}
