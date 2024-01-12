package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoSuchTicketsException;
import fr.pantheonsorbonne.ufr27.miage.exception.TicketDoesntExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Customers;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;

import fr.pantheonsorbonne.ufr27.miage.model.Trip;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TicketsDAOImpl implements TicketsDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    @Transactional
    public Tickets findTicket(int idTicket) throws TicketDoesntExistException {
        Tickets t = em.find(Tickets.class, idTicket);
        if (null == t) {
            throw new TicketDoesntExistException(idTicket);
        }
        return t;
    }

    @Override
    @Transactional
    public Tickets createTicket(int idTrip, int idCustomer) throws CustomersNotFoundException, TripNotFoundException {
       // Trip trip = (Trip) em.createQuery("Select t from Trip t where t.idTrip=:idTrip").setParameter("idTrip", idTrip).getSingleResult();
        Trip trip = em.find(Trip.class, idTrip);

        Customers customers = em.find(Customers.class, idCustomer);
        Tickets tickets = new Tickets();
        tickets.setIdTrip(trip.getIdTrip());
        tickets.setIdCustomers(customers.getIdCostumer());

        tickets.setFname(customers.getFname());
        tickets.setLname(customers.getLname());
        tickets.setEmail(customers.getEmail());
        tickets.setPhone(customers.getPhone());
        tickets.setPrix(trip.getPrix());
        tickets.setIdTrain(trip.getIdTrain());

        em.persist(tickets);
        return tickets;
    }
}
