FROM java:8

VOLUME /tmp/fast

ADD /target/cloud-fast-1.0-SNAPSHOT.jar /app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8082

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

