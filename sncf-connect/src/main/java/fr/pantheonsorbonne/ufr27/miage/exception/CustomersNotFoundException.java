package fr.pantheonsorbonne.ufr27.miage.exception;

public class CustomersNotFoundException extends Exception {

    public CustomersNotFoundException(String email)  { super("Customer not Found" + email);}
}
