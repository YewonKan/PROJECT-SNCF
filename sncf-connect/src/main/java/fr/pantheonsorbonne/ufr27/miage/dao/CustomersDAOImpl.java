package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.CustomersNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Customers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomersDAOImpl implements CustomersDAO {

    @PersistenceContext(name = "mysql")
    EntityManager em;

    @Override
    public Customers findMatchingCustomer(String email) throws CustomersNotFoundException {
        try {
            Customers c = (Customers) em.createQuery("Select c from Customers c where c.email=:email").setParameter("email", email).getSingleResult();
            return c;
        } catch (NoResultException e) {
            throw new CustomersNotFoundException(email);
        }
    }

    @Override
    @Transactional
    public Customers createNewCustomer(String fname, String lname, String email, String phone) {
        Customers c = new Customers();

        c.setFname(fname);
        c.setLname(lname);
        c.setEmail(email);
        c.setPhone(phone);

        em.persist(c); // Persistez l'objet Customers dans la base de données

        return c; // Retournez l'objet Customers créé
    }

}
