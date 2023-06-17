FROM amazoncorretto:17-alpine3.17

RUN apk update
RUN apk add maven

COPY . .

RUN mvn clean
RUN mvn package

ENTRYPOINT ["java", "-jar", "/target/litoka-ws-server-0.0.1-SNAPSHOT.jar"]
