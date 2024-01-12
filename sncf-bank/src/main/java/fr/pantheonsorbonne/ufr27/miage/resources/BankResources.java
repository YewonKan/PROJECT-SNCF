package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.model.RefundRequest;
import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
import fr.pantheonsorbonne.ufr27.miage.service.InsertService;
import fr.pantheonsorbonne.ufr27.miage.service.UpdateService;
import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("Bank")
public class BankResource {


    //@Inject
    //bankGateway  bankGateway;
    @Path(("/askRefund"))
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response askRefund(DTO) {

        insertService.insertCompensationType();

    }
    @Path(("/getPctCompensation"))
    @POST
    public Response getCompensPercentage(int trajetId, int trainId) {
        double result = calculationService.getCompensationAmount(trajetId,trainId);
        return Response.ok(result).build();
    }
}
