package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dao.*;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.Motivation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;

@ApplicationScoped
public class VerificationServiceImpl implements VerificationService {
    @Inject
    DelayInformationDAO delayInformationDAO;
    @Inject
    TicketInformationDAO ticketInformationDAO;
    @Inject
    CompensationDAO compensationDAO;
    @Inject
    MotivationDAO motivationDAO;

    @Override
    public boolean isEligibleForRefund(int trajetId, int trainId, Date currentDate) {
        DelayInformation delayInformation = delayInformationDAO.findById(trajetId, trainId);

        // Check if the trip is delayed
        if (delayInformation != null) {
            TicketInformation ticket = ticketInformationDAO.findRequestById(trajetId, trainId);

            // Check if the request is within 60 days
            long timeDifference = currentDate.getTime() - delayInformation.getDelayedDate().getTime();
            long daysDifference = timeDifference / (1000 * 60 * 60 * 24);
            boolean isWithin60Days = daysDifference <= 60;

            // Check if the motivation is eligible
            Motivation motivation = motivationDAO.isEligibleMotivation(delayInformation.getDelayMotivation());

            // Handle the case where motivation is null
            if (motivation != null) {
                boolean isEligibleMotivation = motivation.getMotivationStatus();
                return isWithin60Days && isEligibleMotivation;
            } else {
                // Log or handle the case where motivation is null
                return true;
            }
        }

        return false; // Not eligible if the trip is not delayed
    }


    @Override
    public Compensation.RefundStatus isRefundExecuted(int ticketId) {
        Compensation compensation = compensationDAO.findByIdTicket(ticketId);

        // Check if compensation is null
        if (compensation == null) {
            return Compensation.RefundStatus.ELIGIBLE;
        }

        return compensation.getStatusRefund();
    }
    @Override
    public Compensation getCompensation(int ticketId){
        Compensation compensation =compensationDAO.findByIdTicket(ticketId);
        return compensation;
    }
}
