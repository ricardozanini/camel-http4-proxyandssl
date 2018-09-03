This is a simple example of how to add Proxy capabilities to a Camel route using [camel-http4](https://github.com/apache/camel/blob/master/components/camel-http4/src/main/docs/http4-component.adoc).

# How to use

1. Replace your `URL` the `application.properties` file or set the `HTTP4_URL` environment variable (nice with OpenShift) 
2. [Install](https://tinyproxy.github.io/) and start the Tinyproxy service: `systemctl start tinyproxy.service`
3. Run the Spring Boot application: `mvn spring-boot:run`
4. Access the URL at: [http://localhost:8080/camel/http4]() and your going to see the response
