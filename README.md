# Solución al examen de mercado libre

## Instrucción de ejecución del programa

### Ejecución de pruebas unitarias (JUnit) manuales

 1. Ejecute el siguiente comando para inicar las pruebas de unidad
 
	<code>$~ ***mvn test***</code>
	
	***Nota:*** Se debe contar con la base de datos MySQL con el script ___mutantesdb.sql___ que se encuentra en el directorio ___scriptsql___.

### Requisitos previos para ejecución en ambiente local

 - Java 11
 - Maven 3.6.x
 - Base de datos MySQL
 - Ejecute el script ___mutantesdb.sql___ que se encuentra en el directorio ___scriptsql___.

### Credenciales MySQL

	- base de datos: muntantesdb
	- username: root
	- password: mercadolibre.2020

### Ejecución en ambiente local

 1. Ejecutar el comando de compilación
	
	<code>$~ ***mvn clean package***</code>
	
 2. Ejecutar la aplicacion con el siguiente comando
 
	<code>$~ ***mvn spring-boot:run***</code>
	
### Ejecución en ambiente docker

	* Requisitos previos:
		* Docker version >= 19.x
		* Docker compose version >= 1.27.x

 1. Ejecute el siguiente comando dentro del directorio __docker__.
 
	<code>$~ ***docker-compose up -d***</code>
	
 ___Nota:___ Espere un par de minutos mientras se ejecuta el script inicial en la base de datos MySQL
 
 2. Para detener la aplicación ejecute el siguiente comando.
 
 <code>$~ ***docker-compose down***</code>

### Endpoints

Lista de servicios expuestos

 1. [POST] http://localhost:8000/mutant
 
	[REQUEST] 
 	{
 		"dna": [
 			"ATGCGA",
 			"CAGTGC",
 			"TTATGT",
 			"AGAAGG",
 			"CCTCTA",
 			"TCACAT"
 		]
 	}
	
 2. [GET] http://localhost:8000/stats
 
	[RESPONSE]
	{
		"count_mutant_dna": 0.0,
		"count_human_dna": 0.0,
		"ratio": "0"
	}

<hr/>

##### ___Author: Jesús Matiz, Arquitecto de Soluciones___
	
	