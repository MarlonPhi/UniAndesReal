package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ClientAuthSalesforceProcessor implements Processor{
	
	public final static Logger log = Logger.getLogger("");

	/**
	 * Method of process to send Post request in Salesforce for get auth token - exchange of route
	 * @param exchange
	 * @throws Exception
	 */
	public void process(Exchange ex) throws Exception {
		log.info("ENTRANDO PROCESSOR - CLIENT SALESFORCE");
		String url = ex.getProperty("urlAuth", String.class);
		String granttype = ex.getProperty("grantType", String.class);
		String clientid = ex.getProperty("clientId", String.class);
		String clientsecret = ex.getProperty("clientSecret", String.class);
		String username = ex.getProperty("username", String.class);
		String psswd = ex.getProperty("psswd", String.class);
		
		log.info("url: "+url+" gratType: "+granttype+" clientId: "+clientid+" clientSecret: "+clientsecret+" username: "+username+" psswd: "+psswd);
		
		log.info("ENTRANDO A SEND POST AUTH REQUEST");
	    String response = sendRequest(url, granttype, clientid, clientsecret, username, psswd, "POST");
	    log.warn("verificando RESPONSE TOTAL ------> "+ response);
	    log.info("SALIENDO A SEND POST AUTH REQUEST");
		
		String[] parts = response.split("___");
	    String responseBody = parts[0];
	    String responseCode = parts[1];
	    if (response != null) {
	    	log.info("VERIFICANDO RESPONSE BODY ----------> "+responseBody);
	    	ObjectMapper objMapper = new ObjectMapper();
	    	JsonNode jsonNode = objMapper.readTree(responseBody);
	    	String accessToken = jsonNode.path("access_token").asText();
	    	log.warn("----- Verificando token en cliente de auth ----> "+ accessToken);
	    	//JSONObject jsonResponse = new JSONObject();
	    	//jsonResponse.
	    	
	        ex.getIn().setBody(responseBody);
	        ex.setProperty("accssToken", accessToken);
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
	private String sendRequest(String urlString, String grantType
										, String clientId, String clientSecret, String userName, String password, String httpMethod ) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            configureConnection(connection, httpMethod);

            String boundary = generateBoundary();
            byte[] requestBody;
            requestBody = buildRequestBodyPost(
            		grantType
            		, clientId
            		, clientSecret
            		, userName
            		, password
            		, boundary).getBytes("UTF-8");
            log.info("VERIFICANDO REQUEST BODY EN DATA FORMAT"+ requestBody);
            sendRequestData(connection, requestBody);
            
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
	 * Method of build request body to  POST endpoint in onBase request with keywords an idTempalte
	 * @param keywords
	 * @param idTemplate
	 * @param boundary
	 * @return String text to body of request with keywords and idtemplate.
	 */
	private String buildRequestBodyPost(String grantType, String clientId, String clientSecret, String userName, String password, String boundary) {
        return "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"grant_type\"\r\n\r\n" +
               grantType+"\r\n" +
               "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"client_id\"\r\n\r\n" +
               clientId + "\r\n" +
               "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"client_secret\"\r\n\r\n" +
               clientSecret + "\r\n" +
               "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"username\"\r\n\r\n" +
               userName + "\r\n" +
               "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"password\"\r\n\r\n" +
               password + "\r\n" +
               "--" + boundary + "--\r\n";
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
	 * Method of configuration connection to level http with request properties
	 * @param connection
	 * @param authHeader
	 * @param httpMethod
	 * @throws IOException
	 */
	private void configureConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        String boundary = generateBoundary();
        connection.setRequestMethod(httpMethod);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        //connection.setRequestProperty("Authorization", authHeader);
    }
	
	/**
	 * Method to generate initial boundary to body of http request
	 * @return String line to current milliseconds id
	 */
	private String generateBoundary() {
        return "*****" + Long.toString(System.currentTimeMillis()) + "*****";
    }

}
