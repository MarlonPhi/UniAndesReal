package co.edu.uniandes.fuse.api.academico.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ValidatePidmDataProcessor implements Processor{

	@Override
	public void process(Exchange ex) throws Exception {
		String sdocument = ex.getIn().getHeader("snumerodocumento", String.class);
		String slogin = ex.getIn().getHeader("slogin", String.class);
		String spidm = ex.getIn().getHeader("spidm", String.class);
		String scodigo = ex.getIn().getHeader("scodigo", String.class);

		validateMinimalData(ex, sdocument, slogin, spidm, scodigo);
		
		ex.setProperty("spidm", spidm);
		ex.setProperty("snumerodocumento", sdocument);
		ex.setProperty("scodigo", scodigo);
		ex.setProperty("slogin", slogin);
		
	}
	
	private void validateMinimalData(Exchange ex, String sdocument, String slogin, String spidm, String scodigo) {
		
		int emptyCount = 0;
		
		if (spidm != null) {
			if (!spidm.trim().equals("")) {
				try {
					Long.parseLong(spidm);
				} catch (NumberFormatException e) {
					ex.setProperty("HttpErrorCode", "http.code.bad.request");
					throw new IllegalArgumentException("Campo spidm inválido.");
				}
			} else {
				emptyCount++;
			}
		} else {
			emptyCount++;
		}

		if (sdocument != null) {
			if (!sdocument.trim().equals("")) {
				try {
					Long.parseLong(sdocument);
				} catch (NumberFormatException e) {
					ex.setProperty("HttpErrorCode", "http.code.bad.request");
					throw new IllegalArgumentException("Campo snumerodocumento inválido.");
				}
			} else {
				emptyCount++;
			}
		} else {
			emptyCount++;
		}
		
		if (scodigo != null) {
			if (!scodigo.trim().equals("")) {
				try {
					Long.parseLong(scodigo);
				} catch (NumberFormatException e) {
					ex.setProperty("HttpErrorCode", "http.code.bad.request");
					throw new IllegalArgumentException("Campo scodigo inválido.");
				}
			} else {
				emptyCount++;
			}
		} else {
			emptyCount++;
		}
		
		if (slogin != null) {
			if (!slogin.trim().equals("")) {
				try {
					Long.parseLong(slogin);
					ex.setProperty("HttpErrorCode", "http.code.bad.request");
					throw new IllegalArgumentException("Campo slogin inválido.");
				} catch (NumberFormatException e) {
					//No hay error porque si es texto
				}
			} else {
				emptyCount++;
			}
		} else {
			emptyCount++;
		}

		if (emptyCount == 4) {
			ex.setProperty("HttpErrorCode", "http.code.bad.request");
			throw new IllegalArgumentException("No se ha diligenciado los campos minimos requeridos");
		}
	}

}
