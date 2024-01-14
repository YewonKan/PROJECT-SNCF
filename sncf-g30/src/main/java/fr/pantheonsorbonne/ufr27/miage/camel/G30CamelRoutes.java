package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dto.DelayNotificationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransmissionTicketDTO;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
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
    @Inject
    FidelityProcessor fidelityProcessor;

    @Override
    public void configure() throws Exception {

        from("sjms2:M1." + jmsPrefix + "fidelityResponse")
                .autoStartup(isRouteEnabled)
                .marshal()
                .json(CompensationDTO.class)
                .log("Received Fidelity request: ${body}")
                .process(new FidelityProcessor());

        from("sjms2:M1.G30fidelity")
                .autoStartup(isRouteEnabled)
                .log("Received G30 request: ${body}")
                .choice()
                .when(simple("${body} == 'Response from Fidelity'"))
                .log("Received successful response from Fidelity: ${body}")
                .to("sjms2:M1." + jmsPrefix + "fidelityResponse");



        from("sjms2:M1.delayNotification?exchangePattern=InOnly")
            .autoStartup(isRouteEnabled)
            .log("Received delay notification: ${body}")
            .process(new DelayNotificationProcessor());

        from("sjms2:M1.SNCF_Connect_Ticket_Transmission?exchangePattern=InOnly")
            .autoStartup(isRouteEnabled)
            .process(new TicketTransmissionProcessor())
            .log("Received Ticket Transmission request: ${body}");

    }

    public class TicketTransmissionProcessor implements Processor {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {
            log.info("start function process");
            String ticketInformationJson = exchange.getIn().getBody(String.class);
            log.info("ticketInformationJson = " + ticketInformationJson);
            TransmissionTicketDTO ticketTransmissionDTO = objectMapper.readValue(ticketInformationJson, TransmissionTicketDTO.class);
            TicketInformation ticketInformation = insertService.insertTicketInformation(ticketTransmissionDTO);
            Log.info("Received Ticket Transmission: SUCCESSFULLY " + ticketInformation);
        }
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

    @ApplicationScoped
    public static class FidelityProcessor implements Processor {
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {

            String compensationJson = exchange.getIn().getBody(String.class);

            CompensationDTO compensationDTO = objectMapper.readValue(compensationJson, CompensationDTO.class);
            //insertService.insertCompensationType(compensationDTO);
            Log.info("Received message on another queue: client Id - " + compensationDTO.clientID() + " has type - " + compensationDTO.type());
        }
    }
}