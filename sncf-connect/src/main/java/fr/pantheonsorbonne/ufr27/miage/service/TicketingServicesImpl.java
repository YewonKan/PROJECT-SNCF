package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.TripDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.dao.CustomersDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketsDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TicketingServicesImpl implements TicketingServices {

    @Inject
    CustomersDAO customersDAO;

    @Inject
    TripDAO tripDAO;

    @Inject
    TicketsDAO ticketsDAO;


    @Override
    @Transactional
    public Tickets emitTicket(int idTrip, int idCustomer, String email, String fname, String lname, Double phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        try {
           customersDAO.findMatchingCustomer(email);
        } catch (CustomersNotFoundException e){
            customersDAO.createNewCustomer(phone, fname, lname, email);
        }

        if (tripDAO.findById(idTrip) == null) {
            throw new TripNotFoundException(idTrip);
        }

        if (tripDAO.findById(idTrip).getQuota() == 0) {
            throw new NoAvailablePlaces(tripDAO.findById(idTrip).getQuota());
        }

        Tickets tickets = ticketsDAO.createTicket(idTrip, idCustomer);
        System.out.println("Tickets created successfully: " + tickets);
        return tickets;
    }
}





