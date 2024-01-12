package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;
import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.UpdateService;
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
    UpdateService updateService;
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

        if (verificationService.isEligibleForRefund(trainId, trajetId)&&verificationService.isRefundExecuted(ticketId).equals(Compensation.RefundStatus.ELIGIBLE)) {
            URI bankUri = UriBuilder.fromResource(RefundRequest.class)
                    .path(RefundRequest.class, "get")
                    .build(ticketId);;

//            bankGateway.sendRefundResultToBank(ticketId);

            updateService.updateStatusRefunded(ticketId);

            return Response.created(bankUri)
                    .entity(ticketId)
                    .build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    @Path(("/getPctCompensation"))
    @POST
    public Response getCompensPercentage(int trajetId, int trainId) {
        double result = calculationService.getCompensationAmount(trajetId,trainId);
        return Response.ok(result).build();
    }
}
