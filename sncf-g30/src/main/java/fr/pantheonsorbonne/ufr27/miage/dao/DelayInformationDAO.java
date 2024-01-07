package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;

public interface DelayInformationDAO {
    DelayInformation findById(int ticketId);
}
