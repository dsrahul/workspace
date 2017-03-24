FROM java:8
VOLUME /tmp
ADD target/employee-0.0.1-SNAPSHOT.war app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb/microservices", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]