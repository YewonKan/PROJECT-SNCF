package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TicketDoesntExistException;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;
import jakarta.transaction.Transactional;


public interface TicketingServices {
    @Transactional
    Tickets emitTicket(int idTrip, String fname, String lname, String email, String phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces;

    Tickets verifyTicket(int idTicket) throws TicketDoesntExistException;
}
