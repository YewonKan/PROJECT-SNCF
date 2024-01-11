package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;


public interface ReseauService {

    DelayNotification sendDelayNotification(String idTrain, int delayDuration, String reason, String creationTime);
}
