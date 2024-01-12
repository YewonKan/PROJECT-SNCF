//package fr.pantheonsorbonne.ufr27.miage.resources;
//
//import fr.pantheonsorbonne.ufr27.miage.service.CalculationService;
//import fr.pantheonsorbonne.ufr27.miage.service.VerificationService;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//
//public class BankResource {
//
//
//
//    @Inject
//    CalculationService calculationService;
//
//    @GET
//    @Path("/{trainId}/{trajetId}/{ticketId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response get(
//            @PathParam("trainId") int trainId,
//            @PathParam("trajetId") int trajetId,
//            @PathParam("ticketId") int ticketId) {
//        updateService.updateStatusRefunded(compensationDTO.ticketId());
//
//        double result = calculationService.getCompensationAmount(trajetId, trainId, ticketId);
//        return Response.ok(result).build();
//    }
//}
