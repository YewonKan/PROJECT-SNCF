package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.CompensationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FideliteDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class UpdateServiceImpl implements UpdateService {

    @Inject
    FideliteDAO fideliteDAO;
    @Inject
    CompensationDAO compensationDAO;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Compensation updateStatusRefunded(int ticketId) {
        Compensation compensation= compensationDAO.setStatusRefund(ticketId);
        return compensation;
    }
}


