# Getting Started

This is a demonstration for instrumenting a Springboot Java service with OpenTelemetry.

To build this project you simply need maven, Java 17, docker and the usual everyday tools.

# Setup

After you cloned the project locally, make sure your proxy is set up and running, then simply run:

`mvn clean install`

The build should be done in a few seconds, download of prereqs might take a little longer on first execution.

Then, you should be setting up the OpenTelemetry collector by running:

`./run-collector`

There's a few lines of output that conclude with:

`service/service.go:146	Everything is ready. Begin running and processing data.`

Once you see that, open a new terminal window and  run:

`run-otel target/demo-0.0.1-SNAPSHOT.jar`

This runs the demo service that has been instrumented with OpenTelemetry. The service sends the data to the through the opentelemetry-javaagent to the OpenTelemetry collector.

TODO:
The scripts need to be adjusted for Windows. Linux should work.

# Using the service

Either use `PostMan` or any other tool like `curl` or even a web brower to send requests to the service. It has two possible query parameters that you can use. A typical request should be:

`http://localhost:8080/request?time=500`

The `/request` endpoint reacts on 2 query parameters:

* time=X

    * where X is a time in milliseconds. It simply sleeps for X +(0-20%) before it returns
* code=X

    * where X is the HTTP response you want the service to be returned. 
    NOTE: there is currently no range check... feel free to add it.

Using both parameters at the same time, will sleep and return the requested http response.

# Load Testing

There is a `loadtest.js` script in the root directory of the project. This is intened to be use with a simple load testing tool named `K6`.
`K6` can be downloaded from the [K6 Website](https://k6.io/).

---
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#web)
* [OpenTelemetry HomePage](https://opentelemetry.io/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [NewRelic Setup for OpenTelemetry](https://docs.newrelic.com/docs/more-integrations/open-source-telemetry-integrations/opentelemetry/opentelemetry-introduction/)

