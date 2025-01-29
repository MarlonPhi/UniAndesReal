package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

public class SalidaInformacionSobrepaso {

	private String sSemestre;
	private int sPidm;
	private String sSobreP;
	private String sTipoAdm;
	private String sNivel;

	public SalidaInformacionSobrepaso(String sSemestre, int sPidm, String sSobreP, String sTipoAdm, String sNivel) {
		this.sSemestre = sSemestre;
		this.sPidm = sPidm;
		this.sSobreP = sSobreP;
		this.sTipoAdm = sTipoAdm;
		this.sNivel = sNivel;
	}

	public SalidaInformacionSobrepaso() {
	}

	public String getsSemestre() {
		return sSemestre;
	}

	public void setsSemestre(String sSemestre) {
		this.sSemestre = sSemestre;
	}

	public int getsPidm() {
		return sPidm;
	}

	public void setsPidm(int sPidm) {
		this.sPidm = sPidm;
	}

	public String getsSobreP() {
		return sSobreP;
	}

	public void setsSobreP(String sSobreP) {
		this.sSobreP = sSobreP;
	}

	public String getsTipoAdm() {
		return sTipoAdm;
	}

	public void setsTipoAdm(String sTipoAdm) {
		this.sTipoAdm = sTipoAdm;
	}

	public String getsNivel() {
		return sNivel;
	}

	public void setsNivel(String sNivel) {
		this.sNivel = sNivel;
	}
	  

}
