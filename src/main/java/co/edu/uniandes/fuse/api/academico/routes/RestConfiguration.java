package co.edu.uniandes.fuse.api.academico.routes;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.camel.BeanInject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;
import org.apache.camel.component.netty.http.SecurityConstraintMapping;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.jndi.JndiTemplate;


/**
 * Configuración REST: definición properties, datasources y configuración rest
 * 
 * @author CedEx Desarrollo de Software - DSIT - Universidad de los Andes
 * @since 2020-03-25
 */
public class RestConfiguration extends RouteBuilder {
	
	@BeanInject("authConstrain")
	SecurityConstraintMapping authConstrain;
	
	private DataSource dataSourceBanner;
	private DataSource dataSourcePrescoring;
	private DataSource dataSourceHomologaciones;
	private DataSource dataSourceQa;
	private DataSource dataSourceNife;
	private DataSource dataSourceBibliotecaD;
	//private DataSource dataSourceNukak;
	private DataSource dataSourceNifePortalayr;
	private DataSource dataSourceGestDocBanner;
	private DataSource dataSourceAdmissfBanner;
	
	private String realm = "";
	private String securityConstraint = "";
	private String realmKey = "";
	private String securityConstraintKey = "";
	private final static Logger log = Logger.getLogger("");
	String currentDataSource = "";
	String dsVerifyConfigured = "";
	
	@Override
	public void configure() throws Exception {
		
		CamelContext context = this.getContext();

		// GET PROPERTIES FILE

		PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
		pc.setLocation("ref:props-academico");
		
		JasyptPropertiesParser jpp = new JasyptPropertiesParser();
		jpp.setAlgorithm("{{datasource.algorithm}}");
		jpp.setPassword("{{datasource.key}}");
		pc.setPropertiesParser(jpp);
		
		try {
			MDC.put("ip", InetAddress.getLocalHost().getHostAddress().toString());
		} catch (UnknownHostException e) {
			log.error("ERROR AL OBTENER LA IP :::" + e);
		}

		// DATASOURCE CONFIGURATION
		try {
			dsVerifyConfigured = "banner";
			JndiTemplate jndi = new JndiTemplate();
			log.info("Intentando obtener dataSourceBanner");
			String jndiName = context.resolvePropertyPlaceholders("{{datasource.api.banner}}");
			dataSourceBanner = jndi.lookup("osgi:service/jdbc/"+jndiName, DataSource.class);
			log.info("dataSourceBanner obtenido correctamente");
			
			dsVerifyConfigured = "prescoring";
			JndiTemplate jndiP = new JndiTemplate();
			log.info("Intentando obtener dataSourcePrescoring");
			String jndiNameP = context.resolvePropertyPlaceholders("{{datasource.api.prescoring}}");
			dataSourcePrescoring = jndiP.lookup("osgi:service/jdbc/"+jndiNameP, DataSource.class);
			log.info("dataSourcePrescoring obtenido correctamente");
			
			dsVerifyConfigured = "homologaciones";
			JndiTemplate jndiH = new JndiTemplate();
			log.info("Intentando obtener dataSourceHomologaciones");
			String jndiNameH = context.resolvePropertyPlaceholders("{{datasource.api.homologaciones}}");
			dataSourceHomologaciones = jndiH.lookup("osgi:service/jdbc/"+jndiNameH, DataSource.class);
			log.info("dataSourceHomologaciones obtenido correctamente");
			
			dsVerifyConfigured = "qa";
			JndiTemplate jndiQa = new JndiTemplate();
			log.info("Intentando obtener dataSourceQa");
			String jndiQa1 = context.resolvePropertyPlaceholders("{{datasource.api.qa}}");
			dataSourceQa = jndiQa.lookup("osgi:service/jdbc/"+jndiQa1, DataSource.class);
			log.info("dataSourceQa obtenido correctamente");
			
			dsVerifyConfigured = "nife";
			JndiTemplate jndiNife = new JndiTemplate();
			log.info("Intentando obtener dataSourceNife");
			String jndiNameNife = context.resolvePropertyPlaceholders("{{datasource.api.nife}}");
			dataSourceNife = jndiNife.lookup("osgi:service/jdbc/"+jndiNameNife, DataSource.class);
			log.info("dataSourceNife obtenido correctamente");
			
			dsVerifyConfigured = "biblioteca";
			JndiTemplate jndiBiblioteca = new JndiTemplate();
			log.info("Intentando obtener dataSourceBibliotecaD");
			String jndiNameBiblioteca = context.resolvePropertyPlaceholders("{{datasource.api.biblioteca}}");
			dataSourceBibliotecaD = jndiBiblioteca.lookup("osgi:service/jdbc/"+jndiNameBiblioteca, DataSource.class);
			log.info("dataSourceBibliotecaD obtenido correctamente");
			
			dsVerifyConfigured = "nifePortalayr";
			JndiTemplate jndiNifeAyR = new JndiTemplate();
			log.info("Intentando obtener dataSourceNifePortalayr"); 
			String jndiNameNifeAyR =context.resolvePropertyPlaceholders("{{datasource.api.nifePortalayr}}");
			dataSourceNifePortalayr =jndiNifeAyR.lookup("osgi:service/jdbc/"+jndiNameNifeAyR, DataSource.class);
			log.info("DataSourceNifePortalayr obtenido correctamente");
			
			dsVerifyConfigured = "gestDocBanner";
			JndiTemplate jndiGEstDocBanner = new JndiTemplate();
			log.info("Intentando obtener dataSourceGestDocBanner");
			String jndiNameGestDocBanner = context.resolvePropertyPlaceholders("{{datasource.api.gestDocBanner}}");
			dataSourceGestDocBanner = jndiGEstDocBanner.lookup("osgi:service/jdbc/"+jndiNameGestDocBanner, DataSource.class);
			log.info("dataSourceGestDocBanner obtenido correctamente");
			
			
			dsVerifyConfigured = "admisSalesForce"; 
			JndiTemplate jndiAdmissfBanner = new JndiTemplate();
			log.info("Intentando obtener dataSourceAdmisSalesforceBanner"); 
			String jndiNameAdmissfBanner =context.resolvePropertyPlaceholders("{{datasource.api.admisSalesForce}}");
			dataSourceAdmissfBanner =jndiAdmissfBanner.lookup("osgi:service/jdbc/"+jndiNameAdmissfBanner,DataSource.class);
			log.info("dataSourceAdmissfBanner obtenido correctamente");
				
						 
			
			/*JndiTemplate jndiNukak = new JndiTemplate();
			String jndiNameNukak = context.resolvePropertyPlaceholders("{{datasource.api.nukak}}");
			dataSourceNukak = jndiNukak.lookup("osgi:service/jdbc/"+jndiNameNukak, DataSource.class);*/
			
		}catch (Exception e) {
			 log.error("Error al configurar los DataSources: " + e.getMessage(), e);
			throw new Exception("DataSource -"+dsVerifyConfigured+"- is not configured; Error message: "+ e.getMessage() + "... total e:"+ e);
		}
		
		
		
		try {
			currentDataSource = "banner";
			context.getComponent("banner", SqlComponent.class).setDataSource(dataSourceBanner);	
			
			currentDataSource = "prescoring";
			context.getComponent("prescoring", SqlComponent.class).setDataSource(dataSourcePrescoring);
			
			currentDataSource = "homologaciones"; 
			context.getComponent("homologaciones", SqlComponent.class).setDataSource(dataSourceHomologaciones);	
			
			currentDataSource = "qa";
			context.getComponent("qa", SqlComponent.class).setDataSource(dataSourceQa);	
			
			currentDataSource = "nife";
			context.getComponent("nife", SqlComponent.class).setDataSource(dataSourceNife);
			
			currentDataSource = "bibliotecaDeposito";
			context.getComponent("bibliotecaDeposito", SqlComponent.class).setDataSource(dataSourceBibliotecaD);
			
			currentDataSource = "nifePortalayr";
			context.getComponent("nifePortalayr", SqlComponent.class).setDataSource(dataSourceNifePortalayr);
			
			currentDataSource = "gestDocBanner";
			context.getComponent("gestDocBanner", SqlComponent.class).setDataSource(dataSourceGestDocBanner);
			
			currentDataSource = "admisSalesForce";
			context.getComponent("admisSalesForce",SqlComponent.class).setDataSource(dataSourceAdmissfBanner);
			 
			
			/*context.getComponent("bannerNukak", SqlComponent.class).setDataSource(dataSourceNukak);*/
		} catch (Exception e) {
			throw new Exception("Component Datasource -" + currentDataSource +"-  is not configured; Error message: "+ e.getMessage());
		}
		
		
		//BASIC AUTH CONFIGURATION 
		String apiAuth = context.resolvePropertyPlaceholders("{{auth.api.enable}}");
		authConstrain.addInclusion("/*", context.resolvePropertyPlaceholders("{{auth.api.role}}"));
		
		if (Boolean.valueOf(apiAuth) == true) {
			realmKey = "securityConfiguration.realm";
			realm = context.resolvePropertyPlaceholders("{{auth.api.realm}}");
			securityConstraintKey = "securityConfiguration.securityConstraint";
			securityConstraint = "#authConstrain";
		}
		
		// REST & SWAGGER CONFIGURATION
		restConfiguration()
		    .component("netty-http")
		    //.host("{{rest.api.host}}")
		    //.port("{{rest.api.port}}")
		    .endpointProperty(realmKey, realm)
		    .endpointProperty(securityConstraintKey, securityConstraint)
		    .endpointProperty("nettySharedHttpServer", "#sharedNettyHttpServer")
		    .bindingMode(RestBindingMode.json)
		    .dataFormatProperty("prettyPrint", "{{rest.api.prettyPrint}}")
		    .contextPath("/{{rest.api.name}}")
		    .apiContextPath("/doc")
		    .apiContextRouteId("doc")
	        .apiProperty("api.title", "{{rest.api.title}}")
	        .apiProperty("api.description", "{{rest.api.description}}")
	        .apiProperty("api.version", "{{rest.api.version}}")
	        .apiProperty("api.contact.name", "{{rest.api.contact.name}}")
	        .apiProperty("api.contact.email", "{{rest.api.contact.email}}")
	        .apiProperty("cors", "true");
	}
}
