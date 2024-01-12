package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;

public interface RefundRequestDAO {
    RefundRequest findRequestById(int trajetId,int trainId);
    RefundRequest findRequestByIdTicket(int ticketId);

}
