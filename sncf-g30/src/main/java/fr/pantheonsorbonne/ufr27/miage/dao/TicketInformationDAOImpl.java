package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TicketInformationDAOImpl implements TicketInformationDAO {

    @Inject
    EntityManager em;

    @Override
    public TicketInformation findRequestById(int trajetId, int trainId) {
        return em.find(TicketInformation.class, trajetId);
    }
    @Override
    public TicketInformation findRequestByIdTicket(int ticketId){
        return em.find(TicketInformation.class, ticketId);
    }


}