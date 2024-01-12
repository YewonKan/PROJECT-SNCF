package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Client;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;


public class FidelityResource {

    @Inject
    InsertService insertService;
//    @Inject
//    FidelityGateway fidelityGateway;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response askFidelity(Client client, int ticketId) {
        URI orderUri = UriBuilder.fromResource(Client.class)
                .path(Client.class, "")
                .build(client.getIdClient());

       // String response = producerTemplate.sendBody("direct:fidelityRequest", ExchangePattern.InOut, client, String.class);
        String response = "need config Gateway";
        //fidelityGateway.sendRequestToFidelity(client.getIdClient());

        insertService.insertCompensationType(response, ticketId);
        return Response.accepted().build();
    }
}
