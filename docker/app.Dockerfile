FROM openjdk:11
ARG APP_JAR=/../app/@app.name@-@project.version@.jar
COPY $APP_JAR app.jar
ENTRYPOINT ["java","-jar","/app.jar"]