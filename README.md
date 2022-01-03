# jukebox-api

## Overview

This application consumes two other APIs and performs a search by correlating data from those APIs.


## Basic Architecture

Spring Boot Application inside a docker container.


## Build & Deployment

At project root:
``` 
gradle build
```

To run:
```
java jar build/libs/jukebox-api-0.0.1-SNAPSHOT.jar
```
To Build a docker container:
```
docker build -t jukebox/jukebox-spring-boot-docker .
```
To run the container:
```
docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t jukebox/jukebox-spring-boot-docker
```
## Testing

All functions are short and unit testable. Junit unit tests accompany the source code.

Using curl

```
curl --location --request GET 'localhost:8080/api/search?settingId=b43f247a-8478-4f24-8e28-792fcfe539f5&model=fusion&offset=&limit=&' \
```

Using Postman

```
{
	"info": {
		"_postman_id": "8640f14e-03c6-4287-9dd1-6e2a0162f6f0",
		"name": "Jukebox",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "JukeboxSearch",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "localhost:8080/api/search?settingId=b43f247a-8478-4f24-8e28-792fcfe539f5&model=fusion&offset=&limit=&",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"search"
					],
					"query": [
						{
							"key": "settingId",
							"value": "b43f247a-8478-4f24-8e28-792fcfe539f5"
						},
						{
							"key": "model",
							"value": "fusion"
						},
						{
							"key": "offset",
							"value": ""
						},
						{
							"key": "limit",
							"value": ""
						},
						{
							"key": "",
							"value": null
						}
					]
				}
			},
			"response": []
		}
	]
}
```


## Documentation

Swagger UI at - http://localhost:8080/swagger-ui.html
