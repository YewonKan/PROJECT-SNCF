//package fr.pantheonsorbonne.ufr27.miage.camel;
//
//
//import org.apache.camel.builder.RouteBuilder;
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//
//import jakarta.enterprise.context.ApplicationScoped;
//
//@ApplicationScoped
//public class G30CamelRoutes extends RouteBuilder {
//
//    @ConfigProperty(name = "camel.routes.enabled", defaultValue = "true")
//    boolean isRouteEnabled;
//
//    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.jmsPrefix")
//    String jmsPrefix;
//
//    @Override
//    public void configure() throws Exception {
//
//        from("direct:g30Request")  // Route starting point
//                .autoStartup(isRouteEnabled)
//                .log("Route G30CamelRoutes is called")
//                .to("sjms2:queue:" + jmsPrefix + "g30Request");  // Sending the request to the JMS queue
//
//        from("sjms2:queue:" + jmsPrefix + "g30Request")  // Listening to the JMS queue for G30 requests
//                .autoStartup(isRouteEnabled)
//                .log("Received G30 request: ${body}")  // Log the received request
//
//                // Assume the response from Fidelity is in the message body
//                .choice()
//                .when(simple("${body} == 'Response from Fidelity'"))
//                .log("Received successful response from Fidelity: ${body}")  // Log successful response
//                .to("direct:processFidelityResponse")  // Process the successful response
//                .otherwise()
//                .log("Received error response from Fidelity: ${body}")  // Log error response
//                .process(exchange -> {
//                    // Assuming Fidelity sends an error message in the body
//                    String errorMessage = exchange.getIn().getBody(String.class);
//                    // You can handle the error message or throw an exception as needed
//                    throw new RuntimeException("Error from Fidelity: " + errorMessage);
//                })
//                .end();
//
//        from("direct:processFidelityResponse")
//                .log("Processing successful response from Fidelity: ${body}")
//                // Add your logic to process the successful response from Fidelity
//                .to("sjms2:topic:" + jmsPrefix + "fidelityResponse");  // Sending the response to a JMS topic
//    }
//}