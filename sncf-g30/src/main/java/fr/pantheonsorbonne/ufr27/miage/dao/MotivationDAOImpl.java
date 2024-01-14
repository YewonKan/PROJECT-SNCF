package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Motivation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
// Assuming this is part of a class annotated with @Repository or @Service
public class MotivationDAOImpl implements MotivationDAO {

    @Inject
    EntityManager em;


    @Override
    public Motivation isEligibleMotivation(String motivationText) {
        String jpql = "SELECT m FROM Motivation m WHERE m.motivationText = :motivationText";

        TypedQuery<Motivation> query = em.createQuery(jpql, Motivation.class);
        query.setParameter("motivationText", motivationText);

        List<Motivation> motivations = query.getResultList();

        return motivations.isEmpty() ? null : motivations.get(0);
    }
}