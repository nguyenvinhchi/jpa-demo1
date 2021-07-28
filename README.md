#Demo project for learning Spring data JDBC & JPA

## Overview
###Dependencies for learning, exploring Spring

spring-boot-starter-actuator -> expose management endpoints like /actuator, /beans, /mappings,...

spring-data-rest-hal-explorer -> to explore endpoints

h2 -> in mem db to quick POC

### Configurations

management.endpoints.web.exposure.include=*

### HAL explore endpoints

curl -X GET localhost:8080/actuator/mappings

curl -X GET http://localhost:8080

Paste link 'http://localhost:8080/actuator' to Edit Headers of HAL explorer and go

http://localhost:8080/h2-console
