FROM openjdk:11
ARG APP_JAR=app/@appName@-@appVersion@.jar
COPY $APP_JAR app.jar
ENTRYPOINT ["java","-jar","/app.jar"]