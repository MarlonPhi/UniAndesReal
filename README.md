![alternativetext](https://github.com/UniandesDSIT/Servicio-Fuse-Persona-Persona/raw/master/path/to/DSIT.png)

# Fuse-Api-Academico

## 1. Resumen o Descripcion
_Plantilla normalizada para la creación/migración de servicios Rest donde iterativamente se complementará el estandar de diseño y arquitectura de código, patrones, buenas practicas y procesos de CI&CD._

## 2. Pre requisitos
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

## 3. Instalacion

Actuaizar las dependencais de maven desde el IDE:

    maven > Update project
    
Desde el panel hawtio o desde la consola de fuse instalar el proyecto y arrancarlo en el servidor:

	install -s mvn:co.edu.uniandes.fuse.labs/restdsl/1.0.0-SNAPSHOT


## 4. Como utilizar

Una vez el proyecto este instalado y corriendo en el server acceder por al URL: 
http://localhost:9090/restDSL/api/v1.0.0/doc
	

## 5. Wiki

En la siguiene Wiki se puede encontrar más información sobre proyectos maven para fuse
[Lienamientos Desarrollo ESB](https://github.com/UniandesDSIT/Fuse-Lab-RestDsl/wiki)


## 6. Contribuyentes

Luis Castillo (LuisCastilloB)
Sebastian Baez (sebastianb1612)

## 7. Version

1.0.0

## 8. Autores
 
- Luis Castillo (LuisCastilloB)
- Sebastian Baez (sebastianb1612)

## 9. Soporte

Este software esta soportado por el Cedex de Desarrollo (Integración) Si tienes algun inconveniente con el software puedes reportarlo en Azure DevOps como un Issue al respectivo proyecto.

## 10. Licencia
Este proyecto esta bajo la Licencia de Propietario (Universidad de los Andes)

## 11. Estado del proyecto

Este proyecto aun esta en fase de desarrollo y mejora continua, actualmente se está trabajando en los de autenticación.

**Nota: Este proyecto cumple con el estandar definido para la gestión de versionamiento del codigo definido por la DSIT. El cual se encuentra publicado en [Lineamientos CedEx](https://github.com/UniandesDSIT/coding-guidelines)**



