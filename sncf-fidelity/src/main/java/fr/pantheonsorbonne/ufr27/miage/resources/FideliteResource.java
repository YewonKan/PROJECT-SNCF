package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.service.FideliteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/fidelite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FideliteResource {

    @Inject
    FideliteService fideliteService;

    @POST
    @Path("/verifyClientStatus")
    public Response verifyClientStatus(Compensation compensation) {
        try {
            if (compensation.getClient() == null || compensation.getClient().getStatus() == null) {
                return Response.status(422).build();
            }

            Compensation result = fideliteService.verifyClientStatus(compensation.getClient().getId());
            return Response.ok(result).build();
        } catch (StatutClientManquantException e) {
            return Response.status(422).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}

