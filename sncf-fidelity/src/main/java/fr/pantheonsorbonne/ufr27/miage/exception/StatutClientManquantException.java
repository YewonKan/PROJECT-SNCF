package fr.pantheonsorbonne.ufr27.miage.exception;

public class StatutClientManquantException extends Exception {

    public StatutClientManquantException(int id) {
        super("Le statut du client est manquant. ID du client : " + id);
    }
}
