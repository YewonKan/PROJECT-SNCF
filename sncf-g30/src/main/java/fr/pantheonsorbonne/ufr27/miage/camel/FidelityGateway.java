//package fr.pantheonsorbonne.ufr27.miage.camel;
//
//import io.quarkus.logging.Log;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.jms.*;
//
//@ApplicationScoped
//public class FidelityGateway {
//
//    @Inject
//    ConnectionFactory connectionFactory;
//
//    public void startCheckFidelityEvent() {
//        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
//            TextMessage message = context.createTextMessage("Delivery has started");
//            context.createProducer().send(context.createQueue("M1.ORDER_GIVEN_TO_DELIVERYMAN"), message);
//        } catch (JMSRuntimeException e) {
//            Log.error("Error : ORDER_GIVEN_TO_DELIVERYMAN", e);
//        }
//    }
//}