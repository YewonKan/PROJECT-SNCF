package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.transaction.Transactional;

public interface CompensationDAO {
    @Transactional
    Compensation save(Compensation compensation);
}
