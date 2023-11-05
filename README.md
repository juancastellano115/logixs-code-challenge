# Logixs Code Challenge
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

![Header](https://media.discordapp.net/attachments/720642232008573089/1170740391176315041/image.png?ex=655a23f4&is=6547aef4&hm=ee872e685d37bd2e6bb823fa5cd391219c0415035639caff0590f8d81c5a3dea&=)

## Objetivo:

````
Desarrollar una solución basada en microservicios que permita gestionar
cursos y estudiantes, utilizando Spring Boot, Docker, Hibernate y Swagger.
````
## Instrucciones:

- Previamente tener instalado Docker y Docker compose. Mis versiones actualmente son: `docker-compose version 1.29.2`, `Docker version 20.10.11`
- Clonar este repositorio: `git clone https://github.com/juancastellano115/logixs-code-challenge`
- `cd logixs-code-challenge`
- `docker-compose up`

## Endpoints Swagger:

#### *MS-COURSES*: http://localhost:8081/swagger-ui/index.html#/

#### *MS-STUDENTS*: http://localhost:8082/swagger-ui/index.html#/

#### *MS-REGISTRATIONS*: http://localhost:8083/swagger-ui/index.html#/

## Endpoint Eureka
#### http://localhost:8761/

## Endpoint Rabbit Dashboard (guest:guest)
#### http://localhost:15672/

## Arquitectura:

![Arquitectura](https://media.discordapp.net/attachments/720642232008573089/1170742113030373387/flow.png?ex=655a258f&is=6547b08f&hm=83602abcdc43b709d37765225e76db814a908fa78219b9a9d5d16543e8805aec&=)

## ER:

![ER](https://www.baeldung.com/wp-content/uploads/2018/11/relation-entity-er-updated.png)

## Features:

- Spring Boot 3
- Rabbit MQ
- OpenFeign
- PostgreSQL
- Eureka
- Spring Cloud
- Springdoc

--------------------

### That's all folks!