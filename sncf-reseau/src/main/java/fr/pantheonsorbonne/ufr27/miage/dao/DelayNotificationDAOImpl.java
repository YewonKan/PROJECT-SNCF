package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class DelayNotificationDAOImpl implements DelayNotificationDAO{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public DelayNotification createNewDelayNotification(String idTrain, int delayDuration, String reason, String creationTime){
        DelayNotification d = new DelayNotification(idTrain,delayDuration,reason,creationTime);
        em.persist(d);
        return d;
    }
}
