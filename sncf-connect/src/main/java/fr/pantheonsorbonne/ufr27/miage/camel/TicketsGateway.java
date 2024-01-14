package fr.pantheonsorbonne.ufr27.miage.camel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.dto.TransmissionTicketDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;
import fr.pantheonsorbonne.ufr27.miage.service.TicketingServices;
import io.quarkus.logging.Log;
import jakarta.jms.*;
import org.apache.camel.CamelContext;
import org.apache.camel.Handler;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class TicketsGateway {

    @Inject
    TicketingServices ticketingServices;

    @Inject
    ConnectionFactory connectionFactory;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Handler
    public void emitTicketSendMessage(int idTrip, String fname, String lname, String email, String phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {

        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {

            Tickets t= ticketingServices.emitTicket(idTrip, fname, lname, email, phone);

            TransmissionTicketDTO ticketInformationDTO = new TransmissionTicketDTO(
                    t.getIdTicket(), t.getPrix(), t.getIdTrip(), t.getIdCustomers(), t.getIdTrain()
            );

            String emitTicketJson = objectMapper.writeValueAsString(ticketInformationDTO);


            context.createProducer().send(context.createQueue("M1.SNCF_Connect_Ticket_Transmission"), emitTicketJson);
            Log.info("Ticket message sent for ticket with ID: " + t.getIdTicket());
        } catch (JMSRuntimeException e) {
            Log.error("Error while sending ticket message " + e);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}