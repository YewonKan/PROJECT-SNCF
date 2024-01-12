package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dao.*;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VerificationServiceImpl implements VerificationService {
    @Inject
    DelayInformationDAO delayInformationDAO;
    @Inject
    RefundRequestDAO refundRequestDAO;
    @Inject
    CompensationDAO compensationDAO;
    @Inject
    MotivationDAO motivationDAO;

    @Override
    public boolean isEligibleForRefund(int trajetId, int trainId) {
        DelayInformation delayInformation = delayInformationDAO.findById(trajetId, trainId);

        // Check if the trip is delayed
        boolean isDelayed = delayInformation != null;

        if (isDelayed) {
            RefundRequest refundRequest = refundRequestDAO.findRequestById(trajetId, trainId);

            // Check if the request is within 60 days
            long timeDifference = refundRequest.getRequestDate().getTime() - delayInformation.getDelayedDate().getTime();
            long daysDifference = timeDifference / (1000 * 60 * 60 * 24);
            boolean isWithin60Days = daysDifference <= 60;

            // Check if the motivation is eligible
            boolean isEligibleMotivation = motivationDAO.isEligibleMotivation(trajetId, trainId).getMotivationStatus();

            return isWithin60Days && isEligibleMotivation;
        }

        return false; // Not eligible if the trip is not delayed
    }


    @Override
    public Compensation.RefundStatus isRefundExecuted(int ticketId){
        Compensation compensation =compensationDAO.findByIdTicket(ticketId);
        return compensation.getStatusRefund();
    }
}
