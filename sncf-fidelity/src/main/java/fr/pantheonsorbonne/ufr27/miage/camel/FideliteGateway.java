/*
package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;

@ApplicationScoped
public class FideliteGateway {


    @Inject
    ServiceG30 serviceG30;

    @Handler
    public void sendCompensationInfo(Compensation compensation, Exchange exchange) throws StatutClientManquantException {
        // Appel du service G30 pour envoyer les informations de compensation
        serviceG30.sendInfo(
                compensation.getId(),
                compensation.getClient().getId(),
                compensation.getType(),
                compensation.getDetails(),
                compensation.getValidityDate()
        );
    }
}
*/
