package fr.pantheonsorbonne.ufr27.miage.service;

import com.google.common.hash.Hashing;
import fr.pantheonsorbonne.ufr27.miage.exception.ClientNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.dao.CustomerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketDAO;

import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Objects;
import java.util.Random;

@ApplicationScoped
public class TicketingServiceImpl implements TicketingService {

    private final Random random = new Random();

    @Inject
    CustomerDAO customerDAO;

    @Inject
    TicketDAO ticketDAO;

    @Inject
    SeatPlacementService seatPlacementService;

    public String getKeyForTicket(Integer ticketId, Integer idVenue, Integer idVendor, Long salt) {
        return Hashing.sha256().hashString(ticketId + "" + idVenue + "" + idVendor + "" + salt + "MySuperSecret75013!", StandardCharsets.UTF_8).toString();
    }

    @Override
    @Transactional
    public TicketEmissionData emitTicket(ETicket eticket) throws ExpiredTransitionalTicketException, NoSuchTicketException, ClientNotFoundException.NoSeatAvailableException {

        Client client = null;
        try {
            client = customerDAO.findMatchingClient(eticket.getEmail());
        } catch (ClientNotFoundException e) {
            client = customerDAO.createNewClient(eticket.getFname(), eticket.getLname(), eticket.getEmail(), null);
        }

        Ticket ticket = ticketDAO.findTicket(eticket.getTransitionalTicketId());
        if (ticket.getValidUntil().isBefore(Instant.now())) {
            throw new ExpiredTransitionalTicketException(eticket.getTransitionalTicketId());
        }
        Long salt = random.nextLong();
        ticket = ticketDAO.emitTicketForCustomer(eticket.getTransitionalTicketId(), client);
        ticket.setTicketKey(this.getKeyForTicket(ticket.getId(), ticket.getIdVenue().getId(), ticket.getIdVendor().getId(), salt));
        if (Objects.equals(eticket.getType(), TicketType.SEATING)) {
            ticket.setSeatReference(seatPlacementService.bookSeat(ticket.getIdVenue().getId()));
        }

        return new TicketEmissionData(ticket.getTicketKey(),salt);


    }

    @Override
    @Transactional
    public void cleanUpTransitionalTicket(int transitionalTicketId) {
        ticketDAO.removeTransitionalTicket(transitionalTicketId);
    }

    @Override
    public boolean validateTicket(int idTicket, int idVenue, int idVendor, long salt, String key) {
        return this.getKeyForTicket(idTicket, idVenue, idVendor, salt).equals(key);
    }
}
