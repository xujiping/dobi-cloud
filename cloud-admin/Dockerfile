FROM java:8

VOLUME /tmp/pets

ADD /cloud-admin-1.0-SNAPSHOT.jar /app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8100

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

