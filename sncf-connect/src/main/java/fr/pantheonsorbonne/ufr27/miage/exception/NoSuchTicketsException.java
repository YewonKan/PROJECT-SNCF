package fr.pantheonsorbonne.ufr27.miage.exception;

public class NoSuchTicketsException extends Throwable {

    public NoSuchTicketsException(int idTicket) { super("error ticket number " + idTicket);
    }

}
