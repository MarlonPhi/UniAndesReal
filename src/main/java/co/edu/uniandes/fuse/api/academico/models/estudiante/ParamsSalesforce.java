package co.edu.uniandes.fuse.api.academico.models.estudiante;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParamsSalesforce {
	
	@JsonProperty("parametrosAspiranteBanner")
	private ParamsAspiranteBanner parametrosAspiranteBanner;
	
	@JsonProperty("parametrosEstudiosBanner")
	private ParamsEstudiosBanner parametrosEstudiosBanner;
	
	

	public ParamsSalesforce() {
	}

	public ParamsSalesforce(ParamsAspiranteBanner parametrosAspiranteBanner,
			ParamsEstudiosBanner parametrosEstudiosBanner) {
		this.parametrosAspiranteBanner = parametrosAspiranteBanner;
		this.parametrosEstudiosBanner = parametrosEstudiosBanner;
	}

	public ParamsAspiranteBanner getParametrosAspiranteBanner() {
		return parametrosAspiranteBanner;
	}

	public void setParametrosAspiranteBanner(ParamsAspiranteBanner parametrosAspiranteBanner) {
		this.parametrosAspiranteBanner = parametrosAspiranteBanner;
	}

	public ParamsEstudiosBanner getParametrosEstudiosBanner() {
		return parametrosEstudiosBanner;
	}

	public void setParametrosEstudiosBanner(ParamsEstudiosBanner parametrosEstudiosBanner) {
		this.parametrosEstudiosBanner = parametrosEstudiosBanner;
	}
	
	public class keywordsSalesforce {
		
		@JsonProperty("nombreParam")
		private String nombreParam;
		
		@JsonProperty("valueParam")
		private String valueParam;
		

		public String getNombreParam() {
			return nombreParam;
		}

		public void setNombreParam(String nombreParam) {
			this.nombreParam = nombreParam;
		}

		public String getValueParam() {
			return valueParam;
		}

		public void setValueParam(String valueParam) {
			this.valueParam = valueParam;
		}
		
	}
	
	public class ParamsAspiranteBanner {
		
		private String usuarioFormulario;
		private String apellido;
		private String nombre;
		private String email;
		private String telefono;
		private String celular;
		private String tipoDoc;
		private String documento;
		private String codigo;
		private String datosSensibles;
		private String mailUniandes;
		private String docs;
		
		
		
		public ParamsAspiranteBanner() {
			
			
		}
		
		public ParamsAspiranteBanner(String usuarioFormulario, String apellido, String nombre, String email,
				String telefono, String celular, String tipoDoc, String documento, String codigo, String datosSensibles,
				String mailUniandes, String docs) {
			super();
			this.usuarioFormulario = usuarioFormulario;
			this.apellido = apellido;
			this.nombre = nombre;
			this.email = email;
			this.telefono = telefono;
			this.celular = celular;
			this.tipoDoc = tipoDoc;
			this.documento = documento;
			this.codigo = codigo;
			this.datosSensibles = datosSensibles;
			this.mailUniandes = mailUniandes;
			this.docs = docs;
		}
		public String getUsuarioFormulario() {
			return usuarioFormulario;
		}
		public void setUsuarioFormulario(String usuarioFormulario) {
			this.usuarioFormulario = usuarioFormulario;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getCelular() {
			return celular;
		}
		public void setCelular(String celular) {
			this.celular = celular;
		}
		public String getTipoDoc() {
			return tipoDoc;
		}
		public void setTipoDoc(String tipoDoc) {
			this.tipoDoc = tipoDoc;
		}
		public String getDocumento() {
			return documento;
		}
		public void setDocumento(String documento) {
			this.documento = documento;
		}
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getDatosSensibles() {
			return datosSensibles;
		}
		public void setDatosSensibles(String datosSensibles) {
			this.datosSensibles = datosSensibles;
		}
		public String getMailUniandes() {
			return mailUniandes;
		}
		public void setMailUniandes(String mailUniandes) {
			this.mailUniandes = mailUniandes;
		}
		public String getDocs() {
			return docs;
		}
		public void setDocs(String docs) {
			this.docs = docs;
		}
		
		
		
		
	}

	public class ParamsEstudiosBanner {
		
		private String programa1Cod;
		private String programa1;
		private String programa2Cod;
		private String programa2;
		private String nombreColegio;
		private String ciudadColegio;
		private String documentos;
		private String observaciones;
		private String fechaEnvio;
		private String estadoOri;
		private String programaAdmi;
		private String tipoAdmi;
		private String estadoAdmi;
		private String applNo;
		private String periodoIcfes;
		private String puntajeIcfes;
		private String enviado;
		private String nivel;
		private String confirmacionIngre;
		private String fechaConfirmacionIngre;
		private String matriculado;
		private String codigoDane;
		private String jornada;
		private String calendario;
		private String naturaleza;
		private String caracter;
		private String fechaPagoMatricula;
		private String fechaProcesadoBanner;
		private String fechaProcesadoBus;
		
		
		
		public ParamsEstudiosBanner() {
			
			
		}

		
		public ParamsEstudiosBanner(String programa1Cod, String programa1, String programa2Cod, String programa2,
				String nombreColegio, String ciudadColegio, String documentos, String observaciones, String fechaEnvio,
				String estadoOri, String programaAdmi, String tipoAdmi, String estadoAdmi, String applNo,
				String periodoIcfes, String puntajeIcfes, String enviado, String nivel, String confirmacionIngre,
				String fechaConfirmacionIngre, String matriculado, String codigoDane, String jornada, String calendario,
				String naturaleza, String caracter, String fechaPagoMatricula, String fechaProcesadoBanner,
				String fechaProcesadoBus) {
			super();
			this.programa1Cod = programa1Cod;
			this.programa1 = programa1;
			this.programa2Cod = programa2Cod;
			this.programa2 = programa2;
			this.nombreColegio = nombreColegio;
			this.ciudadColegio = ciudadColegio;
			this.documentos = documentos;
			this.observaciones = observaciones;
			this.fechaEnvio = fechaEnvio;
			this.estadoOri = estadoOri;
			this.programaAdmi = programaAdmi;
			this.tipoAdmi = tipoAdmi;
			this.estadoAdmi = estadoAdmi;
			this.applNo = applNo;
			this.periodoIcfes = periodoIcfes;
			this.puntajeIcfes = puntajeIcfes;
			this.enviado = enviado;
			this.nivel = nivel;
			this.confirmacionIngre = confirmacionIngre;
			this.fechaConfirmacionIngre = fechaConfirmacionIngre;
			this.matriculado = matriculado;
			this.codigoDane = codigoDane;
			this.jornada = jornada;
			this.calendario = calendario;
			this.naturaleza = naturaleza;
			this.caracter = caracter;
			this.fechaPagoMatricula = fechaPagoMatricula;
			this.fechaProcesadoBanner = fechaProcesadoBanner;
			this.fechaProcesadoBus = fechaProcesadoBus;
		}




		public String getPrograma1Cod() {
			return programa1Cod;
		}

		public void setPrograma1Cod(String programa1Cod) {
			this.programa1Cod = programa1Cod;
		}

		public String getPrograma1() {
			return programa1;
		}

		public void setPrograma1(String programa1) {
			this.programa1 = programa1;
		}

		public String getPrograma2Cod() {
			return programa2Cod;
		}

		public void setPrograma2Cod(String programa2Cod) {
			this.programa2Cod = programa2Cod;
		}

		public String getPrograma2() {
			return programa2;
		}

		public void setPrograma2(String programa2) {
			this.programa2 = programa2;
		}

		public String getNombreColegio() {
			return nombreColegio;
		}

		public void setNombreColegio(String nombreColegio) {
			this.nombreColegio = nombreColegio;
		}

		public String getCiudadColegio() {
			return ciudadColegio;
		}

		public void setCiudadColegio(String ciudadColegio) {
			this.ciudadColegio = ciudadColegio;
		}

		public String getDocumentos() {
			return documentos;
		}

		public void setDocumentos(String documentos) {
			this.documentos = documentos;
		}

		public String getObservaciones() {
			return observaciones;
		}

		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}

		public String getFechaEnvio() {
			return fechaEnvio;
		}

		public void setFechaEnvio(String fechaEnvio) {
			this.fechaEnvio = fechaEnvio;
		}

		public String getEstadoOri() {
			return estadoOri;
		}

		public void setEstadoOri(String estadoOri) {
			this.estadoOri = estadoOri;
		}

		public String getProgramaAdmi() {
			return programaAdmi;
		}

		public void setProgramaAdmi(String programaAdmi) {
			this.programaAdmi = programaAdmi;
		}

		public String getTipoAdmi() {
			return tipoAdmi;
		}

		public void setTipoAdmi(String tipoAdmi) {
			this.tipoAdmi = tipoAdmi;
		}

		public String getEstadoAdmi() {
			return estadoAdmi;
		}

		public void setEstadoAdmi(String estadoAdmi) {
			this.estadoAdmi = estadoAdmi;
		}

		public String getApplNo() {
			return applNo;
		}

		public void setApplNo(String applNo) {
			this.applNo = applNo;
		}

		public String getPeriodoIcfes() {
			return periodoIcfes;
		}

		public void setPeriodoIcfes(String periodoIcfes) {
			this.periodoIcfes = periodoIcfes;
		}

		public String getPuntajeIcfes() {
			return puntajeIcfes;
		}

		public void setPuntajeIcfes(String puntajeIcfes) {
			this.puntajeIcfes = puntajeIcfes;
		}

		public String getEnviado() {
			return enviado;
		}

		public void setEnviado(String enviado) {
			this.enviado = enviado;
		}

		public String getNivel() {
			return nivel;
		}

		public void setNivel(String nivel) {
			this.nivel = nivel;
		}

		public String getConfirmacionIngre() {
			return confirmacionIngre;
		}

		public void setConfirmacionIngre(String confirmacionIngre) {
			this.confirmacionIngre = confirmacionIngre;
		}

		public String getFechaConfirmacionIngre() {
			return fechaConfirmacionIngre;
		}

		public void setFechaConfirmacionIngre(String fechaConfirmacionIngre) {
			this.fechaConfirmacionIngre = fechaConfirmacionIngre;
		}

		public String getMatriculado() {
			return matriculado;
		}

		public void setMatriculado(String matriculado) {
			this.matriculado = matriculado;
		}

		public String getCodigoDane() {
			return codigoDane;
		}

		public void setCodigoDane(String codigoDane) {
			this.codigoDane = codigoDane;
		}

		public String getJornada() {
			return jornada;
		}

		public void setJornada(String jornada) {
			this.jornada = jornada;
		}

		public String getCalendario() {
			return calendario;
		}

		public void setCalendario(String calendario) {
			this.calendario = calendario;
		}

		public String getNaturaleza() {
			return naturaleza;
		}

		public void setNaturaleza(String naturaleza) {
			this.naturaleza = naturaleza;
		}

		public String getCaracter() {
			return caracter;
		}

		public void setCaracter(String caracter) {
			this.caracter = caracter;
		}

		public String getFechaPagoMatricula() {
			return fechaPagoMatricula;
		}

		public void setFechaPagoMatricula(String fechaPagoMatricula) {
			this.fechaPagoMatricula = fechaPagoMatricula;
		}

		public String getFechaProcesadoBanner() {
			return fechaProcesadoBanner;
		}

		public void setFechaProcesadoBanner(String fechaProcesadoBanner) {
			this.fechaProcesadoBanner = fechaProcesadoBanner;
		}

		public String getFechaProcesadoBus() {
			return fechaProcesadoBus;
		}

		public void setFechaProcesadoBus(String fechaProcesadoBus) {
			this.fechaProcesadoBus = fechaProcesadoBus;
		}
		
	      
	}

}


