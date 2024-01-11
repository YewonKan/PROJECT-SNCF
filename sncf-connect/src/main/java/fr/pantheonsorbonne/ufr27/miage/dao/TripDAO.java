package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.Trip;

import java.util.Collection;
import java.util.List;

public interface TripDAO {

        Trip findById(int idTrip );

        List <Trip> getAllTrips();


        void deleteTrip(Trip trip);


}
