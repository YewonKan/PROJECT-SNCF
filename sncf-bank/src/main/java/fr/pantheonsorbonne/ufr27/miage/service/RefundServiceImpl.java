package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.TicketGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.VenueQuotaDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.Gig;
import fr.pantheonsorbonne.ufr27.miage.dto.RemainingQuota;
import fr.pantheonsorbonne.ufr27.miage.model.Ticket;
import fr.pantheonsorbonne.ufr27.miage.model.Venue;
import fr.pantheonsorbonne.ufr27.miage.model.VenueLineUp;
import fr.pantheonsorbonne.ufr27.miage.model.VenueQuota;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RequestScoped
public class RefundServiceImpl implements RefundService {

    @PersistenceContext
    EntityManager em;

    @Inject
    VenueQuotaDAO venueQuotaDAO;

    @Inject
    TicketGateway ticketGateway;



    protected void sendEmailConfirmation(int artistId, Venue venue) {
        Collection<VenueLineUp> venueLineupToRemove = new ArrayList<>();
        for (VenueLineUp lineup : venue.getLineUp()) {
            if (lineup.getId().getArtist().getIdArtist().equals(artistId)) {
                venueLineupToRemove.add(lineup);
            }
        }

        for (VenueLineUp lu : venueLineupToRemove) {
            em.remove(lu);
        }

    }
}
