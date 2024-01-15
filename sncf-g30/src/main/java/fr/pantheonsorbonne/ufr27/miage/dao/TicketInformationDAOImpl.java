package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TicketInformationDAOImpl implements TicketInformationDAO {

    @PersistenceContext(name = "mysql")
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
        System.out.println("blbl");
        TicketInformation newTicketInformation = new TicketInformation();
        newTicketInformation.setClientId(t.getClientId());
        newTicketInformation.setPrix(t.getPrix());
        newTicketInformation.setTicketId(t.getTicketId());
        newTicketInformation.setTrainId(t.getTrainId());
        newTicketInformation.setTrajetId(t.getTrajetId());

        em.persist(newTicketInformation);
    }

}