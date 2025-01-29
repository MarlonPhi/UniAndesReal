package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

public class ClienteOnBaseProcessor implements Processor{
	

public final static Logger log = Logger.getLogger("");


	@Override
	public void process(Exchange exchange) throws Exception {
	    log.info("ENTRANDO A PROCESSOR - CLIENTE ON BASE PROCESSOR");
	    String urlOnBase = exchange.getProperty("urlOnBase", String.class);
	    String keywordsSend = exchange.getProperty("json_keyword", String.class);
	    final String authHeaderValue = exchange.getProperty("authOnBase", String.class);
	    log.info("ENTRANDO A SEND POST REQUEST");
	    String response = sendPostRequest(urlOnBase, keywordsSend, authHeaderValue);
	    log.info("SALIENDO A SEND POST REQUEST");
	    log.warn("verificando salida de response ::"+ response);
	    String[] parts = response.split("___");
	    String responseBody = parts[0];
	    String responseCode = parts[1];
	    if (response != null) {
	        exchange.getIn().setBody(responseBody);
	        exchange.getIn().setHeader("CamelHttpResponseCode", responseCode);
	    }
	}
	
	
	private String sendPostRequest(String urlString, String keywords, String authHeader) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            configureConnection(connection, authHeader);

            String boundary = generateBoundary();
            byte[] requestBody = buildRequestBody(keywords, boundary).getBytes("UTF-8");

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
	
	private void configureConnection(HttpURLConnection connection, String authHeader) throws IOException {
        String boundary = generateBoundary();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("Authorization", authHeader);
    }
	
	private String generateBoundary() {
        return "*****" + Long.toString(System.currentTimeMillis()) + "*****";
    }
	
	private String buildRequestBody(String keywords, String boundary) {
        return "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"formTemplate\"\r\n\r\n" +
               "133\r\n" +
               "--" + boundary + "\r\n" +
               "Content-Disposition: form-data; name=\"keywords\"\r\n\r\n" +
               keywords + "\r\n" +
               "--" + boundary + "--\r\n";
    }
	
	private void sendRequestData(HttpURLConnection connection, byte[] requestBody) throws IOException {
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestBody);
            outputStream.flush();
        }
    }
	
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
