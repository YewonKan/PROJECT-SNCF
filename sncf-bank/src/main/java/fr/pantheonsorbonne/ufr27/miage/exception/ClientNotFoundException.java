package fr.pantheonsorbonne.ufr27.miage.exception;

public class ClientNotFoundException extends Exception {
    public static class NoSeatAvailableException extends Throwable {
        public NoSeatAvailableException(int venueId) {
            super("No available seat for " + venueId);
        }
    }
}
