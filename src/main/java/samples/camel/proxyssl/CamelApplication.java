package samples.camel.proxyssl;

import org.apache.camel.component.http4.ProxyHttpClientConfigurer;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }

    @Bean("proxyConfigurer")
    public ProxyHttpClientConfigurer proxyConfigurer(@Value("${samples.camel.proxyssl.proxy-host}") String host, @Value("${samples.camel.proxyssl.proxy-port}") Integer port,
                                                     @Value("${samples.camel.proxyssl.proxy-scheme}") String scheme, @Value("${samples.camel.proxyssl.proxy-user}") String user,
                                                     @Value("${samples.camel.proxyssl.proxy-pass}") String pass) {
        return new ProxyHttpClientConfigurer(host, port, scheme, user, pass, null, null);
    }

    @Bean
    ServletRegistrationBean camelServlet() {
        // use a @Bean to register the Camel servlet which we need to do
        // because we want to use the camel-servlet component for the Camel REST service
        ServletRegistrationBean mapping = new ServletRegistrationBean();
        mapping.setName("CamelServlet");
        mapping.setLoadOnStartup(1);
        // CamelHttpTransportServlet is the name of the Camel servlet to use
        mapping.setServlet(new CamelHttpTransportServlet());
        mapping.addUrlMappings("/camel/*");
        return mapping;
    }

}
