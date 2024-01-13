package fr.pantheonsorbonne.ufr27.miage.dto;

public record DelayNotification(String idTrain, int delayDuration, String reason, String creationTime) {
}
