package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class CompensationDAOImpl implements CompensationDAO {
    @Inject
    EntityManager em;
    @Override
    Compensation findById(int trajetId, int trainId);

}
