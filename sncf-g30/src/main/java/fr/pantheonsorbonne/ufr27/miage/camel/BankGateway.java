package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import io.quarkus.logging.Log;
import jakarta.jms.*;
import org.apache.camel.CamelContext;
import org.apache.camel.Handler;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class BankGateway {

    @Inject
    ConnectionFactory connectionFactory;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
    String jmsPrefix;

    @Inject
    CamelContext camelContext;

    @Handler
    public void emitBankSendMessage(Compensation c) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            TextMessage message = context.createTextMessage("Transmission in progress for Bank ");

            message.setIntProperty("idClient", c.getClient());
            message.setDoubleProperty("amount", c.getAmount());
            message.setStringProperty("detail", c.getDetail());
            message.setLongProperty("validateDate", c.getValidateDate().getTime());

            context.createProducer().send(context.createQueue("M1.SNCF_G30_BANK_INFORMATION"), message);
            Log.info("Ticket message sent for ticket with ID: " + c.getTicketId());
        } catch (JMSRuntimeException e) {
            Log.error("Error while sending bank information message " + e);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}