package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped

public class CompensationDAOImpl implements CompensationDAO {
    @Inject
    EntityManager em;
    @Override
    public Compensation findByIdTicket(int ticketId){
        return em.find(Compensation.class, ticketId);
    }

    @Override
    @Transactional
    public Compensation setStatusRefund(int ticketId) {
        Compensation compensation = em.find(Compensation.class, ticketId);
        compensation.setStatusRefund(Compensation.RefundStatus.REFUNDED);
        em.persist(compensation);
        return compensation;
    }

    @Override
    @Transactional
    public Compensation insertCompensation(Compensation compensation) {
        em.persist(compensation);
        return compensation;
    }

}
