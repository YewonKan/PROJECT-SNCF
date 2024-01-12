package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.ReseauService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class NotificationDelayGateway {
    //utiliser peut etre Object Mapper
    //private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Inject
    ReseauService reseauService;

    @ConfigProperty(name="fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    public void sendDelayNotification(String idTrain, int delayDuration, String reason, String creationTime) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            DelayNotification delayNotification = reseauService.sendDelayNotification(idTrain, delayDuration, reason, creationTime);
            String notificationMessage = buildDelayNotificationMessage(idTrain, delayDuration, reason);

            // Sending the delay notification message
            producerTemplate.sendBody("sjms2:topic:" + jmsPrefix + ".delayNotification", notificationMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String buildDelayNotificationMessage(String idTrain, int delayDuration, String reason) {

        return "Train " + idTrain + " is delayed by " + delayDuration + " minutes due to " + reason + ".";
    }
}
