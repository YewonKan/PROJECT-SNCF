package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.ClientNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;

public interface CustomerDAO {
    Client findMatchingClient(String email) throws ClientNotFoundException;

    Client createNewClient(String fname, String lname, String email, String status);
}
