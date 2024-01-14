package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.service.FideliteService;
import io.quarkus.logging.Log;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
    FidelityProcessor fidelityProcessor;



    @Inject
    FideliteGateway fideliteGateway;  // Injecting FideliteGateway

    @Override
    public void configure() throws Exception {
   /*     .from("direct:g30Request")
                .autoStartup(isRouteEnabled)
                .log("Received G30 request: ${body}")
                .log("Before JSON Marshal: ${body}")
                .marshal().json()
                .log("After JSON Marshal: ${body}")
                .to("sjms2:queue:"+jmsPrefix+"fidelityToG30?exchangePattern=InOut")
                .unmarshal().json()*/

        from("sjms2:queue:"+jmsPrefix+"G30ToFidelity?exchangePattern=InOut")  // Listening to the JMS queue for G30 requests
                .autoStartup(isRouteEnabled)
                .log("Received G30 request: ${body}")
                .log("After JSON Marshal: ${body}")
                .process(fidelityProcessor)
                .log("Test processor: ${body}")
                .to("sjms2:queue:"+jmsPrefix+"fidelityToG30?exchangePattern=InOut")
                .log("After JSON Marshal: ${body}")
                .log("Response from fidelity : ${body}");
    }
    @ApplicationScoped
    private static class FidelityProcessor implements Processor {
        @Inject
        FideliteService fideliteService;

        @Override
        public void process(Exchange exchange) throws Exception {
            int clientId = exchange.getIn().getBody(Integer.class);

            Compensation compensation = fideliteService.verifyClientStatus(clientId);

            //FideliteService
            exchange.getIn().setBody("resultat de FideliteService");
            exchange.getIn().setHeader("response", compensation);
            Log.info("Processing to send compensation information for " + compensation.getClient() + ".");
        }


    }
}
