FROM maven:3.9-eclipse-temurin-21 AS build
COPY ./ /home/app
RUN cd /home/app && mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:21-alpine
COPY --from=build /home/app/target/*-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
