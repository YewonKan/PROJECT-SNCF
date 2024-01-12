package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.NoAvailablePlaces;
import fr.pantheonsorbonne.ufr27.miage.exception.TripNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Tickets;
import jakarta.transaction.Transactional;


public interface TicketingServices {
    @Transactional
    Tickets emitTicket(int idTrip, int idCustomer, String email, String fname, String lname, Double phone) throws CustomersNotFoundException, TripNotFoundException, NoAvailablePlaces;
}
