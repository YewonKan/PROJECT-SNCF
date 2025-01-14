package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Client;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientDAOImpl implements ClientDAO {
    @PersistenceContext(name = "mysql")
    EntityManager em;


    @Override
    @Transactional
    public Client createNewClient(String Cname, String Cstatus) {
        Client c = new Client(Cname, Cstatus);
        em.persist(c);
        return c;
    }

    @Override
    @Transactional
    public Client findClientById(int clientId){
        return em.find(Client.class, clientId);
    }
}
