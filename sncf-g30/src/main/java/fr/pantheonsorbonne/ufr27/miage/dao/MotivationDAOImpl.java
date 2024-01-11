package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Motivation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
@ApplicationScoped
public class MotivationDAOImpl implements MotivationDAO{
    @Inject
    EntityManager em;

    @Override
    public Motivation isEligibleMotivation(int trainId, int trajetId) {
        return em.find(Motivation.class, trainId);
    }
}

