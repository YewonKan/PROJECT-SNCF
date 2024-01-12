package fr.pantheonsorbonne.ufr27.miage.camel;


import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.TicketingServices;
import org.apache.camel.CamelContext;
import org.apache.camel.Handler;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class TicketsGateway {

    @Inject
    TicketingServices ticketingServices;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Handler
    void emitTicket(int idTrip, int idCustomer, String fname, String lname, String email, int phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try {
            ticketingServices.emitTicket(idTrip, fname,  lname,  email, phone);
        } catch (CustomersNotFoundException | TripNotFoundException e) {
            throw e;
        }
    }

}
