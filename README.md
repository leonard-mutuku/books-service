# Rest API For ABC LTD

This is a spring boot, java integration for manages books collections.

Application runs on java 17 and spring boot version 3.5.7. It can also be run as a docker image and the steps for containerizing the application are explained below.

## Build jar with maven
mvn clean package

## Run application
mvn spring-boot:run

## Docker commands
To build and run a docker image of the same, run below commands

### `docker build -t book-manager .`
builds a docker image 

### `docker run -it -p 8080:8080 -t book-manager`
runs the build image interactively and expose port 8080

### `environment variables*`
Application environment configurations for tuning application:-

1. **TZ: Africa/Nairobi** &rarr; (optional) docker environment variable for setting application timezone. If not set, docker will default to UTC timezone. This will be evident on application logs
2. **JAVA_TOOL_OPTIONS: -XX:MaxRAM=450m -XX:+UseSerialGC** &rarr; (optional) Accepts options for tuning the jvm, e.g max ram and type of Garbage collector.
