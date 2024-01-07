package fr.pantheonsorbonne.ufr27.miage.service;


import fr.pantheonsorbonne.ufr27.miage.dao.*;
import fr.pantheonsorbonne.ufr27.miage.dto.Ticket;

import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VerificationServiceImpl implements VerificationService {
    @Inject
    DelayInformationDAO delayInformationDAO;

    @Override
    public boolean checkIfDelayed(int ticketId) {
        DelayInformation delayInformation = delayInformationDAO.findById(ticketId);
        return delayInformation != null;
    }

    @Override
    @Transactional
        public double getCompensPercentage(int ticketId){
            DelayInformation delayInformation = delayInformationDAO.findById(ticketId);
            int delayedMinutes = delayInformation.getDelayedMinutes();
            if (delayInformation.getDelayedMinutes() >= 30 && delayedMinutes <= 59) {
                return 0.25;
            } else if (delayedMinutes > 60) {
                return 0.50;
            } else {
                return 0.0;
            }
        }


//    @Override
//    @Transactional
//    public Booking book(Booking booking) throws UnsuficientQuotaForVenueException {
//        try {
//            VenueQuota vq = venueQuotaDAO.getMatchingQuota(booking.getVendorId(), booking.getVenueId(), booking.getStandingTicketsNumber(), booking.getSeatingTicketsNumber());
//            vq.setSeatingQuota(vq.getSeatingQuota() - booking.getSeatingTicketsNumber());
//            vq.setStandingQuota(vq.getStandingQuota() - booking.getStandingTicketsNumber());
//
//            Venue venue = venueDAO.findById(booking.getVenueId());
//            Vendor vendor = vendorDAO.findById(booking.getVendorId());
//
//
//            for (int i = 0; i < booking.getStandingTicketsNumber(); i++) {
//                Ticket ticket = ticketDAO.save(Instant.now().plus(10, ChronoUnit.HOURS), vendor, venue);
//                booking.getStandingTransitionalTicket().add(ticket.getId());
//            }
//
//            for (int i = 0; i < booking.getSeatingTicketsNumber(); i++) {
//                Ticket ticket = ticketDAO.save(Instant.now().plus(10, ChronoUnit.MINUTES), vendor, venue);
//                booking.getSeatingTransitionalTicket().add(ticket.getId());
//            }
//        } catch (NonUniqueResultException | NoResultException e) {
//            throw new UnsuficientQuotaForVenueException(booking.getVenueId());
//        }
//        return booking;
//    }

}
