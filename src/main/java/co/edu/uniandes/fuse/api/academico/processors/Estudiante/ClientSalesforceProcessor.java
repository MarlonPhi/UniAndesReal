package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientSalesforceProcessor implements Processor {
	public final static Logger log = Logger.getLogger("");
	@Override
	public void process(Exchange ex) throws Exception {
		
		String accsToken = ex.getProperty("Authorization", String.class);
		String bodySend = ex.getIn().getBody(String.class); // deberia ser un json
		String url = ex.getProperty("CamelHttpUri", String.class);
		String method = ex.getProperty("CamelHttpMethod", String.class);
		String conttype =ex.getProperty("Content-Type", String.class); 
		log.info("ENTRANDO A SENDREQUEST ----------> ");
		String response = sendRequest(url, bodySend, method, accsToken, conttype);
		log.info("<---------- SALIENDO A SENDREQUEST  ");
		
	    if (response != null) {
	    	
	    	String[] parts = response.split("___");
		    String responseBody = parts[0];
		    String responseCode = parts[1];
		    
	    	log.info("VERIFICANDO RESPONSE BODY in client----------> "+responseBody);
	    	ObjectMapper objMapper = new ObjectMapper();
	    	JsonNode jsonNode = objMapper.readTree(responseBody);
	    	//String accessToken = jsonNode.path("access_token").asText();
	    	//log.warn("----- Verificando token en cliente de auth ----> "+ accessToken);
	    	//JSONObject jsonResponse = new JSONObject();
	    	//jsonResponse.
	    	
	        ex.getIn().setBody(responseBody);
	        //ex.setProperty("accssToken", accessToken);
	        ex.getIn().setHeader("CamelHttpResponseCode", responseCode);
	    }
	}
	
	/**
	 * Method of prepare request to onBase with boundary and propierties and headers
	 * @param urlString
	 * @param keywords
	 * @param authHeader
	 * @param httpMethod
	 * @param idTemplate
	 * @return Response of request Http to onBase
	 */
	private String sendRequest(String urlString,String body, String httpMethod, String auth, String contentType) {
        HttpURLConnection connection = null;
        try {
        	log.info("Enviando solicitud a URL: " + urlString);
            URL url = new URL(urlString);
            log.info("Enviando solicitud a URL host: " + url.getHost());
            log.info("Enviando solicitud a URL path: " + url.getPath());
            log.info("Enviando solicitud a URL query: " + url.getQuery());
            connection = (HttpURLConnection) url.openConnection();
            configureConnection(connection, httpMethod, contentType, auth);

            
            if (!"GET".equalsIgnoreCase(httpMethod)) {
                byte[] requestBody = body.getBytes("UTF-8");
                log.info("VERIFICANDO REQUEST BODY EN DATA FORMAT " + requestBody);
                sendRequestData(connection, requestBody);
            }
            
            
            return processResponse(connection);

        } catch (IOException e) {
            log.error("Error during HTTP request", e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
	
	/**
	 * Method of configuration connection to level http with request properties
	 * @param connection
	 * @param authHeader
	 * @param httpMethod
	 * @throws IOException
	 */
	private void configureConnection(HttpURLConnection connection, String httpMethod, String contentType, String auth) throws IOException {
       
        connection.setRequestMethod(httpMethod);
        connection.setDoOutput(!"GET".equalsIgnoreCase(httpMethod)); //false si es diferente de GET
        connection.setRequestProperty("Authorization", auth);
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Content-Type", contentType);
        
     // Imprimir las cabeceras enviadas
        //log.info("Headers being sent antes de for :" + connection.getRequestProperties());
        //log.info("Headers metodo :" + connection.getRequestMethod());
        //log.info("Headers metodo content:" + connection.getContent());
        //log.info("Headers being sent:");
        for (String header : connection.getRequestProperties().keySet()) {
            log.info(header + ": " + connection.getRequestProperty(header));
        }
        
    }
	
	/**
	 * Method that user HttpConection and carry out connection
	 * @param connection
	 * @param requestBody
	 * @throws IOException
	 */
	private void sendRequestData(HttpURLConnection connection, byte[] requestBody) throws IOException {
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody);
            outputStream.flush();
        }
    }
	
	/**
	 * Method of verify response of endpoint request and get response body with status request
	 * @param connection
	 * @return
	 * @throws IOException
	 */
	private String processResponse(HttpURLConnection connection) throws IOException {
		
		String output = "";
        int responseCode = connection.getResponseCode();
        //log.info("Response Code: " + responseCode);
        

        try (InputStream inputStream = (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED)
                ? connection.getInputStream() : connection.getErrorStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            output = response.toString() +"___"+responseCode;
            return output;
        }
    }

}
