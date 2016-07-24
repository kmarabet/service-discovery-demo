# Microservices registration/discovery demo (using Eureka and Consul)

This demo project is based on the [Microservices Blog](https://spring.io/admin/blog/2181-microservices-with-spring) on the spring.io website.

![Demo System Schematic](mini-system.jpg)

## Service Registration/Discovery server

Eureka or Consul could be used for the service discovery, if you don't have those servers running somewhere then you may run them locally:

### Eureka
Eureka could started simply by building and running the eureka-service application (eureka-service module of this project), 
its web UI could be accessed [http://localhost:1111](http://localhost:1111) ;

### Consul
Consul should be downloaded from https://www.consul.io/downloads.html and then could be started using the command line:
`consul agent -dev -bind=127.0.0.1`
Its web UI could be accessed [http://localhost:8500/ui](http://localhost:8500/ui).

## Build the project

 1. In the project root directory, build the project using:
 `mvn clean package -Peureka` if you intent to use Eureka or
 `mvn clean package -Pconsul` if you intent to use Consul as a service-discovery.

 1. Open a CMD windows (Windows) or three Terminal windows (MacOS, Linux) for each application and arrange so you can view them conveniently.
 In each window, change to the directory of each module (eureka-service, micro-service and client-service).

 1. To start the eureka-server (if using consul you may skip this step):  
 `java -jar eureka-service/target/eureka-service-1.0-SNAPSHOT.jar` 
 
 1. To start the micro-service :  
  `java -jar micro-service/target/micro-service-1.0-SNAPSHOT.jar`
   
 1. To start the client-server (web server):  
  `java -jar client-service/target/web-server-1.0-SNAPSHOT.jar`
   
 1. In your favorite browser open the same two links: [http://localhost:1111](http://localhost:1111) and [http://localhost:3333](http://localhost:3333)

You should see servers being registered in the log output of the first (registration) window.
As you interact you should logging in the second and third windows.

 1. In a new window, run up a second micro-server using HTTP port 2223:
     * `java -jar target/micro-service-1.0-SNAPSHOT.jar --server.port=2223`
 1. Allow it to register itself
 1. Kill the first micro-server and see the web-server switch to using the new micro-server - no loss of service.

> NB. Service registration and un-registration could take a while till 15 minutes.  
