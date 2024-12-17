# Projeto: auth
![Java](https://img.shields.io/badge/Java-17-blue?style=plastic&logo=java)  ![Spring](https://img.shields.io/badge/Spring-3.4.0-green?style=plastic&logo=spring)  ![](https://img.shields.io/badge/Maven-orange)  ![Developer](https://img.shields.io/static/v1?label=Dev&message=Fab&color=456a8f&style=plastic&logo=librewolf)  ![Msg](https://img.shields.io/badge/Nada%20é%20permanente,%20exceto%20a%20mudança.-456a8f)

## Índice

1. [Descrição](#descrição) 

2. [Como Funciona](#como-funciona)

3. [Dependências](#dependências) 

4. [Execução Local](#execução-local)

7. [Acessando Aplicação](#acessando-aplicação)

## Descrição
- Este é um estudo de um projeto que realiza uma implementação de spring security e token jwt.

## Como Funciona
- Segue abaixo um esquema de como funciona a aplicação com o spring security e o token jwt implementados.

![Esquema Spring Security e JWT](https://ik.imagekit.io/m9jek0taq/esquemaSpringSecurity.png?updatedAt=1734380688423)

## Dependências
- Segue abaixo as dependências do spring security e do token jwt necessárias no arquivo pom.xml
```xml
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	<!-- dependencia token jjwt -->
	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt-api</artifactId>
		<version>0.11.5</version>
	</dependency>
	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt-impl</artifactId>
		<version>0.11.5</version>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>io.jsonwebtoken</groupId>
		<artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
		<version>0.11.5</version>
		<scope>runtime</scope>
	</dependency>
```

## Execução Local
- É possível subir a aplicação utilizando o próprio Maven. É útil para testes de desenvolvimento onde é necessário uma agilidade maior

- No diretório root do projeto executar:

```bash

mvn spring-boot:run

```
- Ou então, executar o arquivo 'AuthApplication.java' na sua IDE preferida.   

## Acessando Aplicação
- A aplicação irá subir na porta 8080 nos seguintes end-points: 

Do tipo GET  ==>    http://localhost:8080/free
Do tipo GET  ==>    http://localhost:8080/auth
Do tipo POST ==>    http://localhost:8080/login 






