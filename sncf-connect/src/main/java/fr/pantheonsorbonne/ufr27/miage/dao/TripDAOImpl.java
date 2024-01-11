package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Trip;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class TripDAOImpl implements TripDAO {


    @PersistenceContext
    EntityManager em;
    @Override
    public Trip findById(int idTrip) {
        return em.find(Trip.class, idTrip);
    }

    @Override
    public List<Trip> getAllTrips() {
        return null;
    }

    @Override
    public void deleteTrip(Trip trip) {
    }



}
