package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

public interface FideliteService {
    Compensation verifyClientStatus(int clientId) throws StatutClientManquantException;
}
