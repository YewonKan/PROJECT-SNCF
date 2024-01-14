package fr.pantheonsorbonne.ufr27.miage.dto;

import java.util.Date;

public record DelayNotificationDTO(Integer idTrain, Integer idTrajet, int delayDuration, String reason, Date creationTime) {
}
