package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public interface CompensationDAO {
    Compensation findById(int trajetId, int trainId);

}
