package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.*;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class FidelityGateway {

    @Inject
    CamelContext camelContext;

    public void startCheckFidelityEvent(int clientId) {
        try (ProducerTemplate context = camelContext.createProducerTemplate()) {
            context.sendBody("direct:g30Request", clientId);
        } catch (IOException e) {
            Log.error("Erreur lors de l'envoi au fidelity: ", e);
        }
    }
}