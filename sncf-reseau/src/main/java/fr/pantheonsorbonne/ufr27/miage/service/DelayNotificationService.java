package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;

import java.time.LocalDateTime;

public interface DelayNotificationService {

    DelayNotification sendDelayNotification(String idTrain, int delayDuration, String reason, LocalDateTime creationTime);
}
