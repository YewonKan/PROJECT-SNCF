package fr.pantheonsorbonne.ufr27.miage.service;



import fr.pantheonsorbonne.ufr27.miage.dto.Gig;
import fr.pantheonsorbonne.ufr27.miage.dto.RemainingQuota;
import fr.pantheonsorbonne.ufr27.miage.model.Client;

import java.util.Collection;

public interface RefundService {
//    RemainingQuota getRemainingQuotaForVendor(int idVendor, int idVenue);
//
//    Collection<Gig> getAvailableGigs(int idVendor);


    void refundForReservation(int reservationId, Client cli);
}
