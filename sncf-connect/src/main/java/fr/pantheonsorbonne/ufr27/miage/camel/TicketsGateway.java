package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TicketDoesntExistException;
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

    @Handler
    public void emitTicketSendMessage(int idTrip, String fname, String lname, String email, String phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            TextMessage message = context.createTextMessage("Transmission in progress for new ticket ");

            Tickets t= ticketingServices.emitTicket(idTrip,fname, lname, email, phone);

            message.setIntProperty("idTicket", t.getIdTicket());
            message.setIntProperty("idTrip", t.getIdTrip());
            message.setIntProperty("idCustomer", t.getIdCustomers());
            message.setIntProperty("idTrain", t.getIdTrain());

            context.createProducer().send(context.createQueue("M1.SNCF_Connect_Ticket_Transmission"), message);
            Log.info("Ticket message sent for ticket with ID: " + t.getIdTicket());
        } catch (JMSRuntimeException e) {
            Log.error("Error while sending ticket message " + e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @Handler
    public void verifyTicket(int idTicket) throws TicketDoesntExistException {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            TextMessage message = context.createTextMessage("Verification in progress for ticket with ID: " + idTicket);

            Tickets tverifiee = ticketingServices.verifyTicket(idTicket);

            message.setIntProperty("idTicket", tverifiee.getIdTicket());
            message.setIntProperty("idTrip", tverifiee.getIdTrip());
            message.setIntProperty("idCustomer", tverifiee.getIdCustomers());
            message.setIntProperty("idTrain", tverifiee.getIdTrain());

            context.createProducer().send(context.createQueue("SNCF_Connect_Ticket_Verification"), message);
            Log.info("Ticket verification message sent for ticket with ID: " + idTicket);
        } catch (JMSRuntimeException e) {
            Log.error("Error while sending ticket verification message for ticket with ID: " + idTicket, e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}