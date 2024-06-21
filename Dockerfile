# Genera el fat jar de la app con gradle
FROM gradle:7.5.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
ADD . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# Ejecuta la app con el jar generado
FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/PDS-2024-backend-0.0.1-SNAPSHOT.jar /weekbook.jar
ENTRYPOINT ["java", "-jar", "/weekbook.jar"]