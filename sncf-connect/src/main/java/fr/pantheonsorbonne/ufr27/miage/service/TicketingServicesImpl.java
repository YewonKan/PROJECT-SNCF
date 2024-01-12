package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.TripDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.dao.CustomersDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketsDAO;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Customers;
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
    public Tickets emitTicket(int idTrip, String fname, String lname, String email, int phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces {
        Customers c = new Customers();
        try {
            c = customersDAO.findMatchingCustomer(email);
        } catch (CustomersNotFoundException e){
            c = customersDAO.createNewCustomer( fname,  lname,  email,  phone);
        }

        if (tripDAO.findById(idTrip) == null) {
            throw new TripNotFoundException(idTrip);
        }

        if (tripDAO.findById(idTrip).getQuota() == 0) {
            throw new NoAvailablePlaces(tripDAO.findById(idTrip).getQuota());
        }

        System.out.println("ICI"+ idTrip);
        Tickets tickets = ticketsDAO.createTicket(idTrip, c.getIdCostumer());
        System.out.println("Tickets created successfully: " + tickets);
        return tickets;
    }
}





