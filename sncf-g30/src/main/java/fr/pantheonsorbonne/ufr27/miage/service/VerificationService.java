package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;

public interface VerificationService {
    boolean isEligibleForRefund(int trajetId, int trainId);
    RefundRequest.RefundStatus isRefundExecuted(int clientId);

}
