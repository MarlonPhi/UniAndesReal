package co.edu.uniandes.fuse.api.academico.beans.estudiante;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class ProcedureCallMultas {

	private final static Logger log = Logger.getLogger("");
	 private SimpleJdbcCall simpleJdbcCall;
	  private DataSource dataSource;
	  private String procedureCatalogName;
	  private String procedureName;
	  
	//@Override
	/*public void process(Exchange exchange) throws Exception {
		
		
		String slogin = exchange.getProperty("slogin", String.class);
		String function = exchange.getProperty("func", String.class);
		String pack = exchange.getProperty("pack", String.class);
		DataSource datasource = exchange.getProperty("dataSource", DataSource.class);
		
		log.info("verificando entrada de properties en processor"+", login: "+slogin+", funcion: "+function+", package: "+pack+", dataSource: "+datasource.toString());
		
		simpleJdbcCall = new SimpleJdbcCall(datasource);

			Map<String, Object> result = simpleJdbcCall
		    		.withFunctionName(function)
		    		.withCatalogName(pack)
		    		.useInParameterNames(new String[] { "ILOGIN" })
		    		.declareParameters(new SqlParameter[] { new SqlParameter("ILOGIN", 12) })
		    		.execute(new Object[] { slogin });
		
		exchange.getIn().setBody(result);
		
		log.info("verificando salida de result la informacion completa bodyyyyy:::::1:::"+ result);
		log.info("verificando salida de result la informacion completa bodyyyyy:::::2:::"+ exchange.getIn().getBody());
	}*/
	
	/*public ProcedureCallMultas(DataSource dataSource, String procedureCatalogName, String procedureName)
	  {
	    //this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	    this.procedureCatalogName = procedureCatalogName;
	    this.procedureName = procedureName;
	  }*/

	  public Map<String, Object> executeQuery(Exchange exchange)
	  {
		  String slogin = exchange.getProperty("slogin", String.class);
		  String function = exchange.getProperty("func", String.class);
		  String pack = exchange.getProperty("pack", String.class);
		  DataSource datasource = exchange.getProperty("dataSource", DataSource.class);
		  
	    //Map result = this.simpleJdbcCall.withFunctionName(this.procedureName).withCatalogName(this.procedureCatalogName).useInParameterNames(new String[] { "ILOGIN" }).declareParameters(new SqlParameter[] { new SqlParameter("ILOGIN", 12) }).execute(new Object[] { Slogin });
	    Map result = simpleJdbcCall
	    		.withFunctionName(function)
	    		.withCatalogName(pack)
	    		.useInParameterNames(new String[] { "ILOGIN" })
	    		.declareParameters(new SqlParameter[] { new SqlParameter("ILOGIN", 12) })
	    		.execute(new Object[] { slogin });
	    return result;
	  }

	  public DataSource getDataSource()
	  {
	    return this.dataSource;
	  }

	  public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	  }

	  public SimpleJdbcCall getSimpleJdbcCall() {
	    return this.simpleJdbcCall;
	  }

	  public void setSimpleJdbcCall(SimpleJdbcCall simpleJdbcCall) {
	    this.simpleJdbcCall = simpleJdbcCall;
	  }

	  public String getProcedureCatalogName() {
	    return this.procedureCatalogName;
	  }

	  public void setProcedureCatalogName(String procedureCatalogName) {
	    this.procedureCatalogName = procedureCatalogName;
	  }

	  public String getProcedureName() {
	    return this.procedureName;
	  }

	  public void setProcedureName(String procedureName) {
	    this.procedureName = procedureName;
	  }
	
	
	
	  
	  
	  
}
