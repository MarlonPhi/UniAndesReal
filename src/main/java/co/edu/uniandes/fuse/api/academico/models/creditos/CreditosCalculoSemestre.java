package co.edu.uniandes.fuse.api.academico.models.creditos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CreditosCalculoSemestre {
	
	/**
	 * 
	 * @param numCreditosPorSemestre - número de creditos que tiene cada semestre
	 * @param creditosEstudiante - número de creditos qye ha visto el estudiante
	 * @return
	 */
	 public static BigDecimal calcularSemestreSegunCreditos(List<BigDecimal> numCreditosPorSemestre, BigDecimal creditosEstudiante) {
		    BigDecimal cred = creditosEstudiante;
		    int i = 0;
		    int numSemestres = numCreditosPorSemestre.size();
		    BigDecimal creditosSemActual = numCreditosPorSemestre.get(i);
		    
		    while (creditosEstudiante.compareTo(creditosSemActual) > 0 && creditosSemActual.compareTo(BigDecimal.ZERO) >= 0) {
		      creditosEstudiante = creditosEstudiante.subtract(creditosSemActual);
		      i++;
		      if (i >= numSemestres)
		        new BigDecimal(i); 
		      creditosSemActual = numCreditosPorSemestre.get(i);
		    } 
		    BigDecimal sem = new BigDecimal(i);
		    if (creditosSemActual.compareTo(BigDecimal.ZERO) > 0 && creditosEstudiante.compareTo(BigDecimal.ZERO) > 0) {
		      BigDecimal fraccionRestante = creditosEstudiante.divide(creditosSemActual, 2, RoundingMode.DOWN);
		      sem = sem.add(fraccionRestante);
		    } 
		    return sem;
		  }

}
