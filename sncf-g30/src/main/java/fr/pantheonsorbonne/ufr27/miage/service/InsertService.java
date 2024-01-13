package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import fr.pantheonsorbonne.ufr27.miage.model.TicketInformation;

public interface InsertService {
    Compensation insertCompensationType(CompensationDTO compensationDTO);
    DelayInformation insertDelayInformation(DelayInformation delayInformation);
    TicketInformation insertTicketInformation(TicketInformation ticketInformation);
    double getCompensationAmount(int trajetId, int trainId, int ticketId);

}
