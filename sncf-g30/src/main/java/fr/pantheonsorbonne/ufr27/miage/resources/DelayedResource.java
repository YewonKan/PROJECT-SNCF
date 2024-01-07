package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("G30")
public class DelayedResource {


    @Inject
    VerificationService verificationService;

    @Path(("/isDelayed"))
    @POST
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response isTicketDelayed(int ticketId) {
        if (verificationService.checkIfDelayed(ticketId)) {
            boolean result = verificationService.checkIfDelayed(ticketId);
            return Response.ok(result).build();
        } else {
            return Response.status(422, "This train has never been delayed or has not yet departed.").build();
        }
    }

    @Path(("/getPctCompensation"))
    @POST
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompensPercentage(int ticketId) {
            double result = verificationService.getCompensPercentage(ticketId);
            return Response.ok(result).build();
    }

}
