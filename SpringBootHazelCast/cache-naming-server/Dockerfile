FROM java:8
VOLUME /tmp
ADD cache-naming-server/target/cache-naming-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]