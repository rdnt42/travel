FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
WORKDIR /opt
ENV PORT 8081
EXPOSE 8081
COPY target/travel.jar /opt/travel.jar
ENTRYPOINT exec java $JAVA_OPTS -jar travel.jar