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
    // gotta correct it
    @Override
    public TicketInformation findRequestByIdTicket(int ticketId){
        return em.find(TicketInformation.class, ticketId);
    }

    @Override
    @Transactional
    public void insertTicketinfo(TicketInformation t) {

        /*TicketInformation ticketInfo = new TicketInformation();
        ticketInfo.setClientId(t.getClientId());
        ticketInfo.setPrix(t.getPrix());
        ticketInfo.setTicketId(t.getTicketId());
        ticketInfo.setTrainId(t.getTrainId());
        ticketInfo.setTrajetId(t.getTrajetId());*/

        TicketInformation ticketInfo = new TicketInformation();
        ticketInfo.setClientId(100);
        ticketInfo.setPrix(123.3);
        ticketInfo.setTicketId(322);
        ticketInfo.setTrainId(4322);
        ticketInfo.setTrajetId(54321);

        em.persist(t);
    }

}