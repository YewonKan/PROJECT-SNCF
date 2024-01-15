package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DelayInformationDAOImpl implements DelayInformationDAO {

    @PersistenceContext(name = "mysql")

    EntityManager em;

    @Override
    public DelayInformation findById(int trajetId, int trainId) {
        return em.find(DelayInformation.class, trajetId);
    }

    @Override
    @Transactional
    public DelayInformation insertDelayInformation(DelayInformation d){
       // DelayInformation managedEntity = em.find(DelayInformation.class, d.getId());
        em.merge(d);
        return d;
    }
}
