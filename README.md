# README.md

## Descripción de la aplicación

Esta aplicación es una API REST desarrollada con Spring Boot y MongoDB. La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una entidad llamada `Piloto`. Los datos de los pilotos se persisten en una base de datos MongoDB.

## Requisitos del sistema

- Java 8 o superior
- Maven
- MongoDB

## Instalación

1. Clonar el repositorio en su máquina local usando `git clone`.
2. Navegar hasta el directorio del proyecto.
3. Ejecutar `mvn clean install` para construir el proyecto.
4. Ejecutar `mvn spring-boot:run` para iniciar la aplicación.

## Uso

La API tiene los siguientes endpoints:

- `GET /pilotos`: Devuelve un JSON con todos los pilotos.
- `POST /pilotos`: Crea un nuevo piloto. El cuerpo de la petición debe contener el JSON del nuevo piloto.
- `GET /piloto/{id}`: Devuelve un JSON con los datos del piloto con el ID especificado.
- `PUT /pilotos`: Actualiza un piloto existente. El cuerpo de la petición debe contener el JSON del piloto a modificar.
- `DELETE /piloto/{id}`: Elimina el piloto con el ID especificado.

Para interactuar con la API, puede usar herramientas como Postman, curl o cualquier cliente HTTP de su elección.

## Cliente de consola

Además de la API, el proyecto incluye una aplicación de consola que interactúa con la API de manera reactiva utilizando Webflux. La aplicación de consola ofrece las siguientes opciones:

- Mostrar a todos los pilotos.
- Mostrar un piloto dado un id.
- Crear un piloto con nuevos datos.
- Actualizar un piloto dado un id concreto.
- Borrar un piloto dado un id.

Para ejecutar la aplicación de consola, siga las instrucciones de instalación y luego ejecute el comando `java -jar target/nombre-del-proyecto.jar` en la línea de comandos.