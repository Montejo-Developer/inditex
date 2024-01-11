# Spring Boot API Productos

Este proyecto es una aplicación/servicio en Spring Boot que proporciona un endpoint REST 
para consultar precios según ciertos parámetros.

## Tecnologías Utilizadas

Spring Boot: Framework de desarrollo para aplicaciones Java.

Java: Lenguaje de programación utilizado.

Gradle: Sistema de construcción utilizado.

JUnit: Marco de pruebas unitarias para Java.

## Arquitectura y Patrones

La aplicación sigue una arquitectura de tres capas, con controladores que manejan las solicitudes HTTP, 
servicios que contienen la lógica empresarial y repositorios para interactuar con la base de datos.

## Requisitos Previos

- Java 8 o superior

- Maven

- Base de datos h2

## Instalación

1. Clona este repositorio.

2. Configura la base de datos en `application.properties`.

3. Ejecuta `mvn clean install` para construir el proyecto.

## Uso

La aplicación proporciona un endpoint REST para consultar precios.
Asegúrate de proporcionar la fecha de aplicación, identificador de producto y cadena en la solicitud.

## Estructura del Proyecto

- `/src`: Código fuente de la aplicación.

- `/src/main/resources`: Archivos de configuración y propiedades.

## Ejemplos de Llamadas a la API

curl -X GET 'http://localhost:8080/prices?date=2022-01-10T12:00:00&productId=1&brandId=1'
