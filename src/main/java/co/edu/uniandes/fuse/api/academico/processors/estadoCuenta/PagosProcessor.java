package co.edu.uniandes.fuse.api.academico.processors.estadoCuenta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.entity.Multa;
import co.edu.uniandes.fuse.api.academico.models.estudiante.Multas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.PagosMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponsePagosMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.SimpleResponse;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import co.edu.uniandes.model.PrestamoRecurso;
import co.edu.uniandes.model.RecursoBibliografico;

public class PagosProcessor implements Processor {

	private final static Logger log = Logger.getLogger("");
	SimpleResponse er = new SimpleResponse();
	ResponsePagosMultas rPagos = new ResponsePagosMultas();
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String server = exchange.getProperty("server", String.class);
		String user = exchange.getProperty("user", String.class);
		String password = exchange.getProperty("password", String.class);
		String slogin = exchange.getProperty("slogin", String.class);
		List<PagosMultas> listPagos = new ArrayList<>();
		//List<Object> datos = new ArrayList<>();
		
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection conn = DriverManager.getConnection(server, user, password);
		Statement stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,java.sql.ResultSet.CONCUR_READ_ONLY);

		String consulta1 = "{ ? = call ua_sire_multas_prog.consulta_pagos_realizados(?)}";
		
		try {
			
			CallableStatement call = conn.prepareCall(consulta1);

			call.registerOutParameter(1, -10);
			call.setString(2, String.valueOf(slogin));
			call.execute();
			
			ResultSet rset = (ResultSet) call.getObject(1);
			boolean existData = false;
			
			while (rset.next()) {
					existData = true;
					Multa multa = new Multa();
					RecursoBibliografico rb = new RecursoBibliografico();
					PrestamoRecurso pr = new PrestamoRecurso();
					PagosMultas pagos = new PagosMultas();
					
					pr.setsBiblioteca(DataTypes.getColumnString(rset.getString("BIBLIOTECA_DEUDA")));
					
					rb.setsTitulo(DataTypes.getColumnString(rset.getString("TITULO")));
					
					multa.setsCodigo(UtilsAcademico.getColumnBigDecimalToString(rset.getString("CODIGO")));
					
					String billNumb = DataTypes.getColumnString(rset.getString("NUMERO_DEUDA"));
					multa.setiBillBNumber(Integer.valueOf(billNumb));
					multa.setdMontoPagado(UtilsAcademico.getColumnBigDecimalFromString(rset.getString("VALOR_DEUDA")));
					multa.setdFechaMulta(DataTypes.getColumnString(rset.getString("FECHA_DEUDA")));
					multa.setsIdItem(DataTypes.getColumnString(rset.getString("ID_ITEM")));
					multa.setsCodigoRazonMulta(DataTypes.getColumnString(rset.getString("COD_RAZON_MULTA")));
					multa.setsRazonMulta(DataTypes.getColumnString(rset.getString("NOMBRE_RAZON_MULTA")));
					
					String CodeType = UtilsAcademico.getColumnBigDecimalToString(rset.getString("COD_TIPO_PAGO"));
					multa.setiCodigoTipoPago(Integer.valueOf(CodeType));
					multa.setsTipoPago(DataTypes.getColumnString(rset.getString("NOMBRE_TIPO_PAGO")));
					multa.setdFechaPago(DataTypes.getColumnString(rset.getString("FECHA_PAGO")));
					multa.setdMontoAbonado(UtilsAcademico.getColumnBigDecimalFromString(rset.getString("VR_PAGADO")));
					multa.setiNumeroPagos(Integer.valueOf(UtilsAcademico.getColumnBigDecimalFromString(rset.getString("NRO_PAGO")).intValue()));

					pagos.setMulta(multa);
					pagos.setPrestamoRecurso(pr);
					pagos.setRecursoBibliografico(rb);
				
					listPagos.add(pagos);
				
			}
			if (existData == true) {
				rPagos.setPagos(listPagos);
				exchange.getIn().setBody(rPagos);
			}else {
				er.setStatus(false);
				er.setMessage("No se encontrarón resultados para los parametros de busqueda");
				er.setData(new HashMap<Object, Object>());
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				exchange.getIn().setBody(er);
				log.log(Level.INFO, "error else " + er.getMessage());
			}
		} catch (Exception e) {
			er.setStatus(false);
			er.setMessage(e.getMessage());
			er.setData(new HashMap<Object, Object>());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
			exchange.getIn().setBody(er);
			log.log(Level.INFO, "entra catch  " + e.getMessage());
		}finally {
			stmt.close();
		}
	}
}
