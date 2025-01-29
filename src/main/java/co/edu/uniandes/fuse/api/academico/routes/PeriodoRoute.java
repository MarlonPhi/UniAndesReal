package co.edu.uniandes.fuse.api.academico.routes;

import javax.print.attribute.standard.MediaTray;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestParamType;

import com.google.common.net.MediaType;

import co.edu.uniandes.fuse.api.academico.beans.estudiante.ValidateParamsEstudianteService;
import co.edu.uniandes.fuse.api.academico.beans.periodos.PeriodosService;
import co.edu.uniandes.fuse.api.academico.models.periodo.PeriodoAcademico;
import co.edu.uniandes.fuse.api.academico.processors.PeriodoProcessor;
import co.edu.uniandes.fuse.api.academico.processors.ValidateErrorProcessor;
import co.edu.uniandes.fuse.core.utils.models.ErrorResponse;

public class PeriodoRoute extends RestConfiguration{

	public void configure() throws Exception {
		
		// EXCEPTIONS       
       onException(Exception.class)
        .handled(true)
        .to("direct-vm:manageException");
        ;
		
		rest("/periodo")
			.get("/")
				.description("Consulta los cursos por codigo o nombre")				
				.consumes("application/json").produces("application/json")
					.param()
					  	.name("fechainicial").description("Fecha Inicial del periodo academico  <br> (Ej: 2015/01/02)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
					.param()
					  	.name("fechafinal").description("Fecha Final del periodo academico  <br> (Ej: 2015/12/02)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
					.endParam()
					.param()
					  	.name("tiposperiodo").description("Tipo del periodo academico  <br> (Ej: 11)")
					  	.type(RestParamType.query)				  	
					  	.required(false)
				  	.endParam()
				  	.param()
				  		.name("anio").description("Año del periodo academico, <br>Nota: posible hacer peticion con solo este parametro <br> (Ej: 2022)")
				  		.type(RestParamType.query)
				  		.required(false)
				  	.endParam()
				.outType(PeriodoAcademico[].class)
				.responseMessage().code("000").message("300 - Redirect<br>400 - Client Error<br>500 - Server Error").responseModel(ErrorResponse.class).endResponseMessage()
				.to("direct:getPeriodos")
		
		;
		
		from("direct:getPeriodos")
			.log("ROUTE PERIODS START")
			.log("verify headers: ${headers}")
			.log("verify body: ${body}")
			.bean(ValidateParamsEstudianteService.class, "validateParamsPeriods")
			.choice()
				.when(simple("${property.fechaInic} != '' && ${property.fechaInic} != '' && ${property.tipoPeriodo} != '' && ${property.anio} == ''"))
					.log("Entrando a get PeriodoAcademico")	
					.to("direct:getPeriodoAcademico")
				.otherwise()
					.log("Entrando a get PeriodoAcademico ALL")	
					.to("direct:getPeriodoAcademicoAll")
			.end()
			.log("ROUTE PERIODS FINISHED")
		;
		
		
		from("direct:getPeriodoAcademico")
			.to("direct:set-authentication")
			.setProperty("SFechainicial").simple("${headers.fechainicial}")
			.setProperty("SFechafinal").simple("${headers.fechafinal}")
			.setProperty("STiposperiodo").simple("${headers.tiposperiodo}")
			.removeHeaders("*")
			.log("verificando sets propertys: fechaInic:${property.SFechainicial}, FechaFin:${property.SFechafinal}, TipPeriodo: ${property.STiposperiodo}")
			.to("velocity:template/periodo/obtener_periodo_academico.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.log("SQL: ${body}")
			.to("direct:bannerSQL")
			.log("verificando body: ${body}")
			.choice()
				.when(body().isNotNull())
					.setHeader("CamelHttpResponseCode").simple("{{http.code.ok}}")
					.log("salida ok")
				.otherwise()
					.setHeader("CamelHttpResponseCode").simple("{{http.code.not.found}}")
					.log("salida fail")
			.end()
			.choice()
				.when(simple("${header.CamelHttpResponseCode} != 200"))
					.process(new ValidateErrorProcessor())
				.otherwise()
					.bean(PeriodosService.class,"periodWithDate")
			.end()
		;
		
		from("direct:getPeriodoAcademicoAll")
			.log("ROUTE GET PERIOD ACADEMIC ALL START")
			.log("ROUTE START ALL PERIODS")
			.to("velocity:template/periodo/obtener_periodo_anio.vm")
			.setHeader("CamelSqlQuery").simple("${body}")
			.log("SQL: ${body}")
			.to("direct:bannerSQL")
			.log("verificando body: ${body}")
			.bean( PeriodosService.class,"allPeriods")
			.log("ROUTE GET PERIOD ACADEMIC ALL FINISHED")
		
		;
	}
}
