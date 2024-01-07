package fr.pantheonsorbonne.ufr27.miage.service;


public interface VerificationService {
    boolean checkIfDelayed(int ticketId);
//    boolean isWithin60Days();
    double getCompensPercentage(int ticketId);
}
