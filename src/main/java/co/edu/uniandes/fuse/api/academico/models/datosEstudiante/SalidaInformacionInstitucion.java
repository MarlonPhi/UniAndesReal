package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

import org.codehaus.jackson.annotate.JsonProperty;

import co.edu.uniandes.model.EstructuraTerritorial;
import co.edu.uniandes.model.InstitucionEducativa;

public class SalidaInformacionInstitucion {
	
	
	 @JsonProperty("InstitucionEducativa")
	  private InstitucionEducativa institucionEducativa;

	  @JsonProperty("EstructuraTerritorial")
	  private EstructuraTerritorial estructuraTerritorial;

	public SalidaInformacionInstitucion(InstitucionEducativa institucionEducativa,
			EstructuraTerritorial estructuraTerritorial) {
		this.institucionEducativa = institucionEducativa;
		this.estructuraTerritorial = estructuraTerritorial;
	}

	public SalidaInformacionInstitucion() {
	}

	public InstitucionEducativa getInstitucionEducativa() {
		return institucionEducativa;
	}

	public void setInstitucionEducativa(InstitucionEducativa institucionEducativa) {
		this.institucionEducativa = institucionEducativa;
	}

	public EstructuraTerritorial getEstructuraTerritorial() {
		return estructuraTerritorial;
	}

	public void setEstructuraTerritorial(EstructuraTerritorial estructuraTerritorial) {
		this.estructuraTerritorial = estructuraTerritorial;
	}
	
	
	
	

	
	  
	  
	  

}
