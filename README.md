## Challenge
Write a server application (A) that accepts requests and sends those requests to a second server (B). 
Have the second server (B) respond to the requests of the client via the first server (A).
* The request should be a simple operation e.g. multiply two numbers.
* (Tricky Part) The application should be able to handle multiple concurrent requests.


## Technical stack being used
* Java 8
* Spring Boot - There are two microservices (Server-A & Server-B). Server A is acting as the client
while Server B is acting as the receiver.  
* Memcached - Used for implementing a Caching mechanism. Reason: Memcached is distributed, 
so it can run on multiple nodes but still act like it’s just one big memory space to store your objects.
* RabbitMQ - It have native support for request and response pattern. See RabbitMq docs for direct reply-to feature. https://www.rabbitmq.com/direct-reply-to.html
Btw, same can be done with Kafka (but not recommended for this use case).
* Swagger 2 for Spring Boot 2 RESTful API Documentation.

## How to run the setup
* To run this setup successfully you need Docker and Docker-compose installed and working on your machine. 
The Docker setup is done in the file called "docker-compose.yml". The environment variables are set up 
in a file called ".env", which is used by docker-compose. To run build with docker, do a "mvn clean install"
from the project root, then "docker-compose up". 

* If  you want to run the build without Docker, then you will have to switch the <jib.skip.install> 
property from "false" to "true". This property is set in the parent pom. 

* If you chose to run the project without docker, it is assumed that you already have an 
installation of RabbitMQ and MemCached on your local machine. You will also need to update 
the application.properties file (Server-A & Server-B) so it can point to your local installation of Rabbit and MemCached. 
Having done that, you can start Server A and Server B and test the application in the browser 
by Launching Swagger UI available at http://localhost:8080/swagger-ui.html or 
if you don't want to use Swagger just simply call rest endpoint in your browser of 
choice like so: endpoint http://localhost:8080/multiplication?noOne=11&noTwo=8

## Ports in use
* Server-A (client) - port 8080
* Server-B (consumer) - port 8081 
* RabbitMQ - default port 5672
* MemCached - default port 11211








