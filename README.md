![alternativetext](https://github.com/UniandesDSIT/Servicio-Fuse-Persona-Persona/raw/master/path/to/DSIT.png)

# Fuse-Api-Academico

## Resumen o Descripcion
_Plantilla normalizada para la creación/migración de servicios Rest donde iterativamente se complementará el estandar de diseño y arquitectura de código, patrones, buenas practicas y procesos de CI&CD._

## Pre requisitos
Debe tener instaladas en su maquina las siguientes herramientas:

- Code Ready Studio
- Java JDK 1.8 / Open JDK 11+
- Servidor JBoss Fuse v6.3.0

Dependencias requeridas e instaladas en fuse

```sh
features:install camel-jackson
features:install camel-netty4-http
features:install camel-swagger-java
features:install camel-http4

Fabric:

fabric:profile-edit --feature camel-jackson {fabric-profile}
fabric:profile-edit --feature camel-netty4-http {fabric-profile}
fabric:profile-edit --feature camel-swagger-java {fabric-profile}
```

## Instalacion

Actuaizar las dependencais de maven desde el IDE:

    maven > Update project
    
Desde el panel hawtio o desde la consola de fuse instalar el proyecto y arrancarlo en el servidor:

  	
	install -s mvn:co.edu.uniandes.fuse.api/academico/1.3.11


## Como utilizar

Una vez el proyecto este instalado y corriendo en el server acceder por al URL: 
- http://localhost:8186/api-academico/doc

## Test en Postman

Para realizar pruebas de funcionamiento y rendimiento se debe dirigir al siguiente archivo:
- https://github.com/UniandesDSIT/Fuse-Api-Academico/blob/develop/test/API-ACADEMICO.postman_collection.json. 

Esta colección le permitira tener las rutas o end points desarrollados en este proyecto y servicio para .

## Documentación del servicio en SWAGGER

URL documentación: http://filandia.uniandes.edu.co/swagger/?urls.primaryName=API%20Academico%20-%20QA

## Wiki

En la siguiene Wiki se puede encontrar más información sobre proyectos maven para fuse
[Lienamientos Desarrollo ESB](https://github.com/UniandesDSIT/Fuse-Lab-RestDsl/wiki)


## Contribuyentes

- Luis Castillo (LuisCastilloB)
- Sebastian Baez (sebastianb1612)
- Sergio Gutierrez (sergiog182)
- Nicolás Ibarra (nicbot23)

## Version

   ### 1.3.11

## Autores
 
- Luis Castillo (LuisCastilloB)
- Sebastian Baez (sebastianb1612)

## Soporte

Este software esta soportado por el Cedex de Desarrollo (Integración) Si tienes algun inconveniente con el software puedes reportarlo en Azure DevOps como un Issue al respectivo proyecto.

## Licencia
Este proyecto esta bajo la Licencia de Propietario (Universidad de los Andes)

## Estado del proyecto

Este proyecto aun esta en fase de desarrollo y mejora continua. Hasta el momento se ha implementado los siguientes servicios:
- Creditos
- Cursos
- Dependencia
- Egresados
- Estado Cuenta
- Estudiantes
- Homologaciones
- Información Estudiante
- Inscripciones
- Investigación
- Notas
- Periodo
- Profesores
- Programas
- Secciones
- Suspensiones

Estos servicios se han establecido en este proyecto por la desviación perteneciente a la parte academica de la Universidad de los Andes.

**Nota: Este proyecto cumple con el estandar definido para la gestión de versionamiento del codigo definido por la DSIT. El cual se encuentra publicado en [Lineamientos CedEx](https://github.com/UniandesDSIT/coding-guidelines)**



