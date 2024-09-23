# Project 1

This project seeks to provide users with the ability to search for points of interest within a given location. Users will be able to specify locations via coordinates which will be portrayed via a Google Maps interface and filter for locations of interests such as cafes or bakeries. Users can register and use accounts with our provided services to access relevant information about these locations in our databases.


## Server Setup (API Keys)

Add to `src/main/resources/application.properties`:
```
google.api.key=<Your_API_key>
```

Without an API key, an obscure `org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'placesController': Injection of autowired dependencies failed` error will occur.
If you haven't set up an API key with google, you can set the API key to `none` (e.g. `google.api.key=None`), and the server will run but will not be able use Google services that are required for the application.


## Developer Guide

If you have maven installed, you can run the server with:

```shell
mvn spring-boot:run
```

You can then connect to it in your browser at `http://localhost:8080/`


## Troubleshooting

Unusual error: `org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'placesController': Injection of autowired dependencies failed`
Ensure you have setup your Google API key (See Server Setup (API Keys)) 
