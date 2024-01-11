package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;
import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("Bank")
public class BankResource {

    @Inject
    VerificationService verificationService;

    @Inject
    CalculationService calculationService;

    //@Inject
    //bankGateway  bankGateway;
    @Path(("/sendBank"))
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response isTicketDelayed(int trainId, int trajetId,int ticketId) {

        if (verificationService.isEligibleForRefund(trainId, trajetId)&&verificationService.isRefundExecuted(ticketId).equals(RefundRequest.RefundStatus.ELIGIBLE)) {
            URI bankUri = UriBuilder.fromResource(RefundRequest.class)
                    .path(RefundRequest.class, "get")
                    .build(ticketId);;

//            bankGateway.sendRefundResultToBank(ticketId);

            return Response.created(bankUri)
                    .entity(ticketId) // Optionnel, si vous souhaitez renvoyer les détails de la commande
                    .build();
        } else {
            return Response.status(422, "Refund request is not eligible").build();
        }
    }
    @Path(("/getPctCompensation"))
    @POST
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCompensPercentage(int trajetId, int trainId) {
        double result = calculationService.getCompensationAmount(trajetId,trainId);
        return Response.ok(result).build();
    }
}
