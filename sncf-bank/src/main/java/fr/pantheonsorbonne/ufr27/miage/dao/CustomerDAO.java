package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;

public interface CustomerDAO {
    Client findMatchingCustomer(String email) throws CustomerNotFoundException;

    Client createNewCustomer(String fname, String lname, String email, String status);
}
