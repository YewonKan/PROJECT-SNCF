package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.DelayInformation;


import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FidelityGateway {

    @Inject
    VerificationService verificationService;

    public double delay(DelayInformation d) throws UnsuficientQuotaForVenueException {
        return verificationService.getCompensPercentage(d.getIdTrajet(), d.getIdTrain());
    }


}

