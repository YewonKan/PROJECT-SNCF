package fr.pantheonsorbonne.ufr27.miage.exception;

public class TripNotFoundException extends Exception {
    public TripNotFoundException(int idTrip) { super("error trip reference " + idTrip);
    }
}
