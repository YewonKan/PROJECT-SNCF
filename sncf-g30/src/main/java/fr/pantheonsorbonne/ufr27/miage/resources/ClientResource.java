package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.camel.BankGateway;
import fr.pantheonsorbonne.ufr27.miage.camel.FidelityGateway;
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

@Path("g30")
public class ClientResource {

    @Inject
    UpdateService updateService;
    @Inject
    VerificationService verificationService;

    @Inject
    CalculationService calculationService;

    @Inject
    InsertService insertService;

    @Inject
    BankGateway bankGateway;
    @Inject
    FidelityGateway fidelityGateway;
    @Path(("/askRefund"))
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response askRefund(CompensationDTO compensationDTO) throws InterruptedException {
        Date currentDate = new Date();
        if (verificationService.isEligibleForRefund(compensationDTO.trainId(), compensationDTO.trajetId(), currentDate)&&verificationService.isRefundExecuted(compensationDTO.ticketId()).equals(Compensation.RefundStatus.ELIGIBLE)) {
            fidelityGateway.startCheckFidelityEvent(compensationDTO.clientID());
            insertService.insertCompensationType(compensationDTO); // need to put into camelRoutwe

            Thread.sleep(5000);

            Compensation c = verificationService.getCompensation(compensationDTO.ticketId());
            bankGateway.emitBankSendMessage(c);
            updateService.updateStatusRefunded(compensationDTO.ticketId());
            return Response.accepted(c).build(); //must have status Refunded
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
