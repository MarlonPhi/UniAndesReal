package co.edu.uniandes.fuse.api.academico.processors.Estudiante;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http4.HttpEntityConverter;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.model.dataformat.MimeMultipartDataFormat;
import org.apache.camel.spi.DataFormat;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class UploadFormOnBaseProcessor implements Processor {

	private final static Logger logger = Logger.getLogger("");
	
	@Override
	public void process(Exchange ex) throws Exception {
		
        String boundary = ""+System.currentTimeMillis();
		String formdata = "\r\n" + "----=_Part_0_" + boundary + "--\r\n";
		 

        // Establecer los headers
        ex.getIn().setHeader("Authorization", "Basic cGFsdmFyZXo6cGFzc3dvcmQ=");
        ex.getIn().setHeader("Accept", "*/*");
        ex.getIn().setHeader("Content-Type", "multipart/form-data; boundary=----=_Part_0_" + boundary+"; charset=utf-8"); //ISO-8859-1");
        ex.getIn().setHeader(Exchange.HTTP_METHOD, HttpMethods.POST);
        ex.getIn().setBody(formdata);
        
	}

}
