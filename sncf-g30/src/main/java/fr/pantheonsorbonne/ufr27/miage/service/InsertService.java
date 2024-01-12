package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

public interface InsertService {
    Compensation insertCompensationType(String response,int ticketId);
}
