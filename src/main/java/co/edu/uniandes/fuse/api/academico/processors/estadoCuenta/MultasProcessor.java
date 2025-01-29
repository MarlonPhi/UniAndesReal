package co.edu.uniandes.fuse.api.academico.processors.estadoCuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.entity.Multa;
import co.edu.uniandes.fuse.api.academico.models.estudiante.Multas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.ResponseMultas;
import co.edu.uniandes.fuse.api.academico.models.estudiante.SimpleResponse;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;
import co.edu.uniandes.model.PrestamoRecurso;
import co.edu.uniandes.model.RecursoBibliografico;

public class MultasProcessor implements Processor {

	private final static Logger LOGGER = Logger.getLogger("");
	SimpleResponse er = new SimpleResponse();
	ResponseMultas rMultas = new ResponseMultas();

	@Override
	public void process(Exchange exchange) throws Exception {

		String server = exchange.getProperty("server", String.class);
		String user = exchange.getProperty("user", String.class);
		String password = exchange.getProperty("password", String.class);
		String slogin = exchange.getProperty("slogin", String.class);

		List<Multas> listMultas = new ArrayList<>();


		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection conn = DriverManager.getConnection(server, user, password);
		Statement stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,java.sql.ResultSet.CONCUR_READ_ONLY);


		String consulta1 = "{ ? = call ua_sire_multas_prog.consultar(?)}";

		try {

			CallableStatement call = conn.prepareCall(consulta1);

			call.registerOutParameter(1, -10);
			call.setString(2, String.valueOf(slogin));
			call.execute();

			ResultSet rset = (ResultSet) call.getObject(1);
			
			boolean existData = false;
			
			


			while (rset.next()) {
				existData = true;
				
				RecursoBibliografico rb = new RecursoBibliografico();
				Multa multa = new Multa();
				PrestamoRecurso pr = new PrestamoRecurso();
				Multas multas = new Multas();

				pr.setsBiblioteca(DataTypes.getColumnString(rset.getString("LIBRARY")));
				rb.setsTitulo(UtilsAcademico.getColumnString(rset.getString("TITULO")));
				rb.setsAutor(UtilsAcademico.getColumnString(rset.getString("NOMBRE")));

				multa.setsCodigo(UtilsAcademico.getColumnBigDecimalToString(rset.getString("CODIGO")).toString());
				String llave = DataTypes.getColumnString(rset.getString("USER_KEY"));
				multa.setiUserKey(Integer.valueOf(llave));

				multa.setsNumeroDocumento(UtilsAcademico.getColumnString(rset.getString("DOCUMENTO")));
				multa.setsIdItem(UtilsAcademico.getColumnString(rset.getString("ID_ITEM")));
				String billNumb = DataTypes.getColumnString(rset.getString("BILL_NUMBER"));
				multa.setiBillBNumber(Integer.valueOf(billNumb));

				multa.setdFechaVencimiento(DataTypes.getColumnString(rset.getString("FECHA_VENCIMIENTO")));
				multa.setdFechaMulta(DataTypes.getColumnString(rset.getString("FECHA_MULTA")));

				multa.setsConceptoMulta(UtilsAcademico.getColumnString(rset.getString("CONCEPTO_PAGO")));
				multa.setsCodigoRazonMulta(DataTypes.getColumnString(rset.getString("COD_RAZON")));
				multa.setsRazonMulta(UtilsAcademico.getColumnString(rset.getString("NOM_RAZON")));
				String temp = UtilsAcademico.getColumnString((rset.getString("NUMBER_OF_PAYMENTS")));
				multa.setiNumeroPagos(Integer.valueOf(temp));

				multa.setdMontoPagado(UtilsAcademico.getColumnBigDecimalFromString(rset.getString("AMOUNT_BILLED")));
				multa.setdMontoAbonado(UtilsAcademico.getColumnBigDecimalFromString(rset.getString("ABONO")));
				multa.setdSaldo(UtilsAcademico.getColumnBigDecimalFromString(rset.getString("SALDO")));


				multas.setPrestamoRecurso(pr);
				multas.setRecursoBibliografico(rb);
				multas.setMulta(multa);

				listMultas.add(multas);

			}

			if (existData == true) {
				rMultas.setMultas(listMultas);
				exchange.getIn().setBody(rMultas);
			} else {
				er.setStatus(false);
				er.setMessage("No se encontrarón resultados para los parametros de busqueda");
				er.setData(new HashMap<Object, Object>());
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				exchange.getIn().setBody(er);
				LOGGER.log(Level.INFO, "error else " + er.getMessage());
			}

		} catch (Exception e) {

			er.setStatus(false);
			er.setMessage(e.getMessage());
			er.setData(new HashMap<Object, Object>());
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
			exchange.getIn().setBody(er);
			LOGGER.log(Level.INFO, "entra catch  " + e.getMessage() + " MAS MENSAJE " + e.getCause() + "  mas-->"
					+ e.getLocalizedMessage());
		} finally {
			stmt.close();
		}

	}

}
