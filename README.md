# Project 1

This project seeks to provide users with the ability to search for points of interest within a given location. Users will be able to specify locations via coordinates which will be portrayed via a Google Maps interface and filter for locations of interests such as cafes or bakeries. Users can register and use accounts with our provided services to access relevant information about these locations in our databases.

## Browser Compatibility

Chrome is the only tested web browser for client use.

There are known issues with running CoffeeBuddy on FireFox. For example, you cannot create in-house accounts if using FireFox. You will still be able to log into the application if using google accounts.

## Server Setup (API Keys + MySQL server setup)

Create and add to `src/main/resources/application.properties`:

```
google.api.key=<insert_api_key_here>

# MySQL Configuration (See example_configs/example_application.properties for this example)

#Below is an example configuration of a local mysql server (given you use the same parameters as the docker run command below)
#spring.datasource.url=jdbc:mysql://localhost:3306/location_search_db
#spring.datasource.username=root
#spring.datasource.password=location-passw
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.url=<insert_database_url>
spring.datasource.username=<insert_username>
spring.datasource.password=<insert_password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Google Login Screen
spring.security.oauth2.client.registration.google.client-id=<Insert-Client-ID>
spring.security.oauth2.client.registration.google.client-secret=<Insert-Client-Secret>

# JPA Properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

Without an API key, an obscure `org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'placesController': Injection of autowired dependencies failed` error will occur.
If you haven't set up an API key with google, you can set the API key to `none` (e.g. `google.api.key=None`), and the server will run but will not be able use Google services that are required for the application.

An example application.properties file can be found under example_configs

## Create MySQL Server (locally)

Install Docker and run the following commands (you can change run parameters if you really want, just make sure to change the application.properties above)

```
docker pull mysql:latest
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=location-passw -e MYSQL_DATABASE=location_search_db -p 3306:3306 -d mysql:latest
```

This pulls the latest image of MySQL from DockerHub and creates a MySQL Database on port 3306 on your local machine.

To access your database locally for testing purposes, run this command here:

```
docker exec -it mysql-container mysql -uroot -p
```

From there enter your database password and select the database using:

```
USE database_name;
```

## Create Google OAuth2 Client Login (Locally)

Set Up OAuth2 Client Login Consent within Google Console

Create an OAuth2 Client Key within Google Console

When setting up OAuth2 Client keys, set the redirect URI to:

```
http://localhost:8080/login/oauth2/code/google
```
or to whichever URL you are running the server on

## Developer Guide

If you have maven installed, you can run the server with:

```shell
mvn spring-boot:run
```
You can then connect to it in your browser at `http://localhost:8080/`

## Troubleshooting

Unusual error: `org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'placesController': Injection of autowired dependencies failed`
Ensure you have setup your Google API key (See Server Setup (API Keys))
