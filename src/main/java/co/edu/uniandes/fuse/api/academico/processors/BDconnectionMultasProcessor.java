package co.edu.uniandes.fuse.api.academico.processors;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import co.edu.uniandes.fuse.api.academico.models.estudiante.EstadoMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.SalidaEstadoMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.SimpleResponse;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import javassist.bytecode.analysis.Type;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.OracleSQLOutput;

public class BDconnectionMultasProcessor implements Processor {
	
	private final static Logger log = Logger.getLogger("");

	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		Integer itipoPago = exchange.getProperty("ITIPOPAGO", Integer.class);
		Integer scodigo = exchange.getProperty("SCODIGO", Integer.class);
		Integer ibillNumber = exchange.getProperty("IBILLNUMBER", Integer.class);
		BigDecimal imontoPagado = exchange.getProperty("IMONTOPAGADO", BigDecimal.class);
		Integer ilibrary = exchange.getProperty("ILIBRARY", Integer.class);
		
		Integer montoPagado = imontoPagado.intValue();
		
		List<SalidaEstadoMultas> salida = new ArrayList<>();
		
		String server = exchange.getProperty("server", String.class);
		String user = exchange.getProperty("user", String.class);
		String password = exchange.getProperty("password", String.class);
		 
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection conexion = DriverManager.getConnection(server, user, password);

		   try {
	
			   CallableStatement callstmt = conexion.prepareCall("{call ua_sire_multas_prog.carga_pago_bib_nube(?, ?, ?, ?, ?, ?)}");
			  
			   callstmt.setInt(1,itipoPago);
			   callstmt.setInt(2,scodigo);
			   callstmt.setInt(3,ibillNumber);
			   callstmt.setInt(4,montoPagado);
			   callstmt.setInt(5,ilibrary);
			   callstmt.registerOutParameter(6, Types.VARCHAR);
			   callstmt.execute();
			   
			   String salidaConsulta = callstmt.getString(6);
			   
			   EstadoMultas estMu = new EstadoMultas();
			   SalidaEstadoMultas estadoMul = new SalidaEstadoMultas();
			   
			   String codeResponse = "";
			   String statusTrans = "";
			   String responseTransaccion = "";
			   String responseTipo = "";
			   
			   
			   if (salidaConsulta.contains("500")) {
					   String[]  messDiv = salidaConsulta.split(";");
					   codeResponse = messDiv[0];
					   log.info("verificando salida de codeResponse: "+ codeResponse);
					   statusTrans = messDiv[1];
					   log.info("verificando statustransaccion: "+ statusTrans);
					   responseTransaccion = statusTrans.replace(":", "");
			   }else {
				   
					   String[]  messDiv = salidaConsulta.split(";");
					   codeResponse = messDiv[0];
					   statusTrans = messDiv[1];
					   String[] divStatus = statusTrans.split(":");
					   String statusReal = divStatus[0];
					   String tipo = divStatus[1];
					   responseTipo = tipo.replace(")", "");
					   responseTransaccion = statusReal.replace(". (tipo", "");
			   }
			   
			   estadoMul.setCodigoRespuesta(DataTypes.getColumnString(codeResponse));
			   estadoMul.setEstadoTransaccion(DataTypes.getColumnString(responseTransaccion));
			   estadoMul.setTipoProceso(DataTypes.getColumnString(responseTipo));
			   salida.add(estadoMul);
	
				   
			 if (!estadoMul.getEstadoTransaccion().isEmpty()) {
				 estMu.setEstadoMultas(salida);
				 exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, estadoMul.getCodigoRespuesta());
				 exchange.getIn().setBody(estMu);
			}else {
				SimpleResponse er = new SimpleResponse();
				er.setStatus(false);
				er.setMessage("No se encontrarón resultados para la respuesta correspondiente");
				er.setData(new HashMap<Object, Object>());
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				exchange.getOut().setBody(er);
				log.info("error else " + er.getMessage());
			}
			   
			conexion.close();
			   
		} catch (Exception e) {
			
			e.printStackTrace();
			SimpleResponse er = new SimpleResponse();
			er.setStatus(false);
			er.setMessage(e.getMessage());
			er.setData(new HashMap<Object, Object>());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
			exchange.getIn().setBody(er);
			log.log(Level.INFO, "entra catch  " + e.getMessage());
		}
	}
}
