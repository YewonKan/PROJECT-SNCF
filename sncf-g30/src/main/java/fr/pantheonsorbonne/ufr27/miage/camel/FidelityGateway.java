package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.*;

@ApplicationScoped
public class FidelityGateway {

    @Inject
    ConnectionFactory connectionFactory;

    public void startCheckFidelityEvent(int clientId) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            //On créer un message contenant l'orderDTO sous forme de json
            TextMessage msg = context.createTextMessage(String.valueOf(clientId));
            //On envoie l'order aux darkKitchen via le topic M1.DK
            context.createProducer().send(context.createTopic("M1.G30_REQUEST"), msg);
            Log.info("(recherche de fidelity): " + clientId);
        } catch (JMSRuntimeException e) {
            Log.error("Erreur lors de l'envoi au fidelity: ", e);
        }
    }
}