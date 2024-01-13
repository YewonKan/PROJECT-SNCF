package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

import java.util.Date;

public interface VerificationService {
    boolean isEligibleForRefund(int trajetId, int trainId, Date currentDate);
    Compensation.RefundStatus isRefundExecuted(int clientId);

    Compensation getCompensation(int ticketId);
}
