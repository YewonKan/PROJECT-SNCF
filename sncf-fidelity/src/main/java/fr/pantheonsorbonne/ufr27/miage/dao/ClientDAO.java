package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Client;
import jakarta.transaction.Transactional;

public interface ClientDAO {
    @Transactional
    Client createNewClient(String Cname, String Cstatus);

    @Transactional
    Client findClientById(int clientId);

}
