# Reading Is Good #

ReadingIsGood is an online books retail firm which operates only on the Internet. Main target of ReadingIsGood is to deliver books from its one centralized warehouse to their customers within the same day. 
# Tech Stack #

* Java 11
* Gradle 7.1.1
* MongoDB 5.0.2
* Spring Boot 2.5.4
* Swagger 2.9.2
* Docker

### How to start the project ? ###
* I assume that you have already downloaded and installed Docker, JDK 11 (or higher) and Gradle 
* First navigate to application root folder.
* Create a jar build for this application using the following command;
  * $ ./gradlew clean -x test build
* Then create the build with docker compose to build docker image using built jar file;
  * $ docker-compose build
* Then use following command to run whole setup using docker compose;
  * $ docker-compose up
* Now you can check the app is up or not;
  * curl --location --request GET 'http://localhost:8080/healthcheck'
### Swagger UI ###
* Please check below url for Swagger UI;
  * localhost:8080/swagger-ui.html#/

### How to authenticate ? ###

Please use below user pass information for authentication;

curl --location --request POST 'http://localhost:8080/authenticate' \
--header 'Authorization: Basic PEJhc2ljIEF1dGggVXNlcm5hbWU+OjxCYXNpYyBBdXRoIFBhc3N3b3JkPg==' \
--header 'Content-Type: application/json' \
--data-raw '{
"username" : "matasoy",
"password" : "password"
}'

### How to register as a customer? ###

curl --location --request POST 'http://localhost:8080/api/customer/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "matasoy",
"lastName" : "atasoy",
"email" : "mehmet.atasoy@test.com",
"password": "password"
}'


### How to consume API ? ###

* First authenticate via "/authenticate" endpoint then get the token and use it as a Bearer Token in all other 
  request except "/api/customer/register", "/healthcheck"


* For further information please use postman collection found in the source or Swagger UI.
