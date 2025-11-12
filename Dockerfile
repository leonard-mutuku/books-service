FROM eclipse-temurin:17.0.6_10-jre-alpine AS builder
LABEL org.opencontainers.image.authors="leonard.mutuku@ke.sportpesa.com"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract
FROM eclipse-temurin:17.0.6_10-jre-alpine
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

EXPOSE 5001

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAM=512m"

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
