# Redaction Web Service

This is a web service using Spring Boot that exposes a GET and POST endpoint available at the "localhost:[port]/redact" mapping. 

## Features

- POST endpoint for receiving a message string to redact
- GET endpoint for determining the web service name
- A configurable "black list" of words that will be redacted in the original message sent through the POST endpoint.
- A configurable server port at runtime.

## Prerequisites

- Java 21+
- Maven 3.9.9+

## Build

Clone repository</br>
build with Maven
``` bash
mvn clean package
```

## Run the application
```bash
java -jar target/redactor-0.0.1-SNAPSHOT.jar <--server.port=<port-number-of-choice>>
```
The application starts on port 8080 by default.

## GET Request

```bash
$ curl -i -X GET http://localhost:8080/redact
```
If the get was successful, then a response of 200 along with the response `Redaction Service` should be received.

## POST JSON

```bash
$ curl -i -X POST http://localhost:8080/redact   -H "Content-Type: application/json"   -d '{
    "A dog, a monkey or a dolphin are all mammals. A snake, however, is not a mammal, it is a reptile. Who can say what a DogSnake is?"
  }'
```
If the post was successful a `201 created` will be returned and the redacted message based on the blackList will be returned in </br> 
the response body.

```bash
HTTP/1.1 201
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 06 Jul 2025 16:20:46 GMT

{"redactedContent":"{\n    
\"A REDACTED, a monkey or a REDACTED are all mammals. A REDACTED, however, is not a REDACTED, it is a reptile. 
Who can say what a DogSnake is?\"\n  }"}
```

## Testing
```bash
mvn test
```

# Future improvements
Storing redacted messages to a database

Retrieving stored messages from the database

Enhance error handling and validation

Add API authentication and security. For example, if the user has the correct credentials, to un-redact past messages. 

