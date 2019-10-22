FROM java:8

VOLUME /tmp/gateway-cloud

ADD /gateway-cloud.jar /app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8110

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

