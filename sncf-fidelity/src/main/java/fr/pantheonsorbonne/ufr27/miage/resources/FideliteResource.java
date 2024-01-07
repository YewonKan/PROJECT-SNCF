package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.StatutClientManquantException;
import fr.pantheonsorbonne.ufr27.miage.model.Compensation;
import fr.pantheonsorbonne.ufr27.miage.service.FideliteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/fidelite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FideliteResource {

    private static final Logger LOGGER = Logger.getLogger(FideliteResource.class.getName());

    @Inject
    FideliteService fideliteService;

    @POST
    @Path("/verifyClientStatus")
    public Response verifyClientStatus(Compensation compensation) {
        try {
            if (compensation.getClient() == null || compensation.getClient().getStatus() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erreur : Le statut du client est manquant.")
                        .build();
            }

            Compensation result = fideliteService.verifyClientStatus(compensation.getClient());

            // Vous pouvez maintenant renvoyer la compensation comme réponse sans l'afficher dans la console
            return Response.ok(result).build();
        } catch (Exception e) {
            // En cas d'erreur imprévue, renvoyez une réponse d'erreur avec le message
            LOGGER.severe("Erreur lors de la vérification du statut du client : " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur interne du serveur.")
                    .build();
        } catch (StatutClientManquantException e) {
            throw new RuntimeException(e);
        }
    }
}
