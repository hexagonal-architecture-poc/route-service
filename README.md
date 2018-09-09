# Route service documentation

This microservice is responsible for creating routes based on an origin city and a destiny city. To find the possible route it is necessary to search itineraries on itinerary-service.

[Pipeline](https://gitlab.com/itinerary-challenge/route-service/pipelines)

[Docker Registry](https://gitlab.com/itinerary-challenge/route-service/container_registry)

--- 
## How to build 

This project was created with maven. To build the project execute the following command:

	$ mvn clean install

---

## How to run 

To run the application on standalone, execute the following command:

	$ mvn spring-boot:run

**Note:** This microservice is dependent of [itinerary-service](https://gitlab.com/itinerary-challenge/itinerary-service).


To run the application with docker-compose or kubernetes access this [documentation](https://gitlab.com/itinerary-challenge/devops).

---

## How to use

To access the standalone access: 
- Less time between city 1 and city 3: [http://localhost:8081/routes/less-time?origin=1&destiny=3](http://localhost:8081/routes/less-time?origin=1&destiny=3)

    ```json
    {
        "originId":1,
        "destinyId":3,
        "itineraries":[
            {
                "id":7,
                "origin":{
                    "id":1,
                    "name":"Zaragoza"
                },
                "destiny":{
                    "id":4,
                    "name":"Granada"
                },
                "departureTime":"22:10:00",
                "arrivalTime":"23:14:00"
            },
            {
                "id":9,
                "origin":{
                    "id":4,
                    "name":"Granada"
                },
                "destiny":{
                    "id":3,
                    "name":"Madri"
                },
                "departureTime":"23:20:00",
                "arrivalTime":"23:50:00"
            }
        ],
        "totalTime":100
    }
    ```

- Less connections between city 1 and city 3: [http://localhost:8081/routes/less-connections?origin=1&destiny=3](http://localhost:8081/routes/less-connections?origin=1&destiny=3)


    ```json
    {
        "originId":1,
        "destinyId":3,
        "itineraries":[
            {
                "id":3,
                "origin":{
                    "id":1,
                    "name":"Zaragoza"
                },
                "destiny":{
                    "id":3,
                    "name":"Madri"
                },
                "departureTime":"15:05:00",
                "arrivalTime":"22:00:00"
            }
        ],
        "totalTime":415
    }
    ```


The api documentation (Swagger): [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)


---
## Default data

### Cities

| ID     | Name          | 
| :------|:--------------| 
| 1      | Zaragoza      |
| 2      | Barcelona     |   
| 3      | Madri         |    
| 4      | Granada       |    

### Itineraries

| ID     | origin_id | destiny_id | departure_time | arrival_time |
| :------|:----------|:-----------|:---------------|:-------------| 
| 1      | 1         | 2          | 10:10:00       | 11:15:00     |
| 2      | 1         | 2          | 15:10:00       | 15:45:00     |
| 3      | 1         | 3          | 15:05:00       | 22:00:00     |
| 4      | 2         | 4          | 18:09:00       | 20:10:00     |
| 5      | 2         | 1          | 12:10:00       | 14:30:00     |
| 6      | 3         | 2          | 16:10:00       | 17:04:00     |
| 7      | 1         | 4          | 22:10:00       | 23:14:00     |
| 8      | 4         | 2          | 06:10:00       | 08:34:00     |
| 9      | 4         | 3          | 23:20:00       | 23:50:00     |

---

## Frameworks

- **SpringBoot(2.0):** Spring Boot makes it easy to create stand-alone applications. The Spring container provides an implementation for IoC supporting Dependency Injection.
- **SpringBoot Actuator:** SpringBoot’s Actuator which provides production ready features to help you monitor and manage your application.
	- This solution uses the health endpoint to check if the application is ready when deploying on kubernetes. (readinessProbe and livenessProbe)
- **SpringBoot devtools:** Includes a set of tools to make the development experience easier. For example, Automatic Restart. When the application is launched with 'java -jar' all developer tools are disabled.
- **Sleuth:** Implements a distributed tracing solution.
	- Sleuth was used to easily track logs from different microservices.
	- With Zipkin, it's possible to track every microservice call. 
- **Swagger:** Creates a dynamic documentation for controllers.
	- Used to create a API documentation.
- **OpenFeign:** Makes writing java HTTP clients easier.
    - Used to create a HTTP client for itinerary-service.

