FROM openjdk:17-jdk
RUN  mkdir -p /opt/newrelic
ADD ./docker/newrelic/newrelic.jar /opt/newrelic/newrelic.jar
ADD ./docker/newrelic/newrelic.yml /opt/newrelic/newrelic.yml
ADD ./target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java","-javaagent:/opt/newrelic/newrelic.jar","-jar","/app/app.jar"]
