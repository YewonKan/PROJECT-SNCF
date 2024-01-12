package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.DelayInformationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.RefundRequestDAO;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class CalculationServiceImpl implements CalculationService {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    DelayInformationDAO delayInformationDAO;
    @Inject
    RefundRequestDAO refundRequestDAO;

    @Override
    @Transactional
    public double getCompensationAmount(int trajetId, int trainId){
        DelayInformation delayInformation = delayInformationDAO.findById(trajetId, trainId);
        RefundRequest refundRequest =refundRequestDAO.findRequestById(trajetId, trainId);
        int delayedMinutes = delayInformation.getDelayedMinutes();
        if (delayInformation.getDelayedMinutes() >= 30 && delayedMinutes <= 59) {
            return 0.25*refundRequest.getPrix();
        } else if (delayedMinutes > 60) {
            return 0.50*refundRequest.getPrix();
        } else {
            return 0.0;
        }
    }
}