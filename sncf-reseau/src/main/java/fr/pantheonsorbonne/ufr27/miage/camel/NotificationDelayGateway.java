package fr.pantheonsorbonne.ufr27.miage.camel;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.ReseauService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.Date;

import static org.apache.camel.throttling.ThrottlingInflightRoutePolicy.ThrottlingScope.Context;

@ApplicationScoped
public class NotificationDelayGateway {
    @Inject
    ReseauService reseauService;
    @Inject
    ConnectionFactory connectionFactory;

    @ConfigProperty(name="fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendDelayNotification(Integer idTrain, Integer idTrajet, int delayDuration, String reason, Date creationTime) {
        try (JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {

            DelayNotification delayNotification = reseauService.sendDelayNotification(idTrain,idTrajet,delayDuration,reason,creationTime);

            // Convertir DelayNotification object en JSON string
            String delayNotificationJson = objectMapper.writeValueAsString(delayNotification);

            // Sending the delay notification message
            context.createProducer().send(context.createQueue("M1.delayNotification"),delayNotificationJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
