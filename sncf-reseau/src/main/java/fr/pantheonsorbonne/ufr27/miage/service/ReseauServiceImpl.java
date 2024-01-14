package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.DelayNotificationDAO;
import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Date;

@ApplicationScoped
public class ReseauServiceImpl implements ReseauService {
    @Inject
    DelayNotificationDAO delayNotificationDAO;

    @Override
    public DelayNotification sendDelayNotification(Integer idTrain,Integer idTrajet, int delayDuration, String reason, Date creationTime) {

        return delayNotificationDAO.createNewDelayNotification(idTrain,idTrajet,delayDuration,reason,creationTime);
    }

}
