package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.*;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;

public interface TicketsDAO {
    Tickets findTicket(int idTicket) throws TicketDoesntExistException;

    Tickets createTicket(int idTrip, int idCustomer) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces;



}
