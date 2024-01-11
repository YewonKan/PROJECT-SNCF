package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
/*

@ApplicationScoped
public class CamRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;


    @Inject
    FideliteGateway fideliteGateway;

    @Inject
    CamelContext camelContext;


    @Override
    public void configure() throws Exception {
        camelContext.setTracing(true);

        onException(StatutClientManquantException.class)
                .handled(true)
                .setHeader("success", simple("false"))
                .setBody(simple("No client status"));

        from("direct:verifyClientStatus")
                .autoStartup(isRouteEnabled)
                .unmarshal().json(Compensation.class)
                .bean(fideliteGateway, "sendCompensationInfo")
                .marshal().json();

    }
}
*/
