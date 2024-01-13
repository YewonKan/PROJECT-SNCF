package fr.pantheonsorbonne.ufr27.miage.camel;


import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class G30CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Override
    public void configure() throws Exception {

        from("direct:g30Request")
                .autoStartup(isRouteEnabled)
                .log("Route G30CamelRoutes is called")
                .to("sjms2:queue:" + jmsPrefix + "g30Request");

        from("sjms2:queue:" + jmsPrefix + "g30Request")
                .autoStartup(isRouteEnabled)
                .log("Received G30 request: ${body}")


                .choice()
                .when(simple("${body} == 'Response from Fidelity'"))
                .log("Received successful response from Fidelity: ${body}")
                .to("direct:processFidelityResponse")
                .otherwise()
                .log("Received error response from Fidelity: ${body}")
                .process(exchange -> {

                    String errorMessage = exchange.getIn().getBody(String.class);

                    throw new RuntimeException("Error from Fidelity: " + errorMessage);
                })
                .end();

        from("direct:processFidelityResponse")
                .log("Processing successful response from Fidelity: ${body}")

                .to("sjms2:topic:" + jmsPrefix + "fidelityResponse");
    }
}
