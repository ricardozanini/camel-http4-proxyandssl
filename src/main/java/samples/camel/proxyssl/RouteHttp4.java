package samples.camel.proxyssl;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouteHttp4 extends RouteBuilder {

    @Value("${samples.camel.proxyssl.url}")
    private String url;    
    
    public RouteHttp4() {
    }
    
    @Override
    public void configure() throws Exception {
        restConfiguration().contextPath("/camel/*").bindingMode(RestBindingMode.json);
        
        rest("/")
        .consumes("application/json")
        .produces("application/json")
            .get("/http4")
                .to("direct:http4-ssl");
        
        from("direct:http4-ssl")
           .toF("https4://%s?bridgeEndpoint=true&httpClientConfigurer=#proxyConfigurer", url);
    }
}
    