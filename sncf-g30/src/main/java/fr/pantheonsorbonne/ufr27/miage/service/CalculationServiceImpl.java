package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.DelayInformationDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TicketInformationDAO;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class CalculationServiceImpl implements CalculationService {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    DelayInformationDAO delayInformationDAO;
    @Inject
    TicketInformationDAO ticketInformationDAO;

    @Override
    @Transactional
    public double getCompensationAmount(int trajetId, int trainId, int ticketId){
        DelayInformation delayInformation = delayInformationDAO.findById(trajetId, trainId);
        TicketInformation ticket = ticketInformationDAO.findRequestByIdTicket(ticketId);
        int delayedMinutes = delayInformation.getDelayedMinutes();
        if (delayInformation.getDelayedMinutes() >= 30 && delayedMinutes <= 59) {
            return 0.25 * ticket.getPrix();
        } else if (delayedMinutes > 60) {
            return 0.50 * ticket.getPrix();
        } else {
            return 0.0;
        }
    }
}