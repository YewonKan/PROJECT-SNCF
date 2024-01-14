package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;

import java.time.LocalDateTime;
import java.util.Date;

public interface DelayNotificationDAO {

    DelayNotification createNewDelayNotification(Integer idTrain,Integer idTrajet, int delayDuration, String reason, Date creationTime);

}
