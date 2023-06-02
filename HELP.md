# Modelo de maturidade de aderencia ao rest
modelo de maturidade de Richardson: https://martinfowler.com/articles/richardsonMaturityModel.html

# Getting Projeto final do curso:https://github.com/alura-cursos/2208-kotlin-spring/tree/aula_5


# curso 2 Adição de persistencia

no pom adcinar dependencias h2 e jpa
Alterar arquivo application para yml, arquivo dentro de resources
para acessar o h2 pelo console habilita no properties e entra pelo link
http://localhost:8081/h2-console/

# RODAR SPRING BOOT PELA LINHA DE COMANDO
mvn spring-boot:run
mvn spring-boot:run -D"spring-boot.run.profiles"=prod
Para selecionar o profile correto, passe a flag -Dspring.profile.active=${valor_desejado}


#paginacao spring
https://docs.spring.io/spring-data/rest/docs/current/reference/html/#paging-and-sorting

#gerador de senha encriptada
https://bcrypt-generator.com/

# Configrução do docker
1- Criar arquivo docker file
2-baixar plugin
3-baixar imagem com jdk do dockerhub
rodar docker: docker build -t forum -f Dockerfile .
docker run -p 3080:8081 forum

Sempre que atualizar o codigo e for colocar no docker,  criar o package atualizado usando o comando mnv package

# deploy heroku usando cli
heroku login
heroku create
Ir na pasta raiz do projeto onde esta o git
heroku:git remote -a "nome projeto do heroku"
heroku container:login
heroku container:push web
heroku container:release web

# baixar sql no docker:
pelo cmd: docker pull mysql:8.0.33
rodar : docker run -d -p 3306:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_PASSWORD=root mysql:8.0.33
verificar se tem container: docker ps
habilitar edicao do sql pelo prompt: docker exec -it mysql-container bash
concetar o cmd: mysql -u root -p
criar banco de dados: create database forum
vericar bds : SHOW DATABASES
Abrir o banco: USE NOMEBANCO

iniciar container ja cadastrado: docker start -ai mysql-container

# Api do Swagger
https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui/1.7.0

Site que abre :http://localhost:8080/swagger-ui/index.html

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#using.devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

