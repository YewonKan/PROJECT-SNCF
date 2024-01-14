package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;

import java.util.Date;


public interface ReseauService {

    DelayNotification sendDelayNotification(Integer idTrain,Integer idTrajet, int delayDuration, String reason, Date creationTime);
}
