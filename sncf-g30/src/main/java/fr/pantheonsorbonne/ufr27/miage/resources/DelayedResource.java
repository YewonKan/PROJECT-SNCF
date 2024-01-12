package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("DelayNotification")
public class DelayedResource {


    @Inject
    VerificationService verificationService;

    @Inject
    CalculationService calculationService;

    @Path("train/{trainId}/trajet/{trajetId}")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getDelayNotifivation(@PathParam("trainId") int trainId,@PathParam("trajetId") int trajetId, @PathParam("trajetId") int ticketId) {
            double result = calculationService.getCompensationAmount(trajetId,trainId,ticketId);
            return Response.ok(result).build();
    }
}
