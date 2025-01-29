package co.edu.uniandes.fuse.api.academico.beans.creditos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;

import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosApHomologSemestre;
import co.edu.uniandes.fuse.api.academico.models.creditos.Creditos;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosAp;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosApHomologados;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosApSemestre;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosApTransferidos;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosCalculoSemestre;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosInsSemestre;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosInt;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosIntHomologados;
import co.edu.uniandes.fuse.api.academico.models.creditos.CreditosSemCreditos;
import co.edu.uniandes.fuse.api.academico.models.creditos.TransferenciaResponse;
import co.edu.uniandes.fuse.api.academico.modelss.ResponseCode;
import co.edu.uniandes.fuse.api.academico.utils.UtilsAcademico;
import co.edu.uniandes.fuse.core.utils.beans.DataTypes;

public class CreditosService {
	
	ResponseCode rCode = new ResponseCode();
	CreditosApHomologados resCH = new CreditosApHomologados();
	CreditosApHomologSemestre resCHS = new CreditosApHomologSemestre();
	CreditosAp resCA = new CreditosAp();
	CreditosApSemestre resCAS = new CreditosApSemestre();
	CreditosApTransferidos resCAT = new CreditosApTransferidos();
	CreditosInsSemestre resCIS = new CreditosInsSemestre();
	CreditosInt resCI = new CreditosInt();
	CreditosIntHomologados resCIH = new CreditosIntHomologados();
	CreditosSemCreditos resCSC = new CreditosSemCreditos();
	private final static Integer  numSemestres = 10;
	private final static Logger log = Logger.getLogger("");
	
	
	
	public void postTransferenciaCreditos(Exchange exchange) throws Exception {
	
		
		ArrayList<Map> result = exchange.getIn().getBody(ArrayList.class);
		if (!result.isEmpty()) {
			Map<String, Object> row = result.get(0);
			TransferenciaResponse transerencia = new TransferenciaResponse();
			transerencia.setCodigo((DataTypes.getColumnString(row.get("CODIGO"))));	
			exchange.getIn().setBody(transerencia);
		} else {
			exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getAprobadosHomologados(Exchange exchange) throws Exception {
	
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		if (!result.isEmpty()) {
			for (Map<String, Object> map : result) {
				creditos.setCreditosHomologados(UtilsAcademico.getColumnBigDecimal(map.get("C_A_HOMOLOGADOS")).toPlainString());
			}
			
			resCH.setCreditos(creditos);
			exchange.getOut().setBody(resCH);
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getAprobadosHomologadosSemestre(Exchange exchange) throws Exception{
		
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		if (!result.isEmpty() && result != null) {
			for (Map<String, Object> map : result) {
				creditos.setCreditosHomologados(UtilsAcademico.getColumnBigDecimal(map.get("C_A_HOMOLOGADOS_S")).toPlainString());
			}
			
			resCHS.setCreditos(creditos);
			exchange.getOut().setBody(resCHS);
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
		
		
	}
	
	public void getAprobados(Exchange exchange) throws Exception{
		
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		
		String resulltAH = exchange.getProperty("resulltAH", String.class);
		boolean sumhomolog = exchange.getProperty("sumHomologados", Boolean.class);
		
		Creditos creditos = new Creditos();
		
		if (sumhomolog == false || String.valueOf(sumhomolog) == null) {
			if (!result.isEmpty() && result != null) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosAprobados(UtilsAcademico.getColumnBigDecimal(map.get("C_APROBADOS")).toPlainString());
				}
				resCA.setCreditos(creditos);
				exchange.getIn().setBody(resCA);
			}else {
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				rCode.setStatus(false);
				rCode.setMessage("El recurso no devuelve contenido.");
				exchange.getIn().setBody(rCode);
			}
		}else {
			if (!resulltAH.isEmpty() && resulltAH != null) {
				creditos.setCreditosHomologados(resulltAH);
				resCH.setCreditos(creditos);
				exchange.getIn().setBody(resCH);
			}else {
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				rCode.setStatus(false);
				rCode.setMessage("El recurso no devuelve contenido.");
				exchange.getIn().setBody(rCode);
			}
		}

	}
	
	public void getAprobadosSemestre(Exchange exchange) throws Exception {
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		
		if (!result.isEmpty() && result != null ) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosAprobados(UtilsAcademico.getColumnBigDecimal(map.get("C_APROBADOS_S")).toPlainString());
				}
			resCAS.setCreditos(creditos);
			exchange.getIn().setBody(resCAS);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getAprobadosTransferidos (Exchange exchange) throws Exception{
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		
		if (!result.isEmpty() && result != null ) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosTransferidos(UtilsAcademico.getColumnBigDecimal(map.get("HOURS_PASSED")).toPlainString());
				}
			resCAT.setCreditos(creditos);
			exchange.getIn().setBody(resCAT);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getInscritosSemestre (Exchange exchange) throws Exception {
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		
		if (!result.isEmpty() && result != null ) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosInscritos(UtilsAcademico.getColumnBigDecimal(map.get("CONTEO_CREDITOS")).toPlainString());
				}
				resCIS.setCreditos(creditos);
			exchange.getIn().setBody(resCIS);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getIntentadosHomologados (Exchange exchange) throws Exception{
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		
		if (!result.isEmpty() && result != null ) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosHomologados(UtilsAcademico.getColumnBigDecimal(map.get("C_I_HOMOLOGADOS")).toPlainString());
				}
			resCIH.setCreditos(creditos);
			exchange.getIn().setBody(resCIH);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getIntentados (Exchange exchange) throws Exception {
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		
		String resulltIH = exchange.getProperty("resulltIH", String.class);
		boolean sumhomolog = exchange.getProperty("sumHomologados", Boolean.class);
		
		Creditos creditos = new Creditos();
		
		if (sumhomolog == false || String.valueOf(sumhomolog) == null) {
			if (!result.isEmpty() && result != null) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosIntentados(UtilsAcademico.getColumnBigDecimal(map.get("C_INTENTADOS")).toPlainString());
				}
				resCI.setCreditos(creditos);
				exchange.getIn().setBody(resCI);
			}else {
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				rCode.setStatus(false);
				rCode.setMessage("El recurso no devuelve contenido.");
				exchange.getIn().setBody(rCode);
			}
		}else {
			if (!resulltIH.isEmpty() && resulltIH != null) {
				creditos.setCreditosHomologados(resulltIH);
				resCIH.setCreditos(creditos);
				exchange.getIn().setBody(resCIH);
			}else {
				exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
				rCode.setStatus(false);
				rCode.setMessage("El recurso no devuelve contenido.");
				exchange.getIn().setBody(rCode);
			}
		}
	}
	
	public void getIntentadosSemestre (Exchange exchange) throws Exception {
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		
		if (!result.isEmpty() && result != null ) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosIntentados(UtilsAcademico.getColumnBigDecimal(map.get("C_INTENTADOS_S")).toPlainString());
				}
			resCI.setCreditos(creditos);
			exchange.getIn().setBody(resCI);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getIntentadosHomologadosSemestre (Exchange exchange) throws Exception {
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		Creditos creditos = new Creditos();
		
		if (!result.isEmpty() && result != null ) {
				for (Map<String, Object> map : result) {
					creditos.setCreditosHomologados(UtilsAcademico.getColumnBigDecimal(map.get("C_I_HOMOLOGADOS_S")).toPlainString());
				}
			resCIH.setCreditos(creditos);
			exchange.getIn().setBody(resCIH);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getSemestre (Exchange exchange) throws Exception {
		
		Creditos creditos = new Creditos();
		BigDecimal sumTotalCreds = new BigDecimal("0");
		List<Map<String, Object>> result = exchange.getIn().getBody(ArrayList.class);
		BigDecimal creditosEstudiante = UtilsAcademico.getColumnBigDecimalFromString(exchange.getProperty("creditosIntentados", String.class));
		BigDecimal numCredSemestre  = new BigDecimal("0");
		if (!result.isEmpty() && result != null) {
			List<BigDecimal> numCredSemestres = new ArrayList<BigDecimal>();
			
			for (int i = 0; i <= numSemestres-1; i++) {
				for (Map<String, Object> map : result) {
						int numSem = i+1;
						numCredSemestre = UtilsAcademico.getColumnBigDecimal(map.get("SEM"+ numSem));
						numCredSemestres.add(numCredSemestre);	
				}
				
			}
			
		//------------------------------------- colocando directamente el calcular semestre
			BigDecimal semestre = CreditosCalculoSemestre.calcularSemestreSegunCreditos(numCredSemestres, creditosEstudiante);
			log.info("verificando salida de semestre de calcular :: "+ semestre );
			semestre = semestre.setScale(2, BigDecimal.ROUND_HALF_UP);
			creditos.setSemestreSegunCreditos(semestre.toString());
			log.info("obteniendo el semestre total:"+ creditos.getSemestreSegunCreditos().toString());
			//------------------------------------- final de colocar directamente el calcular semestre
			resCSC.setCreditos(creditos);
			exchange.getIn().setBody(resCSC);
			

			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	}
	
	public void getSemestreCalculoPeriodo (Exchange exchange) throws Exception {
		
		
		BigDecimal semestre = UtilsAcademico.getColumnBigDecimalFromString(exchange.getProperty("cInscritos", String.class));
		String valueSem = String.valueOf(semestre);
		Creditos creditos = new Creditos();
		
		if (!semestre.equals(null) && !valueSem.isEmpty()) {
			
			creditos.setSemestreSegunCreditos(semestre.toString());
			resCSC.setCreditos(creditos);
			exchange.getIn().setBody(resCSC);
			
		}else {
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
			rCode.setStatus(false);
			rCode.setMessage("El recurso no devuelve contenido.");
			exchange.getIn().setBody(rCode);
		}
	
	}
	
	
}
