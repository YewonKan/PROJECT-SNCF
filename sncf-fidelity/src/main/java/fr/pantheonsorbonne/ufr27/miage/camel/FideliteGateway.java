package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.service.FideliteServiceImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.Handler;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;

@ApplicationScoped
public class FideliteGateway {

    @Inject
    FideliteServiceImpl fideliteService;

    @Inject
    CamelContext camelContext;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Handler
    public void processG30Request(Client client) throws IOException, StatutClientManquantException {

        System.out.println("FideliteGateway - processG30Request called with client ID: " + client.getId());

        // Call the FideliteService method to verify the client status
        // You need to adapt this based on the actual structure of your FideliteService methods
        String response = String.valueOf(fideliteService.verifyClientStatus(client.getId()));


        System.out.println("FideliteGateway - processG30Request response: " + response);


        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            producerTemplate.sendBodyAndHeader("sjms2:topic:" + jmsPrefix + "fidelityResponse", response, "clientId", client.getId());
        }
    }
}
