package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;

import java.time.LocalDateTime;

public interface DelayNotificationDAO {

    DelayNotification createNewDelayNotification(String idTrain, int delayDuration, String reason, LocalDateTime creationTime);

}
