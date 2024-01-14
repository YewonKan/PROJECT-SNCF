package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Date;


@ApplicationScoped
public class DelayNotificationDAOImpl implements DelayNotificationDAO{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public DelayNotification createNewDelayNotification(Integer idTrain, Integer idTrajet, int delayDuration, String reason, Date creationTime){
        DelayNotification d = new DelayNotification(idTrain,idTrajet,delayDuration,reason,creationTime);
        d = em.merge(d);

        em.persist(d);
        return d;
    }
}

