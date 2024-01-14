package fr.pantheonsorbonne.ufr27.miage.dto;

public record TicketTransmissionDTO( Integer idTicket, Integer idTrip, Integer idCustomers, Integer idTrain, String fname, String lname, String email, String phone, Double prix) {


}
