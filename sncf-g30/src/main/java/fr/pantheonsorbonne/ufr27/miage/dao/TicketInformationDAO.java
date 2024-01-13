package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;

public interface TicketInformationDAO {
    TicketInformation findRequestById(int trajetId, int trainId);
    TicketInformation findRequestByIdTicket(int ticketId);
    void insertTicketinfo(TicketInformation t);

}
