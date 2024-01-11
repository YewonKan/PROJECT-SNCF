package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.model.DelayNotification;
import fr.pantheonsorbonne.ufr27.miage.service.ReseauService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reseau")
public class ReseauResource {
    @Inject
    ReseauService reseauService;
    @POST
    @Path("/delay")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendDelayNotification(DelayNotification d) {
        DelayNotification notification = reseauService.sendDelayNotification(d.getIdTrain(), d.getDelayDuration(), d.getReason(), d.getCreationTime());

        return Response.ok(notification).build();
    }


}
