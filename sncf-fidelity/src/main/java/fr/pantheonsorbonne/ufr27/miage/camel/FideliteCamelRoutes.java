package fr.pantheonsorbonne.ufr27.miage.camel;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FideliteCamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    FideliteGateway fideliteGateway;

    @Override
    public void configure() throws Exception {

        from("sjms2:queue:" + jmsPrefix + "g30Request")
                .autoStartup(isRouteEnabled)
                .log("Received G30 request: ${body}")


                .bean(fideliteGateway, "processG30Request")


                .to("sjms2:topic:" + jmsPrefix + "fidelityResponse");
    }
}
