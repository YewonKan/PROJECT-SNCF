package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.ReseauService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@ApplicationScoped
public class NotificationDelayGateway {
    @Inject
    ReseauService reseauService;

    @ConfigProperty(name="fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendDelayNotification(String idTrain, int delayDuration, String reason, String creationTime) {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            DelayNotification delayNotification = reseauService.sendDelayNotification(idTrain, delayDuration, reason, creationTime);

            // Convertir DelayNotification object en JSON string
            String delayNotificationJson = objectMapper.writeValueAsString(delayNotification);

            // Sending the delay notification message
            producerTemplate.sendBody("sjms2:topic:" + jmsPrefix + ".delayNotification", delayNotificationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
