package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;

public interface TicketingService {
    TicketEmissionData emitTicket(ETicket eticket) throws ExpiredTransitionalTicketException, NoSuchTicketException, ClientNotFoundException.NoSeatAvailableException;

    void cleanUpTransitionalTicket(int transitionalTicketId);

    boolean validateTicket(int idTicket, int idVenue, int idVendor, long salt, String key);
}
