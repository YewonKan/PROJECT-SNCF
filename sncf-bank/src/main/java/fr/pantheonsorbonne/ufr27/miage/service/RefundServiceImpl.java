package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.camel.TicketGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.VenueQuotaDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Venue;
import fr.pantheonsorbonne.ufr27.miage.model.VenueLineUp;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collection;

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
