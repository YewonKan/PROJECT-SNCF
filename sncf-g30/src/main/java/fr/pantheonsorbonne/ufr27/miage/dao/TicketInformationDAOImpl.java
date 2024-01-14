package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TicketInformationDAOImpl implements TicketInformationDAO {

    @Inject
    EntityManager em;

    @Override
    public TicketInformation findRequestById(int trajetId, int trainId) {
        return em.find(TicketInformation.class, trajetId);
    }
    //correct it !!
    @Override
    public TicketInformation findRequestByIdTicket(int ticketId){
        return em.find(TicketInformation.class, ticketId);
    }

    @Override
    @Transactional
    public void insertTicketinfo(TicketInformation t) {
        em.persist(t);
    }

}