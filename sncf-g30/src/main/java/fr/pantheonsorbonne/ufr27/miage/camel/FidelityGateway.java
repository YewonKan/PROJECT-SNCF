package fr.pantheonsorbonne.ufr27.miage.camel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
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

    @Inject
    ConnectionFactory connectionFactory;


    public void startCheckFidelityEvent(int clientId) {
        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            // Créer le message JMS avec le corps du message
            context.createProducer().send(context.createQueue("M1.G30fidelity"), clientId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi au fidelity", e);
        }
    }

}
