package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

public interface UpdateService {

    Compensation updateStatusRefunded(int ticketId);
}
