package fr.pantheonsorbonne.ufr27.miage.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dto.DelayNotificationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
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
                // Add your logic to process the successful response from Fidelity
                .to("sjms2:topic:" + jmsPrefix + "g30Response");  // Sending the response to a JMS topic

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
            DelayNotificationDTO delayNotification = objectMapper.readValue(delayNotificationJson, DelayNotificationDTO.class);
            // Créer un objet DelayInformation à partir de DelayNotification
            DelayInformation delayInformation = new DelayInformation();
            delayInformation.setIdTrajet(delayNotification.idTrajet());
            delayInformation.setIdTrain(delayNotification.idTrain());
            delayInformation.setDelayedMinutes(delayNotification.delayDuration());
            delayInformation.setDelayMotivation(delayNotification.reason());
            delayInformation.setDelayedDate(delayNotification.creationTime());

            // Appeler la méthode insertDelayInformation avec l'objet DelayInformation
            DelayInformation newDelayInformation = insertService.insertDelayInformation(delayInformation);
            Log.info("Received delay notification: " + newDelayInformation);
        }
    }
}
