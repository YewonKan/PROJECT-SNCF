package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.service.FideliteServiceImpl;
import io.quarkus.logging.Log;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import org.apache.camel.CamelContext;
import org.apache.camel.Handler;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;

@ApplicationScoped
public class G30Gateway {

    @Inject
    FideliteServiceImpl fideliteService;

    @Inject
    CamelContext camelContext;
    @Inject
    ConnectionFactory connectionFactory;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Handler
    public void processG30Request(Client client) throws IOException, StatutClientManquantException {

        Log.info("??????????????????????" + client.getId());

        // Call the FideliteService method to verify the client status
        // You need to adapt this based on the actual structure of your FideliteService methods
        String response = String.valueOf(fideliteService.verifyClientStatus(client.getId()));

        System.out.println("FideliteGateway - processG30Request response: " + response);

        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
            // Convertir la réponse en JSON
            String responseJson = objectMapper.writeValueAsString(response);

            // Créer le message JMS avec le corps du message et l'en-tête clientId
            context.createProducer().send(context.createQueue("M1." + jmsPrefix + "fidelityResponse"), responseJson);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'envoi au fidelity", e);
        }
    }

}


