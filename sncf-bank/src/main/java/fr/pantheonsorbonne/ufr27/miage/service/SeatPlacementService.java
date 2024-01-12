package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.ClientNotFoundException;

public interface SeatPlacementService {
    String bookSeat(int venueId) throws ClientNotFoundException.NoSeatAvailableException;
}
