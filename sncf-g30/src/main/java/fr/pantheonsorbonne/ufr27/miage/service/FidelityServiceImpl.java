package fr.pantheonsorbonne.ufr27.miage.service;
import fr.pantheonsorbonne.ufr27.miage.dao.FideliteDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class FidelityServiceImpl implements FidelityService {
    @Inject
    FideliteDAO fideliteDAO;

    @Override
    @Transactional
    public Compensation getCompensationType(int clientId) {

    }
}
