package fr.pantheonsorbonne.ufr27.miage.exception;

public class NoAvailablePlaces extends Throwable {

    public NoAvailablePlaces(int quota) { super("no available places, quota trip =" + quota);
    }

}
