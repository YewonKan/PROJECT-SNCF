package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;

public interface TicketsDAO {
    Tickets findTicket(int idTicket) throws NoSuchTicketException;

    void dropTicket(int idTicket) throws NoSuchTicketException;
    Tickets createTicket(int idTrip, int idCustomer) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces;


}
