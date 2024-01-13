package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Date;

public record CompensationDTO(int ticketId, int clientID, String type, String detail, Date validateDate,int trainId, int trajetId) {
}
