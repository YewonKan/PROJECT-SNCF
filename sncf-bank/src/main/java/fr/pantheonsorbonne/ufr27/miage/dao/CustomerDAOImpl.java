package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public Client findMatchingCustomer(String email) throws CustomerNotFoundException {
        try {
            Client c = (Client) em.createQuery("Select c from Client c where c.email=:email").setParameter("email", email).getSingleResult();
            return c;
        } catch (NoResultException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    @Transactional
    public Client createNewCustomer(String fname, String lname, String email, String status) {
        Client c = new Client(fname, lname, email, status);
        em.persist(c);
        return c;
    }
}
