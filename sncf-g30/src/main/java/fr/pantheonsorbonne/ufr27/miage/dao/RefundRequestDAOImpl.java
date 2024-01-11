package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class RefundRequestDAOImpl implements RefundRequestDAO {

    @Inject
    EntityManager em;

    @Override
    public RefundRequest findRequestById(int trajetId,int trainId) {
        return em.find(RefundRequest.class, trajetId);
    }
    @Override
    public RefundRequest findRequestByIdTicket(int ticketId){
        return em.find(RefundRequest.class, ticketId);
    }


}