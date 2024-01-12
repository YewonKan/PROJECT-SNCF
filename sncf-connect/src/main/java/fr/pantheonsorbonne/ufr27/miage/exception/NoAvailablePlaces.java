package fr.pantheonsorbonne.ufr27.miage.exception;

public class NoAvailablePlaces extends  Exception{

    public NoAvailablePlaces(int quota) { super("no available places, quota trip =" + quota);
    }

}
