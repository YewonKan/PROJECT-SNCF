package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CompensationDAOImpl implements CompensationDAO {

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public Compensation save(Compensation compensation) {
        entityManager.persist(compensation);
        return compensation;
    }
}
