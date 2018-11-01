# Dropwizard MongoDB Microservice

How to start the application 
---

This microservice is an example that exposes through a REST interface services that manipulate the information of a MongoDB collection.

## Technologies

- Dropwizard v. 1.3
- MongoDB Java Driver 3.8
- Swagger 1.0.6
- Mockito 2.23
- Docker

## Development

1. Run `mvn clean install` to build your application
2. Run `docker-compose up` for start the containers
3. Create the users for database with the next commands:
    - Enter to container:
        `docker exec -it [container_name or container_id] /bin/bash`
    - Inside container call the shell for enter console of mongo db with the command
        `mongo`
    - Create the database
        `use donuts`
    - Create the user    
    - `db.createUser({ user: "user_donuts", pwd: "pAsw0Rd", roles: [ { role: "readWrite", db: "donuts"} ]});`      

Swagger
---
To check that your application is running enter url `http://localhost:8080/dropwizard-mongodb-ms/swagger` 

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
