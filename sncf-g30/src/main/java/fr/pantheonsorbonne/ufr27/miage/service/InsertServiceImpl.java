package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.CompensationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.FideliteDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class InsertServiceImpl implements InsertService {

    @Inject
    FideliteDAO fideliteDAO;
    @Inject
    CompensationDAO compensationDAO;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Compensation insertCompensationType(String response,int ticketId) {
//        Compensation newCompensation =
//                new Compensation(response);
//        compensationDAO.insertCompensation(newCompensation);
//        return newCompensation;
        return null;
    }
}
