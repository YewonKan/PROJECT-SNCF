package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.CompensationDTO;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import fr.pantheonsorbonne.ufr27.miage.service.UpdateService;
import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;

@Path("Bank")
public class ClientResource {

    @Inject
    UpdateService updateService;
    @Inject
    VerificationService verificationService;

    @Inject
    CalculationService calculationService;

    @Inject
    InsertService insertService;

    //@Inject
    //bankGateway  bankGateway;
    @Path(("/askRefund"))
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response askRefund(CompensationDTO compensationDTO) {
        Date currentDate = new Date();
        if (verificationService.isEligibleForRefund(compensationDTO.trainId(), compensationDTO.trajetId(), currentDate)&&verificationService.isRefundExecuted(compensationDTO.ticketId()).equals(Compensation.RefundStatus.ELIGIBLE)) {
  //          bankGateway.sendRefundResultToBank(compensationDTO.clientId());
            insertService.insertCompensationType(compensationDTO);
            return Response.accepted().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    @Path(("/getPctCompensation"))
    @POST
    public Response getCompensPercentage(int trajetId, int trainId, int ticketId) {
        double result = calculationService.getCompensationAmount(trajetId,trainId,ticketId);
        return Response.ok(result).build();
    }
}
