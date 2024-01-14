package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.*;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class FidelityGateway {

    @Inject
    CamelContext camelContext;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    public void startCheckFidelityEvent(int clientId) {
        try (ProducerTemplate context = camelContext.createProducerTemplate()) {
            context.sendBody("sjms2:queue:"+jmsPrefix+"G30ToFidelity?exchangePattern=InOut", clientId);
        } catch (IOException e) {
            Log.error("Erreur lors de l'envoi au fidelity: ", e);
        }
    }
}