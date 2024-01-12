package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Client;

public interface FideliteDAO {
    Client findFidelityById(int ticketId);
}
