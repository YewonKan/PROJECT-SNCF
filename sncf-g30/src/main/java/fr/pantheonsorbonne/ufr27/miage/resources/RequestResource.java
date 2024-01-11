package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("RefundRequest")
public class RequestResource {



    @Inject
    CalculationService calculationService;

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("trainId") int trainId,@PathParam("trajetId") int trajetId) {
        double result = calculationService.getCompensationAmount(trajetId,trainId);
        return Response.ok(result).build();
    }
}
