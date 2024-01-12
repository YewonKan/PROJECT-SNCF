package fr.pantheonsorbonne.ufr27.miage.dto;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

import java.util.Date;

public record CompensationDTO(int ticketId, int clientID, String type, String detail, Date validateDate,int trainId, int trajetId) {
}
