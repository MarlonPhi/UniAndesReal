package co.edu.uniandes.fuse.api.academico.models.datosEstudiante;

public class OpcionEstudiante {

	private String sNombreOpcion;
	private String sCodigoOpcion;
	private String sPeriodo;
	private int sPrioridad;

	public OpcionEstudiante(String sNombreOpcion, String sCodigoOpcion, String sPeriodo, int sPrioridad) {
		this.sNombreOpcion = sNombreOpcion;
		this.sCodigoOpcion = sCodigoOpcion;
		this.sPeriodo = sPeriodo;
		this.sPrioridad = sPrioridad;
	}

	public OpcionEstudiante() {
	}

	public String getsNombreOpcion() {
		return sNombreOpcion;
	}

	public void setsNombreOpcion(String sNombreOpcion) {
		this.sNombreOpcion = sNombreOpcion;
	}

	public String getsCodigoOpcion() {
		return sCodigoOpcion;
	}

	public void setsCodigoOpcion(String sCodigoOpcion) {
		this.sCodigoOpcion = sCodigoOpcion;
	}

	public String getsPeriodo() {
		return sPeriodo;
	}

	public void setsPeriodo(String sPeriodo) {
		this.sPeriodo = sPeriodo;
	}

	public int getsPrioridad() {
		return sPrioridad;
	}

	public void setsPrioridad(int sPrioridad) {
		this.sPrioridad = sPrioridad;
	}
	  
	  
}
