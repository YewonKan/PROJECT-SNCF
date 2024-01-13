package fr.pantheonsorbonne.ufr27.miage.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

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
        from("sjms2:queue:"+ jmsPrefix +"fidelityToG30?exchangePattern=InOut")  // Listening to the JMS queue for G30 requests
                .autoStartup(isRouteEnabled)
                .marshal()
                .json(CompensationDTO.class)
                .log("Received Fidelity request: ${body}")
                .process(new FidelityProcessor());

        from("sjms2:M1.delayNotification?exchangePattern=InOnly")
                .autoStartup(isRouteEnabled)
                .log("Received delay notification: ${body}")
                .process(new DelayNotificationProcessor());
    }

    public class DelayNotificationProcessor implements Processor {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {
            // Récupérer le corps du message (la notification de retard en JSON)
            String delayNotificationJson = exchange.getIn().getBody(String.class);

            // Désérialiser la notification de retard en objet DelayNotification
            DelayNotification delayNotification = objectMapper.readValue(delayNotificationJson, DelayNotification.class);
//            insertService.insertDelayInformation(delayNotification);
            Log.info("Received delay notification: " + delayNotification);
        }
    }
    public class FidelityProcessor implements Processor {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {
            CompensationDTO compensationDTO = exchange.getIn().getBody(CompensationDTO.class);
            insertService.insertCompensationType(compensationDTO);
            Log.info("Received message on another queue: client Id - "+compensationDTO.clientID()+ " has type -"+compensationDTO.type());
        }
    }
}