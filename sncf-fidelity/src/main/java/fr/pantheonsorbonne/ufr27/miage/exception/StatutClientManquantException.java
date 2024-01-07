package fr.pantheonsorbonne.ufr27.miage.exception;

public class StatutClientManquantException extends Throwable {
    public StatutClientManquantException(int id) {
        super("Le statut du client est manquant." + id);
    }
}
