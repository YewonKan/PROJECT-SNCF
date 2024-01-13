package fr.pantheonsorbonne.ufr27.miage.exception;

public class TicketDoesntExistException extends Exception{

    public TicketDoesntExistException(int idTicket)  { super("Ticket not Found" + idTicket);}

}
