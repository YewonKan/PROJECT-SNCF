package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.DelayNotificationDAO;
import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class DelayNotificationServiceImpl implements DelayNotificationService{
    @Inject
    DelayNotificationDAO delayNotificationDAO;

    @Override
    public DelayNotification sendDelayNotification(String idTrain, int delayDuration, String reason, LocalDateTime creationTime) {

        return delayNotificationDAO.createNewDelayNotification(idTrain, delayDuration, reason, creationTime);
    }

}
