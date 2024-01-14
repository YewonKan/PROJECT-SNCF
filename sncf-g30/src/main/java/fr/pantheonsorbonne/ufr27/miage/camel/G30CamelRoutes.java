package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class G30CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    InsertService insertService;

    @Override
    public void configure() throws Exception {
        from("sjms2:queue:" + jmsPrefix + "fidelityToG30?exchangePattern=InOut")
                .autoStartup(isRouteEnabled)
                .marshal()
                .json(CompensationDTO.class)
                .log("Received Fidelity request: ${body}")
                .process(new FidelityProcessor());

        from("direct:g30Request")  // Add this route for the direct endpoint
                .autoStartup(isRouteEnabled)
                .log("Received message on direct:g30Request: ${body}")
                .process(new G30RequestProcessor());
    }

    public class FidelityProcessor implements Processor {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {
            CompensationDTO compensationDTO = exchange.getIn().getBody(CompensationDTO.class);
            insertService.insertCompensationType(compensationDTO);
            Log.info("Received message on another queue: client Id - " + compensationDTO.clientID() + " has type - " + compensationDTO.type());
        }
    }

    public class G30RequestProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            // Process the message received on direct:g30Request endpoint
            Log.info("Processing message from direct:g30Request: ${body}");
        }
    }
}
