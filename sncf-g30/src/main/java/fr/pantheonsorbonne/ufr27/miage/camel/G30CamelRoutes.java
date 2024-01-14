package fr.pantheonsorbonne.ufr27.miage.camel;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketInformationDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.TicketTransmissionDTO;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;



import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class G30CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
    boolean isRouteEnabled;

   // @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    //String jmsPrefix;



    @Inject
    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public void configure() throws Exception {
/*from("sjms2:queue:"+ jmsPrefix +"fidelityToG30?exchangePattern=InOut")  // Listening to the JMS queue for G30 requests
                .autoStartup(isRouteEnabled)
                .marshal()
                .json(CompensationDTO.class)
                .log("Received Fidelity request: ${body}");*/

//                .process(new );

 from("sjms2:M1.delayNotification?exchangePattern=InOnly")
                .autoStartup(isRouteEnabled)
                .log("Received delay notification: ${body}")
                .process(new DelayNotificationProcessor());


        from("sjms2:M1.SNCF_Connect_Ticket_Transmission?exchangePattern=InOnly")
                .autoStartup(isRouteEnabled)
                .log("Received Ticket Transmission request: ${body}")
                //.unmarshal().json(TicketInformationDTO.class)  // Unmarshal the JSON message into TicketInformationDTO
                .process(new TicketTransmissionProcessor());
               // .log("successfull");
    }


    public class DelayNotificationProcessor implements Processor {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {
            // Récupérer le corps du message (la notification de retard en JSON)
         //   String delayNotificationJson = exchange.getIn().getBody(String.class);

            // Désérialiser la notification de retard en objet DelayNotification
           // DelayNotification delayNotification = objectMapper.readValue(delayNotificationJson, DelayNotification.class);
            //  insertService.insertDelayInformation(delayNotification);
           // Log.info("Received delay notification: " + delayNotification);
        }
    }

    @Transactional
    public class TicketTransmissionProcessor implements Processor {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void process(Exchange exchange) throws Exception {
            log.info("start function process");
            String ticketInformationJson = exchange.getIn().getBody(String.class);
            log.info("ticketInformationJson = " + ticketInformationJson );

            TicketTransmissionDTO ticketTransmissionDTO = objectMapper.readValue(ticketInformationJson, TicketTransmissionDTO.class);
            log.info("TicketTransmissionDTO : " + ticketTransmissionDTO);
            TicketInformation ticket = new TicketInformation();
            TicketInformationDAO t = null ;

            ticket.setClientId(ticketTransmissionDTO.idCustomers());
            log.info("setClientId : " + ticket.getClientId());
            ticket.setPrix(ticketTransmissionDTO.prix());
            log.info("setPrix" + ticket.getPrix());
            ticket.setTicketId(ticketTransmissionDTO.idTicket());
            log.info("setTicketId" + ticket.getTicketId());
            ticket.setTrainId(ticketTransmissionDTO.idTrain());
            log.info("setidTraint" + ticket.getTrainId());
            ticket.setTrajetId(ticketTransmissionDTO.idTrip());
            log.info("setidtrajet" + ticket.getTrajetId());

            log.info("start insert in bdd");

           // t.insertTicketinfo(ticket);
           //em.persist(ticket);
            Log.info("Received Ticket Transmission: SUCCESSFULLY " + ticketTransmissionDTO);
        }


    }
}
