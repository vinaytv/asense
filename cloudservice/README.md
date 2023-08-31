# springboot library for cloud message services


## Requirements

For using this library:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Using the library

- Add this library as a maven dependency in your application.
- annotate main class of your application like shown below
- Autowire CloudQueueService like shown below, this has send and receive message method.

```java
import org.springframework.beans.factory.annotation.Autowired;

@ComponentScan(basePackages = {"com.alphasense.cloudservice"})
@Autowired
private CloudQueueService cloudQueueService;
cloudQueueService.sendMessage(String payload);
List<String> messages=cloudQueueService.receiveMessage();
/*
        process messages
 */



```

## Enhancing the library

- This library works with profile based configuration to support different service providers
- All we have to do is add new configuration to introduce new service provider with flag to initialize that particular service.
- AZURE,AWS,GCP are currently supported services in this library