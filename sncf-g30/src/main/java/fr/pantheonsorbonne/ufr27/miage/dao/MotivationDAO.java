package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Motivation;

public interface MotivationDAO {

    Motivation isEligibleMotivation(String motivationText);
}
